package uk.ac.ebi.biosamples.certpipeline.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.ac.ebi.biosamples.certpipeline.Application;
import uk.ac.ebi.biosamples.certpipeline.ConfigLoader;
import uk.ac.ebi.biosamples.certpipeline.model.Config;
import uk.ac.ebi.biosamples.certpipeline.model.Checklist;
import uk.ac.ebi.biosamples.certpipeline.model.ChecklistMatches;
import uk.ac.ebi.biosamples.certpipeline.model.Sample;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class InterrogatorTest {

    @Autowired
    private Interrogator interrogator;

    @Autowired
    private ConfigLoader configLoader;

    @Test
    public void given_ncbi_sample_return_ncbi_checklist_as_a_match() {
        Sample sample = new Sample("test-uuid", "");
        ChecklistMatches checklistMatches = interrogator.interrogate(sample);
        Assert.assertArrayEquals(new Checklist[]{}, checklistMatches.getChecklists());
    }

    @Test
    public void given_empty_sample_return_empty_matches() {
        Sample sample = new Sample("test-uuid", "");
        ChecklistMatches checklistMatches = interrogator.interrogate(sample);
        Assert.assertArrayEquals(new Checklist[]{}, checklistMatches.getChecklists());
    }
}
