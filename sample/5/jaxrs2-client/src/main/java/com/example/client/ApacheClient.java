package com.example.client;

import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;

public class ApacheClient extends Jaxrs2Client {
    public ApacheClient() {
        buildClient();
    }

    void buildClient() {
        final ClientConfig clientConfig = new ClientConfig();
        /*
        clientConfig.property(ApacheClientProperties.PROXY_URI, "http://192.168.0.100"); 
        clientConfig.property(ApacheClientProperties.PROXY_USERNAME, "erichan"); 
        clientConfig.property(ApacheClientProperties.PROXY_PASSWORD , "han"); 
        clientConfig.property(ClientProperties.CONNECT_TIMEOUT, 1000);
        clientConfig.property(ClientProperties.READ_TIMEOUT, 2000);
        */
        clientConfig.connectorProvider(new ApacheConnectorProvider());
        client = ClientBuilder.newClient(clientConfig);
        checkConfig();
    }
}
