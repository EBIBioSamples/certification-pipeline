package uk.ac.ebi.biosamples.certpipeline.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Plan {

    @JsonProperty(value = "candidate_checklist_id")
    private String candidateChecklistID;

    @JsonProperty(value = "certificate_checklist_id")
    private String certificateChecklistID;

    private List<Curation> curations;


    public String getCandidateChecklistID() {
        return candidateChecklistID;
    }

    public void setCandidateChecklistID(String candidateChecklistID) {
        this.candidateChecklistID = candidateChecklistID;
    }

    public String getCertificateChecklistID() {
        return certificateChecklistID;
    }

    public void setCertificateChecklistID(String certificateChecklistID) {
        this.certificateChecklistID = certificateChecklistID;
    }

    public List<Curation> getCurations() {
        return curations;
    }

    public void setCurations(List<Curation> curations) {
        this.curations = curations;
    }

    public Sample applyCuration(Sample sample, Curation curation) {
        Sample curatedSample = sample;
        return curatedSample;
    }
}
