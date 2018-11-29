package uk.ac.ebi.biosamples.certpipeline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.ebi.biosamples.certpipeline.model.ChecklistMatches;

@Service
public class Pipeline {

    private Identifier identifier;

    private Interrogator interrogator;

    public Pipeline(Identifier identifier, Interrogator interrogator) {
        this.identifier = identifier;
        this.interrogator = interrogator;
    }

    public ChecklistMatches run(String data) {
        return interrogator.interrogate(identifier.identify(data));
    }
}
