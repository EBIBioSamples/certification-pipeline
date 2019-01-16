package uk.ac.ebi.biosamples.certpipeline.service;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.ac.ebi.biosamples.certpipeline.Application;
import uk.ac.ebi.biosamples.certpipeline.model.RecorderResult;

import java.io.IOException;

import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, properties = {"job.autorun.enabled=false"})
public class PipelineTest {

    @Autowired
    private Pipeline pipeline;

    @Test
    public void given_ncbi_sample_run_pipeline_for_SAMN03894263() throws IOException {
        String data = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("json/ncbi-SAMN03894263.json"), "UTF8");
        RecorderResult rr = pipeline.run(data);
        assertFalse(rr.getCertificates().isEmpty());
    }

    @Test
    public void given_ncbi_sample_run_pipeline_for_SAMN03894261() throws IOException {
        String data = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("json/ncbi-SAMN03894261.json"), "UTF8");
        RecorderResult rr = pipeline.run(data);
        assertFalse(rr.getCertificates().isEmpty());
    }
}
