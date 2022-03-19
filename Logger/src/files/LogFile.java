package files;

public class LogFile implements File{
    private StringBuilder content;

    public LogFile() {
        this.content = new StringBuilder();
    }
    @Override
    public void write(String line) {
        this.content.append(line);
    }

    @Override
    public int getSize() {
        int sum = 0;
        for (Character ch: this.content.toString().toCharArray()) {
            if(Character.isAlphabetic(ch)) {
                sum += ch;
            }
        }
        return sum;
    }
}
