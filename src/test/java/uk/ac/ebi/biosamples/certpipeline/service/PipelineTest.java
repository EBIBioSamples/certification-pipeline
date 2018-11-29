package uk.ac.ebi.biosamples.certpipeline.service;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.ac.ebi.biosamples.certpipeline.Application;
import uk.ac.ebi.biosamples.certpipeline.model.ChecklistMatches;
import uk.ac.ebi.biosamples.certpipeline.model.Sample;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PipelineTest {

    @Autowired
    private Pipeline pipeline;

    @Test
    public void given_ncbi_sample_run_pipeline() throws IOException {
        String data = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("json/ncbi-SAMN03894263.json"), "UTF8");
        ChecklistMatches checklistMatches = pipeline.run(data);
        System.out.println(checklistMatches.toString());
        assertTrue(checklistMatches.getChecklists().size()==1);
        assertEquals("ncbi-0.0.1", checklistMatches.getChecklists().get(0).getID());
    }
}
