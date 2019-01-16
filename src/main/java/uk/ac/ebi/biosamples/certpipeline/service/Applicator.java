package uk.ac.ebi.biosamples.certpipeline.service;

import org.springframework.stereotype.Service;
import uk.ac.ebi.biosamples.certpipeline.model.CurationResult;
import uk.ac.ebi.biosamples.certpipeline.model.HasCuratedSample;
import uk.ac.ebi.biosamples.certpipeline.model.Sample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class Applicator {

    public Sample apply(HasCuratedSample curationApplicable) {
        if (curationApplicable == null) {
            throw new IllegalArgumentException("cannot apply a null curation applyable");
        }
        Sample sample = curationApplicable.getSample();
        String document = sample.getDocument();
        String updatedDocument = document;
        for (CurationResult curationResult : curationApplicable.getCurationResults()) {
            String pattern = String.format("\\\"%s\\\"\\s?[:]\\s?\\[\\W+?text\\\"\\s?[:]\\s?\\s\\\"(%s)\\\"", curationResult.getCharacteristic(), curationResult.getBefore());
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(updatedDocument);
            if (m.find()) {
                updatedDocument = updatedDocument.replace(m.group(1), curationResult.getAfter());
            }
        }
        Sample curatedSample = new Sample(sample.getAccession(), updatedDocument);
        return curatedSample;
    }
}
