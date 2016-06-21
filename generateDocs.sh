#!/usr/bin/env bash
now=$(date +"%Y-%m-%d")
SRC_PROJECT_PATH="D:/projects/NativeCriteria/"
DOCS_PROJECT_PATH="D:/projects/NativeCriteriaPages/"

mvn clean
couscous generate
cd $DOCS_PROJECT_PATH
git rm -r *
cd $SRC_PROJECT_PATH
cp -av ./.couscous/generated/* $DOCS_PROJECT_PATH
cd $DOCS_PROJECT_PATH
git save "Documentation update $now"
