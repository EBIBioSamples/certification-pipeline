package uk.ac.ebi.biosamples.certpipeline.service;

import org.springframework.stereotype.Service;
import uk.ac.ebi.biosamples.certpipeline.model.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Curator {

    private ConfigLoader configLoader;

    private Map<String, Plan> plansByCandidateChecklistID = new HashMap<>();

    public Curator(ConfigLoader configLoader) {
        this.configLoader = configLoader;
    }

    public PlanResult runCurationPlans(ChecklistMatches checklistMatches) {
        if (checklistMatches == null) {
            throw new IllegalArgumentException("cannot run curation plans on null checklist matches");
        }
        List<PlanResult> planResults = new ArrayList<>();
        if (checklistMatches.getChecklists().isEmpty()) {
            return null;
        }
        for (Checklist checklist : checklistMatches.getChecklists()) {
            PlanResult planResult = runCurationPlan(checklist, checklistMatches.getSample());
            if (planResult != null) {
                planResults.add(planResult);
            }
        }
        return planResults.get(0);
    }

    private PlanResult runCurationPlan(Checklist checklist, Sample sample) {
        if (plansByCandidateChecklistID.containsKey(checklist.getID())) {
            Plan plan = plansByCandidateChecklistID.get(checklist.getID());
            PlanResult planResult = new PlanResult(sample, plan);
            for (Curation curation : plan.getCurations()) {
                CurationResult curationResult = plan.applyCuration(sample, curation);
                if (curationResult != null) {
                    planResult.addCurationResult(curationResult);
                }
            }
            return planResult;
        }
        return null;
    }

    @PostConstruct
    public void init() {
        for (Plan plan : configLoader.config.getPlans()) {
            plansByCandidateChecklistID.put(plan.getCandidateChecklistID(), plan);
        }
    }
}
