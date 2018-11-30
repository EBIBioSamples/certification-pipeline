package uk.ac.ebi.biosamples.certpipeline.model;

public class PlanResult {

    final Sample sample;

    final Plan plan;

    public PlanResult(Sample sample, Plan plan) {
        this.sample = sample;
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "PlanResult{" +
                "sample=" + sample +
                ", plan=" + plan +
                '}';
    }
}
