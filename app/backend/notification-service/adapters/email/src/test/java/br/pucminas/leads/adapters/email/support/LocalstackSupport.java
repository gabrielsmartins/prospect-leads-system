package br.pucminas.leads.adapters.email.support;

import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class LocalstackSupport {

    public static LocalStackContainer localStackContainer = new LocalStackContainer(DockerImageName.parse("localstack/localstack:0.11.3"))
                                                             .withServices(LocalStackContainer.Service.SES)
                                                            .withExposedPorts(4566);

    static {
        localStackContainer.start();
    }


}
