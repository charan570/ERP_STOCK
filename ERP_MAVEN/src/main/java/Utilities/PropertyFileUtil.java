package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtil {
	public static String getValueForKey(String key) throws Throwable
	{
		Properties configprop=new Properties();
		                        // here We are reusing the methods (setting)
		FileInputStream fis=new FileInputStream("D:\\Charan\\ERP_MAVEN\\PropertyFile\\Environment.properties");

		
		// here we are loading  the values (values)
		 configprop.load(fis);
		 return configprop.getProperty(key);
		 
		
	}

}
