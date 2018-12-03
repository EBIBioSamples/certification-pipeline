package uk.ac.ebi.biosamples.certpipeline.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uk.ac.ebi.biosamples.certpipeline.model.Sample;

import java.io.IOException;
import java.util.UUID;

@Service
public class Identifier {

    private static Logger eventsLogger = LoggerFactory.getLogger("events");

    public Sample identify(String data) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            Sample sample = mapper.readValue(data, Sample.class);
            sample.setDocument(data);
            return sample;
        } catch (IOException e) {
            e.printStackTrace();
            String uuid = UUID.randomUUID().toString();
            eventsLogger.info("could not identify sample, assigned UUID %s", uuid);
            return new Sample(uuid, data);
        }
    }
}