#!/usr/bin/env bash
set -ev

#git reset --hard 1.5.2-SNAPSHOT
mvn -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn clean install -DskipTests -Drat.skip=true
cp .travis.settings.xml $HOME/.m2/settings.xml
mvn -DaltDeploymentRepository=snapshots::default::https://yotpo.jfrog.io/artifactory/maven deploy
