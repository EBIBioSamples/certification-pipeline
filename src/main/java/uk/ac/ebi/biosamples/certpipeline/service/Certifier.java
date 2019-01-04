package uk.ac.ebi.biosamples.certpipeline.service;

import org.everit.json.schema.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uk.ac.ebi.biosamples.certpipeline.model.*;

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

    public CertificationResult certify(PlanResult planResult) {
        if (planResult == null) {
            throw new IllegalArgumentException("cannot certify a null plan result");
        }
        CertificationResult certificationResult = new CertificationResult();
        for (Checklist checklist : configLoader.config.getChecklists()) {
            Sample sample = planResult.getSample();
            try {
                validator.validate(checklist.getFileName(), sample.getDocument());
                EVENTS.info(String.format("%s validation successful against %s", sample.getAccession(), checklist.getID()));
                certificationResult.add(new Certificate(sample, planResult.getCurationResults(), checklist));
                EVENTS.info(String.format("%s issued %s certificate", sample.getAccession(), checklist.getID()));
            } catch (IOException ioe) {
                LOG.error(String.format("cannot open schema at %s", checklist.getFileName()), ioe);
            } catch (ValidationException ve) {
                EVENTS.info(String.format("%s validation failed against %s", sample.getAccession(), checklist.getID(), ve.getMessage()));
            }
        }
        return certificationResult;
    }

}
