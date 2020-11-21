package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {
    Properties properties;
    FileInputStream fis;

    private String initPropertyFile(String key){
        try {
            fis = new FileInputStream("C:\\Users\\ACER\\IdeaProjects\\WebDriverAutomation\\src\\config\\object.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("- Properties file not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }

    public String getValue(String key){
        return initPropertyFile(key);
    }
}
