#!/usr/bin/env bash
now=$(date +"%Y_%m_%d")
PROJECT_PATH="D:/projects/NativeCriteria/"
DOCS_PROJECT_PATH="D:/projects/NativeCriteriaPages/"

couscous generate
cd $DOCS_PROJECT_PATH
git rm -r *
cd $PROJECT_PATH
cp -av ./.couscous/generated/* $DOCS_PROJECT_PATH
cd $DOCS_PROJECT_PATH
git save "Documentation update $now"
