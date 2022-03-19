package appenders;

import enums.ReportLevel;
import layouts.Layout;

public class ConsoleAppender implements Appender{
    private Layout layout;
    private ReportLevel reportLevel;
    private int messagesCount;
    public ConsoleAppender(Layout layout) {
        this.layout = layout;
        this.reportLevel = ReportLevel.INFO;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    @Override
    public int getMessagesCount() {
        return messagesCount;
    }

    @Override
    public void setMessagesCount(int messagesCount) {
        this.messagesCount = messagesCount;
    }

    public ReportLevel getReportLevel() {
        return reportLevel;
    }

    public void setReportLevel(ReportLevel reportLevel) {
        this.reportLevel = reportLevel;
    }

    @Override
    public void append(String dateTime, String reportLevel, String message) {
        System.out.println(layout.format(dateTime, reportLevel, message));
    }
}
