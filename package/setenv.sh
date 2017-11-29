#!/bin/sh

# Identifiers will not be checked to ensure that they conform to the Java Language Specification for Java identifiers.
CATALINA_OPTS="-Dorg.apache.el.parser.SKIP_IDENTIFIER_CHECK=true"
