package sng.testutils.testnglisteners;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.io.File;
import java.io.IOException;

@Component
public class SuiteListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        try {
            FileUtils.deleteDirectory(new File("./screenshots/"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
