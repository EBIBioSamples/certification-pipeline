package uk.ac.ebi.biosamples.certservice.model;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SampleTest {

    @Test
    public void ensure_sample_has_a_hash() throws Exception {
        String data = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("json/ncbi-SAMN03894263-curated.json"), "UTF8");
        Sample sample = new Sample("test-uuid", data);
        assertEquals(32, sample.getHash().length());
    }

}
