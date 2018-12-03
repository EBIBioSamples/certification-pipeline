package uk.ac.ebi.biosamples.certpipeline.model;

public class CurationResult {

    private final String characteristic;

    private final String before;

    private final String after;

    public CurationResult(String characteristic, String before, String after) {
        this.characteristic = characteristic;
        this.before = before;
        this.after = after;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public String getBefore() {
        return before;
    }

    public String getAfter() {
        return after;
    }

}
