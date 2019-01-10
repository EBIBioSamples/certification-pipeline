#!/usr/bin/env bash
./gradlew build
curl https://www.ebi.ac.uk/biosamples/samples/SAMN03894261.json -o in.json && java -jar ./build/libs/certification-pipeline-0.0.1-SNAPSHOT.jar in.json && rm in.json
