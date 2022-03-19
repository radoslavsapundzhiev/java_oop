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

public class Main {
    public static void main(String[] args) throws IOException {
        Layout xmlLayout = new XmlLayout();
        Appender consoleAppender = new ConsoleAppender(xmlLayout);

        File file = new LogFile();
        Appender fileAppender = new FileAppender(xmlLayout);
        ((FileAppender) fileAppender).setFile(file);

        Logger logger = new MessageLogger(consoleAppender, fileAppender);

        logger.logError("3/31/2015 5:33:07 PM", "Error parsing request");
        System.out.println(file.getSize());
        logger.logInfo("3/26/2015 2:08:11 PM", "User Pesho successfully registered.");

        System.out.println(file.getSize());
    }
}
