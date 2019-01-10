package uk.ac.ebi.biosamples.certpipeline.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uk.ac.ebi.biosamples.certpipeline.model.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Curator {

    private static Logger EVENTS = LoggerFactory.getLogger("events");

    private ConfigLoader configLoader;

    private Map<String, Plan> plansByCandidateChecklistID = new HashMap<>();

    public Curator(ConfigLoader configLoader) {
        this.configLoader = configLoader;
    }

    public PlanResult runCurationPlans(InterrogationResult interrogationResult) {
        if (interrogationResult == null) {
            throw new IllegalArgumentException("cannot run curation plans on null checklist matches");
        }
        List<PlanResult> planResults = new ArrayList<>();
        if (interrogationResult.getChecklists().isEmpty()) {
            return null;
        }
        for (Checklist checklist : interrogationResult.getChecklists()) {
            PlanResult planResult = runCurationPlan(checklist, interrogationResult.getSample());
            if (planResult != null) {
                planResults.add(planResult);
            }
        }
        return planResults.get(0);
    }

    private PlanResult runCurationPlan(Checklist checklist, Sample sample) {
        Plan plan = plansByCandidateChecklistID.get(checklist.getID());
        PlanResult planResult = new PlanResult(sample, plan);
        if (plansByCandidateChecklistID.containsKey(checklist.getID())) {
            for (Curation curation : plan.getCurations()) {
                CurationResult curationResult = plan.applyCuration(sample, curation);
                if (curationResult != null) {
                    planResult.addCurationResult(curationResult);
                }
            }
        }
        EVENTS.info(String.format("%s plan %s run", sample.getAccession(), plan.getID()));
        return planResult;
    }

    @PostConstruct
    public void init() {
        for (Plan plan : configLoader.config.getPlans()) {
            plansByCandidateChecklistID.put(plan.getCandidateChecklistID(), plan);
        }
    }
}
