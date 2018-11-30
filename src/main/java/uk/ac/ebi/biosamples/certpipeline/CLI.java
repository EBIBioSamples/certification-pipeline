package uk.ac.ebi.biosamples.certpipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import uk.ac.ebi.biosamples.certpipeline.service.Pipeline;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import static java.nio.file.Paths.get;

@Component
@ConditionalOnProperty(prefix = "job.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
public class CLI implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(Application.class);

    @Autowired
    private Pipeline pipeline;

    @Override
    public void run(String... args) throws Exception {
        pipeline.run(readFile(args[0], Charset.defaultCharset()));
    }

    static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(get(path));
        return new String(encoded, encoding);
    }
}
