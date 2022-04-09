package fairyShop.core;

import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;
import fairyShop.repositories.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static fairyShop.common.ConstantMessages.*;
import static fairyShop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{
    private Repository<Helper> helperRepository;
    private Repository<Present> presentRepository;
    private int craftedPresentsCount;
    public ControllerImpl() {
        this.helperRepository = new HelperRepository();
        this.presentRepository = new PresentRepository();
    }
    @Override
    public String addHelper(String type, String helperName) {
        Helper helper;
        switch (type) {
            case "Happy":
                helper = new Happy(helperName);
                break;
            case "Sleepy":
                helper = new Sleepy(helperName);
                break;
            default:
                helper = null;
                break;
        }
        if(helper == null) {
            throw new IllegalArgumentException(HELPER_TYPE_DOESNT_EXIST);
        }
        this.helperRepository.add(helper);
        return String.format(ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Helper helper = this.helperRepository
                .getModels()
                .stream()
                .filter(h -> h.getName().equals(helperName))
                .findFirst()
                .orElse(null);
        if(helper == null) {
            throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
        }
        Instrument instrument = new InstrumentImpl(power);
        helper.addInstrument(instrument);
        return String.format(SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName, energyRequired);
        this.presentRepository.add(present);
        return String.format(SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        List<Helper> readyHelpers = this.helperRepository
                .getModels()
                .stream()
                .filter(h -> h.getEnergy() > 50)
                .collect(Collectors.toList());
        if(readyHelpers.size() == 0) {
            throw new IllegalArgumentException(NO_HELPER_READY);
        }
        Present present = this.presentRepository
                .getModels()
                .stream()
                .filter(p -> p.getName().equals(presentName))
                .findFirst()
                .orElse(null);
        Shop shop = new ShopImpl();
        for (Helper helper: readyHelpers) {
            shop.craft(present, helper);
        }

        String message = "not done";
        if(present.isDone()) {
            message = "done";
        }

        int brokenInstrumentsCount = 0;
        for (Helper helper: readyHelpers) {
            List<Instrument> instrumentsNotBroken = helper
                    .getInstruments()
                    .stream()
                    .filter(i -> i.isBroken())
                    .collect(Collectors.toList());
            brokenInstrumentsCount += instrumentsNotBroken.size();
        }
        if(present.isDone()) {
            this.craftedPresentsCount += 1;
        }
        return String.format(PRESENT_DONE, presentName, message) +
                String.format(COUNT_BROKEN_INSTRUMENTS, brokenInstrumentsCount);
    }

    @Override
    public String report() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%d presents are done!", this.craftedPresentsCount));
        builder.append(System.lineSeparator());
        builder.append("Helpers info:");
        builder.append(System.lineSeparator());
        for (Helper helper: this.helperRepository.getModels()) {
            builder.append(String.format("Name: %s", helper.getName()));
            builder.append(System.lineSeparator());
            builder.append(String.format("Energy: %d", helper.getEnergy()));
            builder.append(System.lineSeparator());
            int instrumentsCount = (int) helper
                    .getInstruments()
                    .stream()
                    .filter(i -> !i.isBroken()).count();
            builder.append(String.format("Instruments: %d not broken left", instrumentsCount));
            builder.append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
