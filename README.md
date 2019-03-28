# BioSamples Certification Service
Service for certifying BioSamples

[![Build Status][1]][2]
[![codecov][3]][4]
[![Maintainability][5]][6]
[![Docker Repository on Quay][7]][8]

A project for the automatic curation, validation and certification of BioSamples.

![Certification Service Flow](images/cert-service.png?raw=true "Certification Service Flow")


[1]: https://travis-ci.org/EBIBioSamples/certification-service.svg?branch=master
[2]: https://travis-ci.org/EBIBioSamples/certification-service-java
[3]: https://codecov.io/gh/EBIBioSamples/certification-service/branch/master/graph/badge.svg
[4]: https://codecov.io/gh/EBIBioSamples/certification-service
[5]: https://api.codeclimate.com/v1/badges/f149ddce987ecfe9948f/maintainability
[6]: https://codeclimate.com/github/EBIBioSamples/certification-service/maintainability
[7]: https://quay.io/repository/ebibiosamples/certification-service/status
[8]: https://quay.io/repository/ebibiosamples/certification-service

## Usages

Post a BioSample json to the /certify endpoint.
```
curl -X POST \
  http://wp-np2-40.ebi.ac.uk:8090/biosamples/certification/certify \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: a1968434-4fd1-49cb-ad5e-e9bd32950a90' \
  -H 'cache-control: no-cache' \
  -d '{
  "name": "CVM N48676",
  "accession": "SAMN03894263",
  "domain": "self.BiosampleImportNCBI",
  "release": "2015-08-10T06:44:40.400Z",
  "update": "2016-02-19T12:22:48.062Z",
  "taxId": 1620419,
  "characteristics": {
    "AssemblyVersion": [
      {
        "text": "CLC Genomics Workbench v. 6.0.2"
      }
    ],
    "INSDC center name": [
      {
        "text": "FDA"
      }
    ],
    "INSDC first public": [
      {
        "text": "2015-08-10T06:44:40.400Z"
      }
    ],
    "INSDC last update": [
      {
        "text": "2016-02-19T12:22:48.062Z"
      }
    ],
    "INSDC secondary accession": [
      {
        "text": "SRS1102063"
      }
    ],
    "INSDC status": [
      {
        "text": "live"
      }
    ],
    "NCBI submission model": [
      {
        "text": "Pathogen.env"
      },
      {
        "text": "environmental/food/other"
      }
    ],
    "NCBI submission package": [
      {
        "text": "Pathogen.env.1.0"
      }
    ],
    "SequencingTechnology": [
      {
        "text": "Illumina MiSeq"
      }
    ],
    "collected by": [
      {
        "text": "FDA"
      }
    ],
    "collection date": [
      {
        "text": "2013"
      }
    ],
    "description title": [
      {
        "text": "Pathogen: environmental/food/other sample from Salmonella enterica subsp. enterica serovar Typhimurium var. 5-"
      }
    ],
    "geographic location": [
      {
        "text": "USA: CT"
      }
    ],
    "isolation source": [
      {
        "text": "Chicken Breast"
      }
    ],
    "latitude and longitude": [
      {
        "text": "missing"
      }
    ],
    "organism": [
      {
        "text": "Salmonella enterica subsp. enterica serovar Typhimurium var. 5-",
        "ontologyTerms": [
          "http://purl.obolibrary.org/obo/NCBITaxon_1620419"
        ]
      }
    ],
    "serovar": [
      {
        "text": "Typhimurium var. 5-"
      }
    ],
    "strain": [
      {
        "text": "CVM N48676"
      }
    ]
  },
  "releaseDate": "2015-08-10",
  "updateDate": "2016-02-19",
  "data": [
    {
      "type": "AMR",
      "schema": "test",
      "content": [
        {
          "species": "Salmonella enterica subsp. enterica serovar Typhimurium var. 5-",
          "antibiotic_name": "cefoxitin",
          "ast_standard": "CLSI",
          "breakpoint_version": null,
          "laboratory_typing_method": "MIC",
          "measurement_units": "mg/L",
          "measurement_sign": "==",
          "resistance_phenotype": "resistant",
          "platform": "",
          "vendor": "Trek",
          "laboratory_typing_method_version_or_reagent": "96-Well Plate",
          "dst_media": null,
          "dst_method": null,
          "critical_concentration": null,
          "measurement": "32"
        },
        {
          "species": "Salmonella enterica subsp. enterica serovar Typhimurium var. 5-",
          "antibiotic_name": "gentamicin",
          "ast_standard": "CLSI",
          "breakpoint_version": null,
          "laboratory_typing_method": "MIC",
          "measurement_units": "mg/L",
          "measurement_sign": "==",
          "resistance_phenotype": "susceptible",
          "platform": "",
          "vendor": "Trek",
          "laboratory_typing_method_version_or_reagent": "96-Well Plate",
          "dst_media": null,
          "dst_method": null,
          "critical_concentration": null,
          "measurement": "1"
        },
        {
          "species": "Salmonella enterica subsp. enterica serovar Typhimurium var. 5-",
          "antibiotic_name": "trimethoprim-sulfamethoxazole",
          "ast_standard": "CLSI",
          "breakpoint_version": null,
          "laboratory_typing_method": "MIC",
          "measurement_units": "mg/L",
          "measurement_sign": "<=",
          "resistance_phenotype": "susceptible",
          "platform": "",
          "vendor": "Trek",
          "laboratory_typing_method_version_or_reagent": "96-Well Plate",
          "dst_media": null,
          "dst_method": null,
          "critical_concentration": null,
          "measurement": "0.12/2.38"
        },
        {
          "species": "Salmonella enterica subsp. enterica serovar Typhimurium var. 5-",
          "antibiotic_name": "ceftriaxone",
          "ast_standard": "CLSI",
          "breakpoint_version": null,
          "laboratory_typing_method": "MIC",
          "measurement_units": "mg/L",
          "measurement_sign": "==",
          "resistance_phenotype": "resistant",
          "platform": "",
          "vendor": "Trek",
          "laboratory_typing_method_version_or_reagent": "96-Well Plate",
          "dst_media": null,
          "dst_method": null,
          "critical_concentration": null,
          "measurement": "8"
        },
        {
          "species": "Salmonella enterica subsp. enterica serovar Typhimurium var. 5-",
          "antibiotic_name": "ciprofloxacin",
          "ast_standard": "CLSI",
          "breakpoint_version": null,
          "laboratory_typing_method": "MIC",
          "measurement_units": "mg/L",
          "measurement_sign": "<=",
          "resistance_phenotype": "susceptible",
          "platform": "",
          "vendor": "Trek",
          "laboratory_typing_method_version_or_reagent": "96-Well Plate",
          "dst_media": null,
          "dst_method": null,
          "critical_concentration": null,
          "measurement": "0.015"
        },
        {
          "species": "Salmonella enterica subsp. enterica serovar Typhimurium var. 5-",
          "antibiotic_name": "chloramphenicol",
          "ast_standard": "CLSI",
          "breakpoint_version": null,
          "laboratory_typing_method": "MIC",
          "measurement_units": "mg/L",
          "measurement_sign": "==",
          "resistance_phenotype": "susceptible",
          "platform": "",
          "vendor": "Trek",
          "laboratory_typing_method_version_or_reagent": "96-Well Plate",
          "dst_media": null,
          "dst_method": null,
          "critical_concentration": null,
          "measurement": "4"
        },
        {
          "species": "Salmonella enterica subsp. enterica serovar Typhimurium var. 5-",
          "antibiotic_name": "tetracycline",
          "ast_standard": "CLSI",
          "breakpoint_version": null,
          "laboratory_typing_method": "MIC",
          "measurement_units": "mg/L",
          "measurement_sign": ">",
          "resistance_phenotype": "resistant",
          "platform": "",
          "vendor": "Trek",
          "laboratory_typing_method_version_or_reagent": "96-Well Plate",
          "dst_media": null,
          "dst_method": null,
          "critical_concentration": null,
          "measurement": "32"
        },
        {
          "species": "Salmonella enterica subsp. enterica serovar Typhimurium var. 5-",
          "antibiotic_name": "nalidixic acid",
          "ast_standard": "CLSI",
          "breakpoint_version": null,
          "laboratory_typing_method": "MIC",
          "measurement_units": "mg/L",
          "measurement_sign": "==",
          "resistance_phenotype": "susceptible",
          "platform": "",
          "vendor": "Trek",
          "laboratory_typing_method_version_or_reagent": "96-Well Plate",
          "dst_media": null,
          "dst_method": null,
          "critical_concentration": null,
          "measurement": "4"
        },
        {
          "species": "Salmonella enterica subsp. enterica serovar Typhimurium var. 5-",
          "antibiotic_name": "streptomycin",
          "ast_standard": "missing",
          "breakpoint_version": null,
          "laboratory_typing_method": "MIC",
          "measurement_units": "mg/L",
          "measurement_sign": "<=",
          "resistance_phenotype": "susceptible",
          "platform": "",
          "vendor": "Trek",
          "laboratory_typing_method_version_or_reagent": "96-Well Plate",
          "dst_media": null,
          "dst_method": null,
          "critical_concentration": null,
          "measurement": "32"
        },
        {
          "species": "Salmonella enterica subsp. enterica serovar Typhimurium var. 5-",
          "antibiotic_name": "sulfisoxazole",
          "ast_standard": "CLSI",
          "breakpoint_version": null,
          "laboratory_typing_method": "MIC",
          "measurement_units": "mg/L",
          "measurement_sign": ">",
          "resistance_phenotype": "resistant",
          "platform": "",
          "vendor": "Trek",
          "laboratory_typing_method_version_or_reagent": "96-Well Plate",
          "dst_media": null,
          "dst_method": null,
          "critical_concentration": null,
          "measurement": "256"
        },
        {
          "species": "Salmonella enterica subsp. enterica serovar Typhimurium var. 5-",
          "antibiotic_name": "ampicillin",
          "ast_standard": "CLSI",
          "breakpoint_version": null,
          "laboratory_typing_method": "MIC",
          "measurement_units": "mg/L",
          "measurement_sign": ">",
          "resistance_phenotype": "resistant",
          "platform": "",
          "vendor": "Trek",
          "laboratory_typing_method_version_or_reagent": "96-Well Plate",
          "dst_media": null,
          "dst_method": null,
          "critical_concentration": null,
          "measurement": "32"
        },
        {
          "species": "Salmonella enterica subsp. enterica serovar Typhimurium var. 5-",
          "antibiotic_name": "azithromycin",
          "ast_standard": "missing",
          "breakpoint_version": null,
          "laboratory_typing_method": "MIC",
          "measurement_units": "mg/L",
          "measurement_sign": "==",
          "resistance_phenotype": "susceptible",
          "platform": "",
          "vendor": "Trek",
          "laboratory_typing_method_version_or_reagent": "96-Well Plate",
          "dst_media": null,
          "dst_method": null,
          "critical_concentration": null,
          "measurement": "2"
        },
        {
          "species": "Salmonella enterica subsp. enterica serovar Typhimurium var. 5-",
          "antibiotic_name": "ceftiofur",
          "ast_standard": "CLSI",
          "breakpoint_version": null,
          "laboratory_typing_method": "MIC",
          "measurement_units": "mg/L",
          "measurement_sign": "==",
          "resistance_phenotype": "resistant",
          "platform": "",
          "vendor": "Trek",
          "laboratory_typing_method_version_or_reagent": "96-Well Plate",
          "dst_media": null,
          "dst_method": null,
          "critical_concentration": null,
          "measurement": "8"
        },
        {
          "species": "Salmonella enterica subsp. enterica serovar Typhimurium var. 5-",
          "antibiotic_name": "kanamycin",
          "ast_standard": "CLSI",
          "breakpoint_version": null,
          "laboratory_typing_method": "MIC",
          "measurement_units": "mg/L",
          "measurement_sign": "<=",
          "resistance_phenotype": "susceptible",
          "platform": "",
          "vendor": "Trek",
          "laboratory_typing_method_version_or_reagent": "96-Well Plate",
          "dst_media": null,
          "dst_method": null,
          "critical_concentration": null,
          "measurement": "8"
        },
        {
          "species": "Salmonella enterica subsp. enterica serovar Typhimurium var. 5-",
          "antibiotic_name": "amoxicillin-clavulanic acid",
          "ast_standard": "CLSI",
          "breakpoint_version": null,
          "laboratory_typing_method": "MIC",
          "measurement_units": "mg/L",
          "measurement_sign": ">",
          "resistance_phenotype": "resistant",
          "platform": "",
          "vendor": "Trek",
          "laboratory_typing_method_version_or_reagent": "96-Well Plate",
          "dst_media": null,
          "dst_method": null,
          "critical_concentration": null,
          "measurement": "32/16"
        }
      ]
    }
  ]
}'
```
This will respond with a array of certificates which say any checklist the sample validates against and any checklist the sample would validate against with given curations.

In this case it passes validation against an NCBI checklist and will validate against BioSamples if a curation is added to change NCBI Status from live to public:

```
{
    "certificates": [
        {
            "sample": {
                "accession": "SAMN03894263",
                "hash": "8A1DFE8BCAA7B988B23F3D92BA684881"
            },
            "checklist": {
                "name": "ncbi",
                "version": "0.0.1",
                "file": "schemas/ncbi-candidate-schema.json"
            },
            "curations": []
        },
        {
            "sample": {
                "accession": "SAMN03894263",
                "hash": "2790CDCFF7248C7306CD0D9AE6133364"
            },
            "checklist": {
                "name": "biosamples",
                "version": "0.0.1",
                "file": "schemas/biosamples-basic.json"
            },
            "curations": [
                {
                    "characteristic": "INSDC status",
                    "before": "live",
                    "after": "public",
                    "applied": true
                }
            ]
        }
    ]
}
```
