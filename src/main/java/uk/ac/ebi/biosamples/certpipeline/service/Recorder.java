package uk.ac.ebi.biosamples.certpipeline.service;

import uk.ac.ebi.biosamples.certpipeline.model.CertificationResult;
import uk.ac.ebi.biosamples.certpipeline.model.RecorderResult;

import java.util.Set;

public interface Recorder {

    RecorderResult record(Set<CertificationResult> certificationResult);

}
