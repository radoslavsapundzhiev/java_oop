import appenders.Appender;
import appenders.ConsoleAppender;
import appenders.FileAppender;
import enums.ReportLevel;
import files.File;
import files.LogFile;
import layouts.Layout;
import layouts.SimpleLayout;
import layouts.XmlLayout;
import loggers.Logger;
import loggers.MessageLogger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int numberOfAppenders = Integer.parseInt(scanner.nextLine());
        List<Appender> appenders = new ArrayList<>();
        for (int i = 0; i < numberOfAppenders; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String appenderType = tokens[0];
            String layoutType = tokens[1];
            String reportLevel = null;
            Appender consoleAppender = null;
            Appender fileAppender = null;
            Layout simpleLayout = null;
            Layout xmlLayout = null;
            if(tokens.length == 3) {
                reportLevel = tokens[2];
            }

            if(layoutType.equals("SimpleLayout")) {
                simpleLayout = new SimpleLayout();
            } else if(layoutType.equals("XmlLayout")) {
                xmlLayout = new XmlLayout();
            }

            if(appenderType.equals("ConsoleAppender")) {
                if(simpleLayout != null) {
                    consoleAppender = new ConsoleAppender(simpleLayout);
                } else if(xmlLayout != null) {
                    consoleAppender = new ConsoleAppender(xmlLayout);
                }
                if(reportLevel != null) {
                    consoleAppender.setReportLevel(ReportLevel.valueOf(reportLevel));
                }
                appenders.add(consoleAppender);
            } else if(appenderType.equals("FileAppender")) {
                if(simpleLayout != null) {
                    fileAppender = new FileAppender(simpleLayout);
                } else if(xmlLayout != null) {
                    fileAppender = new FileAppender(xmlLayout);
                }

                File file = new LogFile();
                ((FileAppender) fileAppender).setFile(file);

                if(reportLevel != null) {
                    fileAppender.setReportLevel(ReportLevel.valueOf(reportLevel));
                }
                appenders.add(fileAppender);
            }
        }
        Appender[] appendersInput = new Appender[appenders.size()];
        for (int i = 0; i < appendersInput.length; i++) {
            appendersInput[i] = appenders.get(i);
        }
        Logger logger = new MessageLogger(appendersInput);
        String input = scanner.nextLine();
        while (!input.equals("END")) {
            String[] inputTokens = input.split("\\|");
            String reportLevel = inputTokens[0];
            String dateTime = inputTokens[1];
            String message = inputTokens[2];
            switch (reportLevel){
                case "INFO":
                    logger.logInfo(dateTime, message);
                    break;
                case "WARNING":
                    logger.logWarning(dateTime, message);
                    break;
                case "ERROR":
                    logger.logError(dateTime, message);
                    break;
                case "CRITICAL":
                    logger.logCritical(dateTime, message);
                    break;
                case "FATAL":
                    logger.logFatal(dateTime, message);
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.println(logger);
    }
}
