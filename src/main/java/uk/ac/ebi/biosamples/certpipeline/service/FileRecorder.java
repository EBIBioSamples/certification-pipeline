package uk.ac.ebi.biosamples.certpipeline.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uk.ac.ebi.biosamples.certpipeline.model.Certificate;
import uk.ac.ebi.biosamples.certpipeline.model.CertificationResult;
import uk.ac.ebi.biosamples.certpipeline.model.CurationResult;
import uk.ac.ebi.biosamples.certpipeline.model.RecorderResult;

import java.util.Collections;
import java.util.List;

@Service
public class FileRecorder implements Recorder {

    private static Logger LOG = LoggerFactory.getLogger(FileRecorder.class);
    private static Logger EVENTS = LoggerFactory.getLogger("events");

    @Override
    public RecorderResult record(CertificationResult certificationResult) {
        RecorderResult recorderResult = new RecorderResult();
        if (certificationResult == null) {
            throw new IllegalArgumentException("cannot record a null certification result");
        }
        for (Certificate certificate : certificationResult.getCertificates()) {
            EVENTS.info(String.format("%s recorded %s certificate", certificate.getSample().getAccession(), certificate.getChecklist().getID()));
            recorderResult.add(certificate);
        }
        List<CurationResult> curations = Collections.EMPTY_LIST;
        return recorderResult;
    }
}
