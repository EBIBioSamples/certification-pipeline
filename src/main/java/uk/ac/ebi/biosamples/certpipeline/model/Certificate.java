package uk.ac.ebi.biosamples.certpipeline.model;

public class Certificate {

    private final Sample sample;

    private final String sampleHash;

    private final Checklist checklist;

    private final String checklistHash;

    public Certificate(Sample sample, String sampleHash, Checklist checklist, String checklistHash) {
        this.sample = sample;
        this.sampleHash = sampleHash;
        this.checklist = checklist;
        this.checklistHash = checklistHash;
    }

    public Sample getSample() {
        return sample;
    }

    public String getSampleHash() {
        return sampleHash;
    }

    public Checklist getChecklist() {
        return checklist;
    }

    public String getChecklistHash() {
        return checklistHash;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "sample=" + sample +
                ", sampleHash='" + sampleHash + '\'' +
                ", checklist=" + checklist +
                ", checklistHash='" + checklistHash + '\'' +
                '}';
    }
}
