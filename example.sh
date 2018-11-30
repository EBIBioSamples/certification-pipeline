#!/usr/bin/env bash
curl https://www.ebi.ac.uk/biosamples/samples/SAMN03894263.json -o in.json && java -jar ./build/libs/certification-pipeline-0.0.1-SNAPSHOT.jar in.json && rm in.json