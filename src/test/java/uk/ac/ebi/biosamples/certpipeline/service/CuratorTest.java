package uk.ac.ebi.biosamples.certpipeline.service;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.ac.ebi.biosamples.certpipeline.Application;
import uk.ac.ebi.biosamples.certpipeline.model.Checklist;
import uk.ac.ebi.biosamples.certpipeline.model.InterrogationResult;
import uk.ac.ebi.biosamples.certpipeline.model.PlanResult;
import uk.ac.ebi.biosamples.certpipeline.model.Sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, properties = {"job.autorun.enabled=false"})
public class CuratorTest {

    @Autowired
    private Curator curator;

    @Test
    public void given_ChecklistMatches_run_curation_plans() throws Exception {
        String data = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("json/ncbi-SAMN03894263.json"), "UTF8");
        Sample sample = new Sample("test", data);
        List<Checklist> checklistList = new ArrayList<>();
        checklistList = Collections.singletonList(new Checklist("ncbi", "0.0.1", "schemas/ncbi-candidate-schema.json"));
        InterrogationResult interrogationResult = new InterrogationResult(sample, checklistList);
        PlanResult planResult = curator.runCurationPlans(interrogationResult);
        assertNotNull(planResult.getSample());
        assertFalse(planResult.getCurationResults().isEmpty());
        assertEquals("live", planResult.getCurationResults().get(0).getBefore());
        assertEquals("public", planResult.getCurationResults().get(0).getAfter());
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_null_checklistMatches_throw_exception() throws IOException {
        curator.runCurationPlans(null);
    }
}
