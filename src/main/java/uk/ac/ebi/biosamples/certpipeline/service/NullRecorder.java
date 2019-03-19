package uk.ac.ebi.biosamples.certpipeline.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uk.ac.ebi.biosamples.certpipeline.model.Certificate;
import uk.ac.ebi.biosamples.certpipeline.model.CertificationResult;
import uk.ac.ebi.biosamples.certpipeline.model.RecorderResult;

import java.util.Set;

@Service
public class NullRecorder implements Recorder {

    private static Logger LOG = LoggerFactory.getLogger(NullRecorder.class);
    private static Logger EVENTS = LoggerFactory.getLogger("events");

    @Override
    public RecorderResult record(Set<CertificationResult> certificationResults) {
        RecorderResult recorderResult = new RecorderResult();
        if (certificationResults == null) {
            throw new IllegalArgumentException("cannot record a null certification result");
        }
        for (CertificationResult certificationResult : certificationResults) {
            for (Certificate certificate : certificationResult.getCertificates()) {
                recorderResult.add(certificate);
                try {
                    //simulate a write with a short pause
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    LOG.warn("pause interupted", e);
                }
                EVENTS.info(String.format("%s recorded %s certificate", certificate.getSample().getAccession(), certificate.getChecklist().getID()));
            }
        }
        return recorderResult;
    }

}
