package uk.ac.ebi.biosamples.certpipeline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.ebi.biosamples.certpipeline.model.ChecklistMatches;
import uk.ac.ebi.biosamples.certpipeline.model.PlanResult;

import java.util.List;

@Service
public class Pipeline {

    private Identifier identifier;

    private Interrogator interrogator;

    private Curator curator;

    public Pipeline(Curator curator, Identifier identifier, Interrogator interrogator) {
        this.curator = curator;
        this.identifier = identifier;
        this.interrogator = interrogator;
    }

    public List<PlanResult> run(String data) {
        return curator.runCurationPlans(interrogator.interrogate(identifier.identify(data)));
    }
}
