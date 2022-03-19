package appenders;

import enums.ReportLevel;
import layouts.Layout;

import java.io.IOException;

public interface Appender {
    ReportLevel getReportLevel();
    void setReportLevel(ReportLevel reportLevel);
    void append(String dateTime, String reportLevel, String message) throws IOException;
    Layout getLayout();
    void setLayout(Layout layout);
    int getMessagesCount();
    void setMessagesCount(int messagesCount);
}
