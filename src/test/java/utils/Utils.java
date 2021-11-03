package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

	//Read the property from configuration.properties file
	public static String readProperty(String key) {
		String value = "";
		try (InputStream input = new FileInputStream("./src/test/resources/data/configuration.properties")) {

			Properties prop = new Properties();
			// load a properties file
			prop.load(input);

			value = prop.getProperty(key);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return value;
	}

}
