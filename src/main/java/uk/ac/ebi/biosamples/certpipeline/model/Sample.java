package uk.ac.ebi.biosamples.certpipeline.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sample {

    private String accession;
    private String document;

    public Sample(String accession, String document) {
        this.accession = accession;
        this.document = document;
    }

    public Sample() {
    }

    public String getAccession() {
        return accession;
    }

    public String getDocument() {
        return document;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return getAccession();
    }
}
