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

    public List<PlanResult> runCurationPlans(ChecklistMatches checklistMatches) {
        List<PlanResult> planResults = new ArrayList<>();
        if (checklistMatches.getChecklists().isEmpty()) {
            return planResults;
        }
        for (Checklist checklist : checklistMatches.getChecklists()) {
            planResults.add(runCurationPlan(checklist, checklistMatches.getSample()));
        }
        return planResults;
    }

    private PlanResult runCurationPlan(Checklist checklist, Sample sample) {
        if (plansByCandidateChecklistID.containsKey(checklist.getID())) {
            Plan plan = plansByCandidateChecklistID.get(checklist.getID());
            Sample curatedSample = sample;
            for (Curation curation : plan.getCurations()) {
                curatedSample = plan.applyCuration(curatedSample, curation);
            }
            return new PlanResult(curatedSample, plan);
        }
        return new PlanResult(sample, null);
    }

    @PostConstruct
    public void init() {
        for (Plan plan : configLoader.config.getPlans()) {
            plansByCandidateChecklistID.put(plan.getCandidateChecklistID(), plan);
        }
    }
}