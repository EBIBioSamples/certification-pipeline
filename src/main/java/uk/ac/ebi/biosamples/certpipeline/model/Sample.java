package uk.ac.ebi.biosamples.certpipeline.model;

public class Sample {

    private final String accession;
    private final String document;

    public Sample(String accession, String document) {
        this.accession = accession;
        this.document = document;
    }

    public String getAccession() {
        return accession;
    }

    public String getDocument() {
        return document;
    }
}
