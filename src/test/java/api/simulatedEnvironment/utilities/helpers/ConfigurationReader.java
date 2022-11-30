package api.simulatedEnvironment.utilities.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/** reads the properties */
public class ConfigurationReader {

  private static Properties properties;

  static {
    try {
      String path =
          "src"
              + File.separator
              + "test"
              + File.separator
              + "resources"
              + File.separator
              + "config.properties";
      FileInputStream input = new FileInputStream(path);
      properties = new Properties();
      properties.load(input);
      input.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String get(String keyName) {
    return properties.getProperty(keyName);
  }

  public static void set(String keyName, String value) {
    properties.setProperty(keyName, value);
  }
}
