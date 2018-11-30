package uk.ac.ebi.biosamples.certpipeline;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, properties = {"job.autorun.enabled=false"})
public class ApplicationTest {

    @Test
    public void contextLoads() {
    }

}
