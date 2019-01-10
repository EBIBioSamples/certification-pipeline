package uk.ac.ebi.biosamples.certpipeline.service;

import org.everit.json.schema.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uk.ac.ebi.biosamples.certpipeline.model.Checklist;
import uk.ac.ebi.biosamples.certpipeline.model.InterrogationResult;
import uk.ac.ebi.biosamples.certpipeline.model.Sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class Interrogator {

    private static Logger LOG = LoggerFactory.getLogger(Certifier.class);
    private static Logger EVENTS = LoggerFactory.getLogger("events");

    private ConfigLoader configLoader;
    private Validator validator;

    public Interrogator(ConfigLoader configLoader, Validator validator) {
        this.validator = validator;
        this.configLoader = configLoader;
    }

    public InterrogationResult interrogate(Sample sample) {
        if (sample == null) {
            throw new IllegalArgumentException("cannot interrogate a null sample");
        }
        List<Checklist> matches = new ArrayList<>();
        for (Checklist checklist : configLoader.config.getChecklists()) {
            try {
                validator.validate(checklist.getFileName(), sample.getDocument());
                EVENTS.info(String.format("%s interrogation successful against %s", sample.getAccession(), checklist.getID()));
                matches.add(checklist);
            } catch (IOException ioe) {
                LOG.error(String.format("cannot open schema at %s", checklist.getFileName()), ioe);
            } catch (ValidationException ve) {
                EVENTS.info(String.format("%s interrogation failed against %s", sample.getAccession(), checklist.getID()));
            }
        }
        return new InterrogationResult(sample, matches);
    }
}
