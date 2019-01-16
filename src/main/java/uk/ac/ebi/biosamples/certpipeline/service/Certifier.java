package uk.ac.ebi.biosamples.certpipeline.service;

import org.everit.json.schema.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uk.ac.ebi.biosamples.certpipeline.model.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class Certifier {

    private static Logger LOG = LoggerFactory.getLogger(Certifier.class);
    private static Logger EVENTS = LoggerFactory.getLogger("events");

    private ConfigLoader configLoader;
    private Validator validator;
    private Applicator applicator;

    public Certifier(ConfigLoader configLoader, Validator validator, Applicator applicator) {
        this.validator = validator;
        this.configLoader = configLoader;
        this.applicator = applicator;
    }

    public CertificationResult certify(Sample sample) {
        if (sample == null) {
            throw new IllegalArgumentException("cannot certify a null sample");
        }
        return certify(sample, Collections.EMPTY_LIST);
    }

    private CertificationResult certify(Sample sample, List<CurationResult> curationResults) {
        CertificationResult certificationResult = new CertificationResult(sample.getAccession());
        for (Checklist checklist : configLoader.config.getChecklists()) {
            try {
                validator.validate(checklist.getFileName(), sample.getDocument());
                EVENTS.info(String.format("%s validation successful against %s", sample.getAccession(), checklist.getID()));
                certificationResult.add(new Certificate(sample, curationResults, checklist));
                EVENTS.info(String.format("%s issued %s certificate", sample.getAccession(), checklist.getID()));
            } catch (IOException ioe) {
                LOG.error(String.format("cannot open schema at %s", checklist.getFileName()), ioe);
            } catch (ValidationException ve) {
                EVENTS.info(String.format("%s validation failed against %s", sample.getAccession(), checklist.getID()));
            }
        }
        return certificationResult;
    }

    public CertificationResult certify(HasCuratedSample hasCuratedSample) {
        if (hasCuratedSample == null) {
            throw new IllegalArgumentException("cannot certify a null plan result");
        }
        return certify(applicator.apply(hasCuratedSample), hasCuratedSample.getCurationResults());
    }

}
