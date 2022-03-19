package loggers;

import appenders.Appender;

import java.io.IOException;
import java.util.List;

public interface Logger {
    void logInfo(String dateTime, String message) throws IOException;
    void logWarning(String dateTime, String message) throws IOException;
    void logError(String dateTime, String message) throws IOException;
    void logCritical(String dateTime, String message) throws IOException;
    void logFatal(String dateTime, String message) throws IOException;
    List<Appender> getAppenders();
}
