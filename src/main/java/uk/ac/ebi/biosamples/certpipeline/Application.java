package uk.ac.ebi.biosamples.certpipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import uk.ac.ebi.biosamples.certpipeline.service.Pipeline;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import static java.nio.file.Paths.get;

@SpringBootApplication
public class Application {

    private static Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
