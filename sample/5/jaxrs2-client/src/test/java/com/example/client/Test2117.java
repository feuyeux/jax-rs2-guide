package com.example.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.message.MessageProperties;

public class Test2117 {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            ClientConfig config = new ClientConfig();
            config.property(ClientProperties.CONNECT_TIMEOUT, 120000);
            config.property(ClientProperties.READ_TIMEOUT, 360000);
            config.property(MessageProperties.XML_SECURITY_DISABLE, Boolean.TRUE);

            Client client = ClientBuilder.newClient(config);
            WebTarget webTarget = client.target("http://localhost:8080/atup-user/rest-api/users");
            System.out.println(webTarget.request(MediaType.APPLICATION_JSON).get().getStatus());
            Thread.sleep(2000);
            System.out.println(i + ":");
            System.out.println(PerformanceLog.getMemory());
        }
    }
}
