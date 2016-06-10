### RELEASE PLANS

2.0
 
 * Migrate to gradle (?)
 * Provide the integration with Spring out of the box.
 * Use bintray (?)
 
### RELEASE NEW VERSION (by jgitflow maven plugin)
 
    mvn jgitflow:release-start -> starting the new release
    mvn jgitflow:release-finish -> finishing the release (and makes other stuff related with gitflow & artifacts deployment)
    
    Then, Go to https://oss.sonatype.org
    Login to the Nexus UI.
    Go to Staging Repositories page.
    Select a staging repository.
    Click the Close button.
    THEN Click the Release button and it should also appear in maven central etc
 
### GENERATE SNAPSHOT

    mvn clean javadoc:jar source:jar deploy -Darguments=-Dgpg.passphrase=PASSPHRASE

### DEPLOY (use the version 2 of GPG on windows not provided gpg1.4 git-for-windows)

    mvn javadoc:jar source:jar deploy -Dgpg.skip=false -Darguments=-Dgpg.passphrase=PASSPHRASE

### RELEASE NEW VERSION (by the maven release plugin) DEPRECATED

    mvn release:clean
    mvn release:prepare -Darguments=-Dgpg.passphrase=PASSPHRASE
    mvn release:perform -Darguments=-Dgpg.passphrase=PASSPHRASE
 
With the approach with maven release plugin the manual branching is required.    
See more there: [http://vincent.demeester.fr/2012/07/maven-release-gitflow/](http://vincent.demeester.fr/2012/07/maven-release-gitflow/)
