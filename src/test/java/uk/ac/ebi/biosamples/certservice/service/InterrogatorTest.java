package uk.ac.ebi.biosamples.certservice.service;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.ac.ebi.biosamples.certservice.Application;
import uk.ac.ebi.biosamples.certservice.model.InterrogationResult;
import uk.ac.ebi.biosamples.certservice.model.Sample;

import java.io.IOException;
import java.util.Collections;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, properties = {"job.autorun.enabled=false"})
public class InterrogatorTest {

    @Autowired
    private Interrogator interrogator;

    @Autowired
    private ConfigLoader configLoader;

    @Test
    public void given_ncbi_sample_return_ncbi_checklist_as_a_match() throws IOException {
        String data = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("json/ncbi-SAMN03894263.json"), "UTF8");
        Sample sample = new Sample("test-uuid", data);
        InterrogationResult interrogationResult = interrogator.interrogate(sample);
        assertTrue(interrogationResult.getChecklists().size()==1);
        assertEquals("ncbi-0.0.1", interrogationResult.getChecklists().get(0).getID());
    }

    @Test
    public void given_empty_sample_return_empty_matches() {
        Sample sample = new Sample("test-uuid", "{}");
        InterrogationResult interrogationResult = interrogator.interrogate(sample);
        assertEquals(Collections.EMPTY_LIST, interrogationResult.getChecklists());
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_null_sample_throw_exception() throws IOException {
        interrogator.interrogate(null);
    }
}
