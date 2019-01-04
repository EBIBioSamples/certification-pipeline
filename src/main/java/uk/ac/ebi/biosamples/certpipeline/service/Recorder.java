package uk.ac.ebi.biosamples.certpipeline.service;

import uk.ac.ebi.biosamples.certpipeline.model.CertificationResult;
import uk.ac.ebi.biosamples.certpipeline.model.RecorderResult;

public interface Recorder {

    RecorderResult record(CertificationResult certificationResult);

}
