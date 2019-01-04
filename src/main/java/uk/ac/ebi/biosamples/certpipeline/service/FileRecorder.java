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

    @Override
    public RecorderResult record(CertificationResult certificationResult) {
        RecorderResult recorderResult = new RecorderResult();
        if (certificationResult == null) {
            throw new IllegalArgumentException("cannot record a null certification result");
        }
        for (Certificate certificate : certificationResult.getCertificates()) {
            LOG.info("recorded: " + certificate.toString());
            recorderResult.add(certificate);
        }
        List<CurationResult> curations = Collections.EMPTY_LIST;
        return recorderResult;
    }
}
