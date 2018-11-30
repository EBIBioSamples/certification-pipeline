package uk.ac.ebi.biosamples.certpipeline.service;

import org.springframework.stereotype.Service;
import uk.ac.ebi.biosamples.certpipeline.model.Certificate;

@Service
public class Pipeline {

    private Identifier identifier;

    private Interrogator interrogator;

    private Curator curator;

    private Certifier certifier;

    public Pipeline(Certifier certifier, Curator curator, Identifier identifier, Interrogator interrogator) {
        this.certifier = certifier;
        this.curator = curator;
        this.identifier = identifier;
        this.interrogator = interrogator;
    }

    public Certificate run(String data) {
        return certifier.certify(curator.runCurationPlans(interrogator.interrogate(identifier.identify(data))));
    }
}
