package militaryElite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] info = scanner.nextLine().split("\\s+");
        List<PrivateImpl> privates = new ArrayList<>();
        List<SoldierImpl> soldiers = new ArrayList<>();
        while (!info[0].equals("End")) {
            String type = info[0];
            int id = Integer.parseInt(info[1]);
            String firstName = info[2];
            String lastName = info[3];
            switch (type){
                case "Private":
                    double privateSalary = Double.parseDouble(info[4]);
                    PrivateImpl priv = new PrivateImpl(id, firstName, lastName, privateSalary);
                    privates.add(priv);
                    soldiers.add(priv);
                    break;
                case "LieutenantGeneral":
                    double lieutenantGeneralSalary = Double.parseDouble(info[4]);
                    LieutenantGeneralImpl lieutenantGeneral = new LieutenantGeneralImpl(id, firstName, lastName, lieutenantGeneralSalary);
                    for (int i = 5; i < info.length; i++) {
                        int currentPrivateId = Integer.parseInt(info[i]);
                        for (PrivateImpl pr: privates) {
                            if(pr.getId() == currentPrivateId) {
                                lieutenantGeneral.addPrivate(pr);
                                break;
                            }
                        }
                    }
                    soldiers.add(lieutenantGeneral);
                    break;
                case "Engineer":
                    double engineerSalary = Double.parseDouble(info[4]);
                    if(!corpIsValid(info[5])) {
                        break;
                    }
                    Corps engineerCorps = Corps.valueOf(info[5]);
                    EngineerImpl engineer = new EngineerImpl(id, firstName, lastName, engineerSalary, engineerCorps);
                    for (int i = 6; i < info.length; i += 2) {
                        String partName = info[i];
                        int hoursWorked = Integer.parseInt(info[i + 1]);
                        Repair repair = new Repair(partName, hoursWorked);
                        engineer.addRepair(repair);
                    }
                    soldiers.add(engineer);
                    break;
                case "Commando":
                    double commandoSalary = Double.parseDouble(info[4]);
                    if(!corpIsValid(info[5])) {
                        break;
                    }
                    Corps commandoCorps = Corps.valueOf(info[5]);
                    CommandoImpl commando = new CommandoImpl(id, firstName, lastName, commandoSalary, commandoCorps);
                    for (int i = 6; i < info.length; i += 2) {
                        if(!stateIsValid(info[i + 1])) {
                            continue;
                        }
                        String codeName = info[i];
                        State state = State.valueOf(info[i + 1]);
                        Mission mission = new Mission(codeName, state);
                        commando.addMission(mission);
                    }
                    soldiers.add(commando);
                    break;
                case "Spy":
                    String codeNumber = info[4];
                    SpyImpl spy = new SpyImpl(id, firstName, lastName, codeNumber);
                    soldiers.add(spy);
                    break;
            }
            info = scanner.nextLine().split("\\s+");
        }
        for (SoldierImpl soldier: soldiers) {
            System.out.println(soldier);
        }
    }

    private static boolean stateIsValid(String stateString) {
        for (State state: State.values()) {
            if(state.name().equals(stateString)) {
                return true;
            }
        }
        return false;
    }

    private static boolean corpIsValid(String corpString) {
        for (Corps corp: Corps.values()) {
            if(corp.name().equals(corpString)) {
                return true;
            }
        }
        return false;
    }
}
