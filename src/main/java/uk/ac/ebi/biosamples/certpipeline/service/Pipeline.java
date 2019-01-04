package uk.ac.ebi.biosamples.certpipeline.service;

import org.springframework.stereotype.Service;
import uk.ac.ebi.biosamples.certpipeline.model.RecorderResult;

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
        return recorder.record(certifier.certify(curator.runCurationPlans(interrogator.interrogate(identifier.identify(data)))));
    }
}
