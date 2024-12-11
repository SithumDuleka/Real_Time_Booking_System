package ticketbooking.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ConfigurationController {
    public ObjectMapper objectMapper;
    public ConfigurationController(){
         objectMapper=new ObjectMapper();
    }
    public void writeAll(Configuration config){
        try {
            objectMapper.writeValue(new File("Configfile.json"),config);
            System.out.println("Configuration is sucessfull ");
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("An error occurred while writing the configuration.");
        }

    }
}
