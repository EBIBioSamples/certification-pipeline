package uk.ac.ebi.biosamples.certpipeline.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import uk.ac.ebi.biosamples.certpipeline.model.Sample;

import java.io.IOException;
import java.util.UUID;

@Component
public class Identifier {

    public Sample identify(String data) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(data, Sample.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new Sample(UUID.randomUUID().toString(), data);
        }
    }
}