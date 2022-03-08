package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
    Properties pro;

    public ReadConfig(String file){
        File src = new File("./Configuration/"+file+".properties");

        try{
            FileInputStream fis = new FileInputStream(src);
            pro = new Properties();
            pro.load(fis);
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
        }
    }

    public String getChromePath() {
        return pro.getProperty("chromepath");
    }

    public String getFFPath() {
        return pro.getProperty("firefoxpath");
    }

    public String getBrowser() {
        return pro.getProperty("browser");
    }

    public String getURL(){
        String env = pro.getProperty("Environment");
        String path;

        path = pro.getProperty("Test");

        return path;
    }
}