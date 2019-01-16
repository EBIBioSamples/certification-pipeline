package uk.ac.ebi.biosamples.certpipeline.service;

import org.springframework.stereotype.Service;
import uk.ac.ebi.biosamples.certpipeline.model.CertificationResult;
import uk.ac.ebi.biosamples.certpipeline.model.PlanResult;
import uk.ac.ebi.biosamples.certpipeline.model.RecorderResult;
import uk.ac.ebi.biosamples.certpipeline.model.Sample;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class Pipeline {

    private Identifier identifier;

    private Interrogator interrogator;

    private Curator curator;

    private Certifier certifier;

    private Recorder recorder;

    public Pipeline(Certifier certifier, Curator curator, Identifier identifier, Interrogator interrogator, Recorder recorder) {
        this.certifier = certifier;
        this.curator = curator;
        this.identifier = identifier;
        this.interrogator = interrogator;
        this.recorder = recorder;
    }

    public RecorderResult run(String data) {
        Set<CertificationResult> certificationResults = new LinkedHashSet<>();
        Sample rawSample = identifier.identify(data);
        certificationResults.add(certifier.certify(rawSample));
        PlanResult planResult = curator.runCurationPlans(interrogator.interrogate(rawSample));
        if (planResult.curationsMade()) {
            certificationResults.add(certifier.certify(planResult));
        }
        return recorder.record(certificationResults);
    }
}
