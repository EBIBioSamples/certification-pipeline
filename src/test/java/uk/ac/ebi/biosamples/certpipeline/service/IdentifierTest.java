package uk.ac.ebi.biosamples.certpipeline.service;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.ac.ebi.biosamples.certpipeline.Application;
import uk.ac.ebi.biosamples.certpipeline.model.Sample;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, properties = {"job.autorun.enabled=false"})
public class IdentifierTest {

    @Autowired
    private Identifier identifier;

    @Test
    public void given_ncbi_sample_return_sample() throws IOException {
        String data = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("json/ncbi-SAMN03894263.json"), "UTF8");
        Sample sample = identifier.identify(data);
        assertTrue(sample.getAccession().matches("SAM[END][AG]?[0-9]+"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_null_sample_throw_exception() throws IOException {
        identifier.identify(null);
    }

}
