package uk.ac.ebi.biosamples.certpipeline.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Plan {

    @JsonProperty(value = "candidate_checklist_id")
    private String candidateChecklistID;

    @JsonProperty(value = "certification_checklist_id")
    private String certificationChecklistID;

    private List<Curation> curations;

    public Plan(String candidateChecklistID, String certificationChecklistID, List<Curation> curations) {
        this.candidateChecklistID = candidateChecklistID;
        this.certificationChecklistID = certificationChecklistID;
        this.curations = curations;
    }

    private Plan() {

    }

    public String getCandidateChecklistID() {
        return candidateChecklistID;
    }

    public void setCandidateChecklistID(String candidateChecklistID) {
        this.candidateChecklistID = candidateChecklistID;
    }

    public String getCertificationChecklistID() {
        return certificationChecklistID;
    }

    public void setCertificationChecklistID(String certificationChecklistID) {
        this.certificationChecklistID = certificationChecklistID;
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

    @Override
    public String toString() {
        return "Plan{" +
                "candidateChecklistID='" + candidateChecklistID + '\'' +
                ", certificationChecklistID='" + certificationChecklistID + '\'' +
                '}';
    }
}
