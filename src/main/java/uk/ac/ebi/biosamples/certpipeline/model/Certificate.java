package uk.ac.ebi.biosamples.certpipeline.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

public class Certificate {

    private final Sample sample;

    private final List<CurationResult> curationsResults;

    private final Checklist checklist;

    @JsonPropertyOrder({"sample", "curationsResults", "checklist"})
    public Certificate(Sample sample, List<CurationResult> curationsResults, Checklist checklist) {
        this.sample = sample;
        this.curationsResults = curationsResults;
        this.checklist = checklist;
    }

    public Sample getSample() {
        return sample;
    }

    public Checklist getChecklist() {
        return checklist;
    }

    @JsonProperty(value = "curations")
    public List<CurationResult> getCurationsResults() {
        return curationsResults;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "sample=" + sample +
                ", curationsResults=" + curationsResults +
                ", checklist=" + checklist +
                '}';
    }
}
