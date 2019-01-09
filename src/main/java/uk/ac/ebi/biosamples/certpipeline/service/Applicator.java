package uk.ac.ebi.biosamples.certpipeline.service;

import org.springframework.stereotype.Service;
import uk.ac.ebi.biosamples.certpipeline.model.CurationResult;
import uk.ac.ebi.biosamples.certpipeline.model.PlanResult;
import uk.ac.ebi.biosamples.certpipeline.model.Sample;

@Service
public class Applicator {

    public Sample apply(PlanResult planResult) {
        if (planResult == null) {
            throw new IllegalArgumentException("cannot apply a null plan result");
        }
        Sample sample = planResult.getSample();
        String document = sample.getDocument();
        String updatedDocument = document;
        for (CurationResult curationResult : planResult.getCurationResults()) {
            String toReplace = curationResult.getCharacteristic() + "\": [\n" +
                    "      {\n" +
                    "        \"text\": \"live\"\n" +
                    "      }";
            String replaceWith = curationResult.getCharacteristic() + "\": [\n" +
                    "      {\n" +
                    "        \"text\": \"" + curationResult.getAfter() + "\"\n" +
                    "      }";
            updatedDocument = updatedDocument.replace(toReplace, replaceWith);
        }
        Sample curatedSample = new Sample(sample.getAccession(), updatedDocument);
        return curatedSample;
    }
}
