package uk.ac.ebi.biosamples.certpipeline.model;

import java.util.ArrayList;
import java.util.List;

public class CertificationResult {

    private final List<Certificate> certificates = new ArrayList<>();

    public void add(Certificate certificate) {
        certificates.add(certificate);
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

}
