package uk.ac.ebi.biosamples.certpipeline.service;

import org.everit.json.schema.ValidationException;
import org.springframework.stereotype.Service;
import uk.ac.ebi.biosamples.certpipeline.model.Certificate;
import uk.ac.ebi.biosamples.certpipeline.model.Checklist;
import uk.ac.ebi.biosamples.certpipeline.model.PlanResult;
import uk.ac.ebi.biosamples.certpipeline.model.Sample;

import java.io.IOException;

@Service
public class Certifier {

    private ConfigLoader configLoader;
    private Validator validator;

    public Certifier(ConfigLoader configLoader, Validator validator) {
        this.validator = validator;
        this.configLoader = configLoader;
    }

    public Certificate certify(PlanResult planResult) {
        for (Checklist checklist : configLoader.config.getChecklists()) {
            try {
                Sample sample = planResult.getSample();
                validator.validate(checklist.getFileName(), sample.getDocument());
                Certificate certificate = new Certificate(sample, "", checklist, "");
                return certificate;
            } catch (IOException ioe) {

            } catch (ValidationException ve) {
                ve.printStackTrace();
            }
        }
        return null;
    }

}
