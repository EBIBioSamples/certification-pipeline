package uk.ac.ebi.biosamples.certpipeline.service;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.ac.ebi.biosamples.certpipeline.Application;
import uk.ac.ebi.biosamples.certpipeline.ConfigLoader;
import uk.ac.ebi.biosamples.certpipeline.model.Checklist;
import uk.ac.ebi.biosamples.certpipeline.model.ChecklistMatches;
import uk.ac.ebi.biosamples.certpipeline.model.Sample;

import java.io.IOException;
import java.util.Collections;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class InterrogatorTest {

    @Autowired
    private Interrogator interrogator;

    @Autowired
    private ConfigLoader configLoader;

    @Test
    public void given_ncbi_sample_return_ncbi_checklist_as_a_match() throws IOException {
        String data = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("json/ncbi-SAMN03894263.json"), "UTF8");
        Sample sample = new Sample("test-uuid", data);
        ChecklistMatches checklistMatches = interrogator.interrogate(sample);
        assertTrue(checklistMatches.getChecklists().size()==1);
        assertEquals("ncbi-0.0.1", checklistMatches.getChecklists().get(0).getID());
    }

    @Test
    public void given_empty_sample_return_empty_matches() {
        Sample sample = new Sample("test-uuid", "{}");
        ChecklistMatches checklistMatches = interrogator.interrogate(sample);
        assertEquals(Collections.EMPTY_LIST, checklistMatches.getChecklists());
    }
}
