package example.testtask.townsandroads3;

import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by alexandr on 26.04.16.
 */
public class Config {
    private static Properties logProperty = new Properties();
    private static String logFile;

    @SuppressWarnings("static-access")
    public Config(String logFile){
        this.logFile = logFile;
    }

    public void init(){
        try{
            logProperty.load(new FileInputStream(logFile));
            PropertyConfigurator.configure(logProperty);
        } catch (IOException ie){
            ie.printStackTrace();
        }
    }
}
