package uk.ac.ebi.biosamples.certpipeline.model;

public class PlanResult {

    private final Sample sample;

    private final Plan plan;

    public PlanResult(Sample sample, Plan plan) {
        this.sample = sample;
        this.plan = plan;
    }

    public Sample getSample() {
        return sample;
    }

    public Plan getPlan() {
        return plan;
    }

    @Override
    public String toString() {
        return "PlanResult{" +
                "sample=" + sample +
                ", plan=" + plan +
                '}';
    }
}
