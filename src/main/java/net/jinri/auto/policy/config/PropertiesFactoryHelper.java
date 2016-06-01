package net.jinri.auto.policy.config;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFactoryHelper
{
    private static PropertiesFactoryHelper _instance = null;
    private Properties properties = new Properties();

    private PropertiesFactoryHelper() {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("core.properties");
            properties.load(inputStream);
            if (inputStream != null)
                inputStream.close();
        } catch (Exception e) {
            System.out.println(e + "file not found");
        }
    }
   
    synchronized public static PropertiesFactoryHelper getInstance() {
        if (_instance == null) {
            _instance = new PropertiesFactoryHelper();
        }
        return _instance;
    }

    public PropertiesFactoryHelper clone() {
        return getInstance();
    }

    public String getConfig(String key) {
        return properties.getProperty(key);
    }

}
