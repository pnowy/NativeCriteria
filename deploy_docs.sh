#!/usr/bin/env sh

# abort on errors
set -e

# build
npm run docs:build

# navigate into the build output directory
cd docs/.vuepress/dist

# if you are deploying to a custom domain
# echo 'www.example.com' > CNAME

git init
git add -A
git commit -m 'deploy'

# force the push of the just initialized master branch to the remote gh-pages branch at origin
git push -f git@github.com:pnowy/NativeCriteria.git master:gh-pages

cd -
