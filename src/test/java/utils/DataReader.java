package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataReader {
	public String readPropertyFile(String key) {
		Properties prop = new Properties();
		BufferedReader dataReader = null;
		final String propFilePath = "resources/application.properties";

		try {
			//dataReader = new BufferedReader(new FileReader("application.properties"));
			dataReader = new BufferedReader(new FileReader(propFilePath));
			prop.load(dataReader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty(key);

	}

}
