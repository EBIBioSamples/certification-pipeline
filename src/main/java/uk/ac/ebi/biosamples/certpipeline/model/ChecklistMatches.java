package uk.ac.ebi.biosamples.certpipeline.model;

public class ChecklistMatches {

    private final Sample sample;
    private final Checklist[] checklists;

    public ChecklistMatches(Sample sample, Checklist[] checklists) {
        this.sample = sample;
        this.checklists = checklists;
    }

    public Sample getSample() {
        return sample;
    }

    public Checklist[] getChecklists() {
        return checklists;
    }
}
