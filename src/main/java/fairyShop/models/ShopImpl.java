package fairyShop.models;

import javax.swing.*;
import java.util.Collection;

public class ShopImpl implements Shop{

    @Override
    public void craft(Present present, Helper helper) {

        if (helper.canWork() && helper.getInstruments().iterator().hasNext()){

            Collection<Instrument> instruments = helper.getInstruments();

            Instrument instrument = helper.getInstruments().iterator().next();

            while (!present.isDone() && helper.canWork()
                    && instruments.iterator().hasNext()){

                helper.work();
                instrument.use();
                present.getCrafted();

                if (instrument.isBroken() && instruments.iterator().hasNext()){
                    instruments.remove(instrument);
                    if (instruments.iterator().hasNext()){
                        instrument = instruments.iterator().next();
                    }else {
                        break;
                    }
                }else if (instrument.isBroken() && instruments.isEmpty()){
                    break;
                }

            }
        }
    }
}
