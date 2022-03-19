package loggers;

import appenders.Appender;
import appenders.ConsoleAppender;
import appenders.FileAppender;
import enums.ReportLevel;

import java.util.Arrays;
import java.util.List;

public class MessageLogger implements Logger{
    private List<Appender> appenders;

    public MessageLogger(Appender... appenders) {
        this.appenders = Arrays.asList(appenders);
    }

    @Override
    public void logInfo(String dateTime, String message) {
        log(ReportLevel.INFO.name(), dateTime, message);
    }

    @Override
    public void logWarning(String dateTime, String message) {
        log(ReportLevel.WARNING.name(), dateTime, message);
    }

    @Override
    public void logError(String dateTime, String message) {
        log(ReportLevel.ERROR.name(), dateTime, message);
    }

    @Override
    public void logCritical(String dateTime, String message) {
        log(ReportLevel.CRITICAL.name(), dateTime, message);
    }

    @Override
    public void logFatal(String dateTime, String message) {
        log(ReportLevel.FATAL.name(), dateTime, message);
    }

    private void log(String level, String dateTime, String message)  {
        for (Appender appender: this.appenders) {
            if(ReportLevel.valueOf(level).ordinal() >= appender.getReportLevel().ordinal()) {
                appender.append(dateTime, level, message);
                appender.setMessagesCount(appender.getMessagesCount() + 1);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Logger info");
        builder.append(System.lineSeparator());
        for (Appender ap: this.appenders) {
            if(ap instanceof ConsoleAppender) {
                builder.append(String.format("Appender type: %s, Layout type: %s, Report level: %s, Messages appended: %d,",
                        ap.getClass().getSimpleName(), ap.getLayout().getClass().getSimpleName(), ap.getReportLevel().name(), ap.getMessagesCount()));
                builder.append(System.lineSeparator());
            } else if (ap instanceof FileAppender) {
                builder.append(String.format("Appender type: %s, Layout type: %s, Report level: %s, Messages appended: %d, File size: %d",
                        ap.getClass().getSimpleName(), ap.getLayout().getClass().getSimpleName(), ap.getReportLevel().name(), ap.getMessagesCount(), ((FileAppender) ap).getFile().getSize()));
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}
