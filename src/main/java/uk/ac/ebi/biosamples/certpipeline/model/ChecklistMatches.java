package uk.ac.ebi.biosamples.certpipeline.model;

import java.util.ArrayList;
import java.util.List;

public class ChecklistMatches {

    private final Sample sample;
    private final List<Checklist> checklists;

    public ChecklistMatches(Sample sample, List<Checklist> checklists) {
        this.sample = sample;
        this.checklists = checklists;
    }

    public Sample getSample() {
        return sample;
    }

    public List<Checklist> getChecklists() {
        return checklists;
    }
}
