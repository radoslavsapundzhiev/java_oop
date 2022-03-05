package telephony;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Smartphone implements Callable, Browsable{
    private List<String> numbers;
    private List<String> urls;
    private String number;
    private String url;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String call() {
        StringBuilder builder = new StringBuilder();
        for (String number: numbers) {
            Pattern pattern = Pattern.compile("^\\d+$");
            Matcher matcher = pattern.matcher(number);
            if(!matcher.find()){
                builder
                        .append("Invalid number!")
                        .append(System.lineSeparator());
                continue;
            }
            builder
                    .append(String.format("Calling... %s", number))
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }

    @Override
    public String browse() {
        StringBuilder builder = new StringBuilder();
        for (String url: urls) {
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(url);
            if(matcher.find()){
                builder
                        .append("Invalid URL!")
                        .append(System.lineSeparator());
                continue;
            }
            builder
                    .append(String.format("Browsing: %s!", url))
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }
}
