#!/usr/bin/env bash
set -ev

#mvn -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn clean install -P integration-tests -Drat.skip=true --no-snapshot-updates
#(cd itests && mvn -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn -P integration-tests -Dit.test=org.apache.unomi.itests.ProfileServiceWithoutOverwriteIT --no-snapshot-updates verify)
mvn -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn clean install -Drat.skip=true --no-snapshot-updates