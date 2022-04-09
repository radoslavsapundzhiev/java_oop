package fairyShop.models;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public class ShopImpl implements Shop{
    @Override
    public void craft(Present present, Helper helper) {
        Collection<Instrument> instruments = helper.getInstruments();
        Deque<Instrument> instrumentsQueue = new ArrayDeque<>(instruments);
        Instrument instrument = instrumentsQueue.poll();
        while (helper.canWork() && instrument != null && !present.isDone()) {
            if (!instrument.isBroken()) {
                instrument.use();
                helper.work();
                present.getCrafted();
            } else {
                instrument = instrumentsQueue.poll();
            }
        }
    }
}
