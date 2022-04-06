package aquarium.core;

import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.aquariums.FreshwaterAquarium;
import aquarium.entities.aquariums.SaltwaterAquarium;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.decorations.Ornament;
import aquarium.entities.decorations.Plant;
import aquarium.entities.fish.Fish;
import aquarium.entities.fish.FreshwaterFish;
import aquarium.entities.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;
import aquarium.repositories.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{
    private Repository decorations;
    private Collection<Aquarium> aquariums;

    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new ArrayList<>();
    }
    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        Aquarium aquarium;
        switch (aquariumType) {
            case "FreshwaterAquarium":
                aquarium = new FreshwaterAquarium(aquariumName);
                break;
            case "SaltwaterAquarium":
                aquarium = new SaltwaterAquarium(aquariumName);
                break;
            default:
                aquarium = null;
                break;
        }
        if(aquarium == null) {
            throw new NullPointerException(INVALID_AQUARIUM_TYPE);
        }
        aquariums.add(aquarium);
        return String.format(SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        Decoration decoration;
        switch (type) {
            case "Ornament":
                decoration = new Ornament();
                break;
            case "Plant":
                decoration = new Plant();
                break;
            default:
                decoration = null;
                break;
        }
        if(decoration == null) {
            throw new IllegalArgumentException(INVALID_DECORATION_TYPE);
        }
        decorations.add(decoration);
        return String.format(SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        Decoration decoration = decorations.findByType(decorationType);
        if(decoration == null) {
            throw new IllegalArgumentException(String.format(NO_DECORATION_FOUND, decorationType));
        }
        Aquarium aquarium = aquariums
                .stream()
                .filter(a -> a.getName().equals(aquariumName))
                .findFirst()
                .orElse(null);
        if(aquarium != null) {
            aquarium.addDecoration(decoration);
            decorations.remove(decoration);
        }
        return String.format(SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquarium.getName());
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {
        Fish fish;
        switch (fishType) {
            case "FreshwaterFish":
                fish = new FreshwaterFish(fishName, fishSpecies, price);
                break;
            case "SaltwaterFish":
                fish = new SaltwaterFish(fishName, fishSpecies, price);
                break;
            default:
                fish = null;
                break;
        }
        if(fish == null) {
            throw new IllegalArgumentException(INVALID_FISH_TYPE);
        }
        Aquarium aquarium = aquariums
                .stream()
                .filter(a -> a.getName().equals(aquariumName))
                .findFirst()
                .orElse(null);
        try {
            aquarium.addFish(fish);
        } catch (IllegalStateException e) {
            return e.getMessage();
        }

        boolean canLive = false;
        if((aquarium.getClass().getSimpleName().startsWith("Freshwater") && fishType.startsWith("Freshwater")) ||
            aquarium.getClass().getSimpleName().startsWith("Saltwater") && fishType.startsWith("Saltwater")) {
            canLive = true;
        }

        if(!canLive) {
            return WATER_NOT_SUITABLE;
        }

        return String.format(SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
    }

    @Override
    public String feedFish(String aquariumName) {
        Aquarium aquarium = aquariums
                .stream()
                .filter(a -> a.getName().equals(aquariumName))
                .findFirst()
                .orElse(null);
        aquarium.feed();
        Collection<Fish> fedFish = aquarium.getFish()
                .stream()
                .filter(f -> f.getSize() != 0)
                .collect(Collectors.toList());
        return String.format(FISH_FED, fedFish.size());
    }

    @Override
    public String calculateValue(String aquariumName) {
        Aquarium aquarium = aquariums
                .stream()
                .filter(a -> a.getName().equals(aquariumName))
                .findFirst()
                .orElse(null);
        double fishPrice = aquarium.getFish().stream().mapToDouble(Fish::getPrice).sum();
        double decorationsPrice = aquarium.getDecorations().stream().mapToDouble(Decoration::getPrice).sum();
        double totalSum = fishPrice + decorationsPrice;
        return String.format(VALUE_AQUARIUM, aquariumName, totalSum);
    }

    @Override
    public String report() {
        StringBuilder builder = new StringBuilder();
        for (Aquarium aquarium: aquariums) {
            builder.append(String.format("%s (%s):", aquarium.getName(), aquarium.getClass().getSimpleName()));
            builder.append(System.lineSeparator());
            if(aquarium.getFish().size() == 0) {
                builder.append("Fish: none");
            } else {
                String fishAsString = aquarium.getFish().stream().map(Fish::getName).collect(Collectors.joining(" "));
                builder.append(String.format("Fish: %s", fishAsString));
            }
            builder.append(System.lineSeparator());
            builder.append(String.format("Decorations: %d", aquarium.getDecorations().size()));
            builder.append(System.lineSeparator());
            builder.append(String.format("Comfort: %d", aquarium.calculateComfort()));
        }
        return builder.toString();
    }


}
