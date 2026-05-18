package Utilis;

import java.io.*;
import java.util.Properties;

public class PropertiesUtils {

    public static Properties loadProperties(String FilePath){
        File file =new File(FilePath);
        try {
            InputStream inputStream=new FileInputStream(file);
            Properties properties=new Properties();
            properties.load(inputStream);
            inputStream.close();
            return properties;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File Not Found");
        } catch (IOException e) {
            throw new RuntimeException("Error While Loading Properties");
        }
    }
}
