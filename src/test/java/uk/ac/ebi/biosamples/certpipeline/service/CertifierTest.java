package uk.ac.ebi.biosamples.certpipeline.service;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.ac.ebi.biosamples.certpipeline.Application;
import uk.ac.ebi.biosamples.certpipeline.model.Certificate;
import uk.ac.ebi.biosamples.certpipeline.model.Plan;
import uk.ac.ebi.biosamples.certpipeline.model.PlanResult;
import uk.ac.ebi.biosamples.certpipeline.model.Sample;

import java.util.Collections;

import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CertifierTest {

    @Autowired
    private Certifier certifier;

    @Test
    public void given_valid_plan_result_issue_certificate() throws Exception {
        String data = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("json/ncbi-SAMN03894263-curated.json"), "UTF8");
        Sample sample = new Sample("test-uuid", data);
        Plan plan = new Plan("ncbi-0.0.1", "biosamples-0.0.1", Collections.EMPTY_LIST);
        PlanResult planResult = new PlanResult(sample, plan);
        Certificate certificate = certifier.certify(planResult);
        assertNotNull(certificate);
    }
}
