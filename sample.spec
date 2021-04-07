{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "title": "ES Index Specifications Schema Definitions",
  "description": "Schema definitions to define ES index property mappings, aliases and settings",
  "type": "object",
  "additionalProperties": false,
  "definitions": {
    "dataType": {
      "enum": [
        "boolean",
        "date",
        "float",
        "keyword",
        "long",
        "nested",
        "object",
        "text"
      ],
      "description": "Supported ES data types"
    },
    "alias": {
      "type": "object",
      "additionalProperties": false,
      "description": "Property definition for ES aliases",
      "properties": {
        "name": {
          "type": "string",
          "description": "Name of the alias"
        }
      },
      "required": [
        "name"
      ]
    },
    "dynamic": {
      "enum": [
        "true",
        "false",
        "strict"
      ],
      "description": "Controls whether new fields can be added dynamically or not",
      "default": "strict"
    },
    "property": {
      "type": "object",
      "additionalProperties": false,
      "description": "Property definition for ES mappings",
      "properties": {
        "applicability": {
          "type": "string",
          "description": "Applicability of this property, e.g. if present only under some conditions or only for some scenarios"
        },
        "label": {
          "type": "string",
          "description": "User friendly label for the property, e.g. if used in UI under a different name"
        },
        "deprecated": {
          "type": "boolean",
          "description": "Indicates if the property is marked as deprecated",
          "default": false
        },
        "description": {
          "type": "string",
          "description": "Description about the property"
        },
        "dynamic": {
          "$ref": "#/definitions/dynamic"
        },
        "label": {
          "type": "string",
          "description": "User friendly label for the property, used in UI for example"
        },
        "name": {
          "type": "string",
          "description": "Name of the property"
        },
        "properties": {
          "type": "array",
          "description": "Child properties for this property",
          "items": {
            "$ref": "#/definitions/property"
          }
        },
        "supportedValues": {
          "type": "array",
          "description": "Supported values for this property",
          "items": {
            "type": "string"
          }
        },
        "supportedFrom": {
          "type": "string",
          "description": "Release from when this property was supported",
          "pattern": "^(\\d+\\.)(\\d+\\.)(\\d+)$|^$"
        },
        "type": {
          "$ref": "#/definitions/dataType"
        }
      },
      "required": [
        "name",
        "supportedFrom",
        "type"
      ]
    }
  },
  "properties": {
    "indexName": {
      "type": "string",
      "description": "Name of the ES index"
    },
    "indexPatterns": {
      "type": "array",
      "description": "List of ES index patterns for which the template gets applied",
      "items": {
        "type": "string"
      }
    },
    "order": {
      "type": "integer",
      "description": "Priority order for template application, with lower order being applied first, and higher orders overriding them",
      "minimum": 0
    },
    "aliases": {
      "type": "array",
      "description": "List of alias definitions for the ES index",
      "items": {
        "$ref": "#/definitions/alias"
      }
    },
    "mapping": {
      "type": "object",
      "additionalProperties": false,
      "description": "Defines property mappings for the ES index",
      "properties": {
        "dynamic": {
          "$ref": "#/definitions/dynamic"
        },
        "properties": {
          "type": "array",
          "description": "List of properties for the ES index",
          "items": {
            "$ref": "#/definitions/property"
          },
          "minItems": 1
        }
      },
      "required": [
        "dynamic",
        "properties"
      ]
    },
    "settings": {
      "type": "object",
      "additionalProperties": false,
      "description": "Defines settings for the ES index",
      "properties": {
        "numberOfShards": {
          "type": "integer",
          "description": "Number of primary shards",
          "minimum": 1,
          "default": -1
        },
        "numberOfReplicas": {
          "type": "integer",
          "description": "Number of replicas",
          "minimum": 0,
          "default": -1
        },
        "refreshInterval": {
          "type": "string",
          "description": "Index refresh interval"
        }
      }
    }
  },
  "required": [
    "indexName",
    "indexPatterns",
    "mapping"
  ]
}