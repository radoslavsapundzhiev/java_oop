package militaryElite;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] info = scanner.nextLine().split("\\s+");
        List<PrivateImpl> privates = new ArrayList<>();
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
                    break;
                case "Engineer":
                    double engineerSalary = Double.parseDouble(info[4]);
                    for (int i = 5; i < info.length; i += 2) {
                        //TO DO
                    }
                    break;
                case "Commando":
                    double commandoSalary = Double.parseDouble(info[4]);
                    Corp corp = Corp.valueOf(info[5]);
                    for (int i = 6; i < info.length; i += 2) {
                        //TO DO
                    }
                    break;
                case "Spy":
                    String codeNumber = info[4];
                    break;
            }
            info = scanner.nextLine().split("\\s+");
        }
    }
}
