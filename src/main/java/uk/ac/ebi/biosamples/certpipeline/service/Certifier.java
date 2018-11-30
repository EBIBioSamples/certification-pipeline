package uk.ac.ebi.biosamples.certpipeline.service;

import org.everit.json.schema.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uk.ac.ebi.biosamples.certpipeline.model.Certificate;
import uk.ac.ebi.biosamples.certpipeline.model.Checklist;
import uk.ac.ebi.biosamples.certpipeline.model.PlanResult;
import uk.ac.ebi.biosamples.certpipeline.model.Sample;

import java.io.IOException;

@Service
public class Certifier {

    private static Logger LOG = LoggerFactory.getLogger(Certifier.class);
    private static Logger EVENTS = LoggerFactory.getLogger("events");

    private ConfigLoader configLoader;
    private Validator validator;

    public Certifier(ConfigLoader configLoader, Validator validator) {
        this.validator = validator;
        this.configLoader = configLoader;
    }

    public Certificate certify(PlanResult planResult) {
        for (Checklist checklist : configLoader.config.getChecklists()) {
            Sample sample = planResult.getSample();
            try {
                validator.validate(checklist.getFileName(), sample.getDocument());
                Certificate certificate = new Certificate(sample, "", checklist, "");
                return certificate;
            } catch (IOException ioe) {
                LOG.error(String.format("cannot open schema at %s", checklist.getFileName()), ioe);
            } catch (ValidationException ve) {
                EVENTS.info(String.format("validation failed for %s against %s", sample.getAccession(), checklist.getID(), ve.getMessage()));
            }
        }
        return null;
    }

}
