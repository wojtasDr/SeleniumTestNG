package sng.testutils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateTimeUtils {
    @Value("${screenshot.timestamp.format}")
    private String dateTimeFormat;

    @Autowired
    public DateTimeUtils() {
    }

    public String timestamp() {
        return new SimpleDateFormat(dateTimeFormat).format(new Date());
    }

}
