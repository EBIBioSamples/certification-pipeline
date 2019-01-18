package uk.ac.ebi.biosamples.certpipeline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.ebi.biosamples.certpipeline.model.RecorderResult;
import uk.ac.ebi.biosamples.certpipeline.service.Pipeline;

@RestController
public class PipelineController {

    @Autowired
    private Pipeline pipeline;

    @PostMapping("/")
    public RecorderResult submitToPipeline(@RequestBody String body) {
        return pipeline.run(body);
    }

}
