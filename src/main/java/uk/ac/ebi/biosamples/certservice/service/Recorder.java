package uk.ac.ebi.biosamples.certservice.service;

import uk.ac.ebi.biosamples.certservice.model.CertificationResult;
import uk.ac.ebi.biosamples.certservice.model.RecorderResult;

import java.util.Set;

public interface Recorder {

    RecorderResult record(Set<CertificationResult> certificationResult);

}
