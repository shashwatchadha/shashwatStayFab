package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;



public class LoadPropertiesFiles {
	
	
	
	
	private static String configPropertiesPath;
	private static FileInputStream fis;
	private static File file;
	public static Properties configProperties = new Properties();

	public static String getConfigPropertiesPath() {
		return configPropertiesPath;
	}

	public static  void setConfigPropertiesPath(String configPropertiesPath) {
		LoadPropertiesFiles.configPropertiesPath = configPropertiesPath;
	}




	public static Properties loadPropertiesFile()
	{
		 file = new File(LoadPropertiesFiles.getConfigPropertiesPath());
		
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			configProperties.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	return configProperties;
		
		
		
	}


}
