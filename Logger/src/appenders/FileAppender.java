package appenders;

import enums.ReportLevel;
import files.File;
import layouts.Layout;

public class FileAppender implements Appender{
    private Layout layout;
    private File file;
    private ReportLevel reportLevel;
    private int messageCount;
    public FileAppender(Layout layout) {
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
        return messageCount;
    }

    @Override
    public void setMessagesCount(int messagesCount) {
        this.messageCount = messagesCount;
    }

    public void setFile(File logFile) {
        this.file = logFile;
    }

    public File getFile() {
        return file;
    }

    @Override
    public ReportLevel getReportLevel() {
        return reportLevel;
    }

    @Override
    public void setReportLevel(ReportLevel reportLevel) {
        this.reportLevel = reportLevel;
    }

    @Override
    public void append(String dateTime, String reportLevel, String message) {
        file.write(layout.format(dateTime, reportLevel, message));
    }
}
