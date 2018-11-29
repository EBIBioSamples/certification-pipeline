package uk.ac.ebi.biosamples.certpipeline;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uk.ac.ebi.biosamples.certpipeline.model.Config;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
public class ConfigLoader {

    @Value("${pipeline.config}")
    private String configFile;

    public Config config;

    @PostConstruct
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Convert JSON string from file to Object
            config = mapper.readValue(this.getClass().getClassLoader().getResourceAsStream(configFile), Config.class);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
