### RELEASE

    gradlew release                                     // create release tag, push it to the remote
    gradlew uploadArchives -Pcentral                    // upload archives to staging
    gradlew closeAndReleaseRepository -Pcentral         // close repo (equivalent of manual staging)
    
### PUSH SNAPSHOT 

    gradlew uploadArchives -Pcentral -> with snapshot version

### STAGING
 
    Go to https://oss.sonatype.org
    Login to the Nexus UI.
    Go to Staging Repositories page.
    Select a staging repository.
    Click the Close button (on the top bar).
    THEN Click the Release button and it should also appear in maven central etc

### CONFIGURE ACCESS

Add to the file: ~/.gradle/gradle.properties

```
nexusUsername=login
nexusPassword=pass

ossrhUser=login
ossrhPassword=pass

signing.keyId=XXX
signing.password=YYY
signing.secretKeyRingFile=pathToTheKey
```

You can retrieve `signing` elements by running: `gpg -K`

The key ring file is contained in the first line of the output; the short ID is following the / on the sec line. The passphrase is known only by you.
