package fairyShop.core;

import fairyShop.common.ConstantMessages;
import fairyShop.common.ExceptionMessages;
import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller{

    private HelperRepository helperRepository;
    private PresentRepository presentRepository;
    private int donePresents;
    private int totalInstruments;
    private int brokenInstruments;

    public ControllerImpl() {
        this.helperRepository = new HelperRepository();
        this.presentRepository = new PresentRepository();
        this.donePresents = 0;
        this.totalInstruments = 0;
        this.brokenInstruments = 0;
    }

    public int getCurrentTotalInstruments() {
        int currentInstruments = 0;
        for (Helper helper: this.helperRepository.getModels()){
            for (Instrument i : helper.getInstruments()){
                currentInstruments++;
            }
        }
        return currentInstruments;
    }

    public int getBrokenInstruments() {
        return brokenInstruments = this.totalInstruments - getCurrentTotalInstruments();
    }

    @Override
    public String addHelper(String type, String helperName) {

        Helper helper;
        switch (type){
            case "Happy":
                helper = new Happy(helperName);
                break;
            case "Sleepy":
                helper = new Sleepy(helperName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.HELPER_TYPE_DOESNT_EXIST);
        }
        this.helperRepository.add(helper);
        return String.format(ConstantMessages.ADDED_HELPER,type,helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {

        Instrument instrument = new InstrumentImpl(power);

        if (this.helperRepository.findByName(helperName) != null){
            this.helperRepository.findByName(helperName).addInstrument(instrument);
        }else {
            throw new IllegalArgumentException(ExceptionMessages.HELPER_DOESNT_EXIST);
        }
        this.totalInstruments++;
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER,power,helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName,energyRequired);

        this.presentRepository.add(present);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PRESENT,presentName);
    }

    @Override
    public String craftPresent(String presentName) {


        Shop shop = new ShopImpl();

        Present present = this.presentRepository.findByName(presentName);
        List<Helper> helperList = new ArrayList<>();

        if (this.helperRepository.getModels().isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.NO_HELPER_READY);
        }

        for (Helper helper : this.helperRepository.getModels()){
            if (helper.getEnergy() > 50){
                helperList.add(helper);
            }
        }

        if (helperList.isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.NO_HELPER_READY);
        }
        for (Helper h : helperList){

            if (!h.getInstruments().isEmpty()){
                shop.craft(present,h);
                if (present.isDone()){
                    break;
                }
            }
        }
        StringBuilder str = new StringBuilder();

        if (present.isDone()){
            this.donePresents++;
            str.append(String.format(ConstantMessages.PRESENT_DONE,present.getName(),"done"));
        }else {
            str.append(String.format(ConstantMessages.PRESENT_DONE,present.getName(),"not done"));
        }

        str.append(String.format(ConstantMessages.COUNT_BROKEN_INSTRUMENTS,this.getBrokenInstruments()));
        return str.toString().trim();
    }

    @Override
    public String report() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("%d presents are done!",this.donePresents));
        str.append(System.lineSeparator());
        str.append("Helpers info:");
        str.append(System.lineSeparator());

        for (Helper h : this.helperRepository.getModels()){
            str.append(String.format("Name: %s",h.getName()));
            str.append(System.lineSeparator());
            str.append(String.format("Energy: %d",h.getEnergy()));
            str.append(System.lineSeparator());
            str.append(String.format("Instruments: %d not broken left",h.getInstruments().size()));
            str.append(System.lineSeparator());
        }
        return str.toString().trim();
    }
}
