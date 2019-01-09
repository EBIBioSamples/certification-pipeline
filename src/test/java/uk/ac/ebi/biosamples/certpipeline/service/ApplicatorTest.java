package uk.ac.ebi.biosamples.certpipeline.service;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.ac.ebi.biosamples.certpipeline.Application;
import uk.ac.ebi.biosamples.certpipeline.model.*;

import java.io.IOException;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, properties = {"job.autorun.enabled=false"})
public class ApplicatorTest {

    @Autowired
    private Applicator applicator;

    @Test
    public void given_valid_plan_result_apply_curations() throws Exception {
        String data = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("json/ncbi-SAMN03894263.json"), "UTF8");
        String curatedData = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("json/ncbi-SAMN03894263-curated.json"), "UTF8");
        Sample sample = new Sample("test", data);
        Sample curatedSample = new Sample("test", curatedData);
        Curation curation = new Curation("INSDC status", "public");
        Plan plan = new Plan("ncbi-0.0.1", "biosamples-0.0.1", Collections.singletonList(curation));
        PlanResult planResult = new PlanResult(sample, plan);
        planResult.addCurationResult(new CurationResult(curation.getCharacteristic(), "live", curation.getValue()));
        assertEquals(curatedSample.getDocument(), applicator.apply(planResult).getDocument());
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_null_planResult_throw_exception() throws IOException {
        applicator.apply(null);
    }
}
