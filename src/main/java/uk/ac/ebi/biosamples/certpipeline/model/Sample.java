package uk.ac.ebi.biosamples.certpipeline.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.util.DigestUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sample {

    private String accession;

    private String document;

    private String hash;

    public Sample(String accession, String document) {
        this.accession = accession;
        this.document = document;
        this.hash = DigestUtils.md5DigestAsHex(this.document.getBytes()).toUpperCase();
    }

    public Sample() {
    }

    public String getAccession() {
        return accession;
    }

    @JsonIgnore
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
        return "Sample{" +
                "accession='" + accession + '\'' +
                ", document='" + document + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }

    public String getHash() {
        return hash;
    }
}
