package uk.ac.ebi.biosamples.certpipeline.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uk.ac.ebi.biosamples.certpipeline.model.Sample;

import java.io.IOException;
import java.util.UUID;

@Service
public class Identifier {

    public Sample identify(String data) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Sample sample = mapper.readValue(data, Sample.class);
            sample.setDocument(data);
            return sample;
        } catch (IOException e) {
            e.printStackTrace();
            return new Sample(UUID.randomUUID().toString(), data);
        }
    }
}