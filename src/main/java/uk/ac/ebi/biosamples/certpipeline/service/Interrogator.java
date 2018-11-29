package uk.ac.ebi.biosamples.certpipeline.service;

import org.springframework.stereotype.Service;
import uk.ac.ebi.biosamples.certpipeline.ConfigLoader;
import uk.ac.ebi.biosamples.certpipeline.model.Checklist;
import uk.ac.ebi.biosamples.certpipeline.model.ChecklistMatches;
import uk.ac.ebi.biosamples.certpipeline.model.Sample;

@Service
public class Interrogator {

    private ConfigLoader configLoader;
    private Validator validator;

    public Interrogator(ConfigLoader configLoader, Validator validator) {
        this.validator = validator;
        this.configLoader = configLoader;
    }

    public ChecklistMatches interrogate(Sample sample) {
        return new ChecklistMatches(sample, new Checklist[]{});
    }
}
