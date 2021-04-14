
### Works
```
mvn clean package -Dquarkus.package.type=uber-jar
java -jar target/quarkus-oidc-reproducer-0.0.1-SNAPSHOT-runner.jar
```

### Fails
```
mvn clean package -Pnative
./target/quarkus-oidc-reproducer-0.0.1-SNAPSHOT-runner
```
```
__  ____  __  _____   ___  __ ____  ______
 --/ __ \/ / / / _ | / _ \/ //_/ / / / __/
 -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/
2021-04-13 17:48:35,132 DEBUG [io.qua.ver.cor.run.VertxCoreRecorder] (main) Vert.x Cache configured to: /tmp/vertx-cache/8069278841815678558
2021-04-13 17:48:35,133 DEBUG [io.qua.ver.cor.run.VertxCoreRecorder] (main) Vertx has Native Transport Enabled: false
2021-04-13 17:48:35,133 DEBUG [io.qua.arc.impl] (main) ArC DI container shut down
2021-04-13 17:48:35,134 ERROR [io.qua.run.Application] (main) Failed to start application (with profile prod): io.vertx.core.VertxException: Invalid url: https://server.example.org/auth/realms/REALM/.well-known/openid-configuration
        at io.vertx.ext.web.client.impl.WebClientBase.requestAbs(WebClientBase.java:256)
        at io.vertx.ext.web.client.impl.WebClientBase.requestAbs(WebClientBase.java:246)
        at io.vertx.ext.web.client.impl.WebClientBase.getAbs(WebClientBase.java:81)
        at io.vertx.mutiny.ext.web.client.WebClient.getAbs(WebClient.java:295)
        at io.quarkus.oidc.runtime.OidcRecorder.discoverMetadata(OidcRecorder.java:297)
        at io.quarkus.oidc.runtime.OidcRecorder.createOidcClientUni(OidcRecorder.java:266)
        at io.quarkus.oidc.runtime.OidcRecorder.createOidcProvider(OidcRecorder.java:214)
        at io.quarkus.oidc.runtime.OidcRecorder.createTenantContext(OidcRecorder.java:185)
        at io.quarkus.oidc.runtime.OidcRecorder.createStaticTenantContext(OidcRecorder.java:102)
        at io.quarkus.oidc.runtime.OidcRecorder.setup(OidcRecorder.java:49)
        at io.quarkus.deployment.steps.OidcBuildStep$setup-635434700.deploy_0(OidcBuildStep$setup-635434700.zig:100)
        at io.quarkus.deployment.steps.OidcBuildStep$setup-635434700.deploy(OidcBuildStep$setup-635434700.zig:40)
        at io.quarkus.runner.ApplicationImpl.doStart(ApplicationImpl.zig:499)
        at io.quarkus.runtime.Application.start(Application.java:90)
        at io.quarkus.runtime.ApplicationLifecycleManager.run(ApplicationLifecycleManager.java:100)
        at io.quarkus.runtime.Quarkus.run(Quarkus.java:66)
        at io.quarkus.runtime.Quarkus.run(Quarkus.java:42)
        at io.quarkus.runtime.Quarkus.run(Quarkus.java:119)
        at io.quarkus.runner.GeneratedMain.main(GeneratedMain.zig:29)
```


### Solution
Enable SSL native support manually as quarkus-oidc does not trigger it automatically (https://quarkus.io/guides/native-and-ssl)
```
<properties>
     <quarkus.package.type>native</quarkus.package.type>
     <quarkus.native.additional-build-args>-H:EnableURLProtocols=https</quarkus.native.additional-build-args>
</properties>
```

### Environment
* RHEL 8.3
* Apache Maven 3.6.3
* Mandrel 21.0.0.0.Final
```
mvn -version
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: /root/apache-maven-3.6.3
Java version: 11.0.10, vendor: Oracle Corporation, runtime: /root/mandrel-java11-21.0.0.0-Final
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "4.18.0-240.22.1.el8_3.x86_64", arch: "amd64", family: "unix"
```
