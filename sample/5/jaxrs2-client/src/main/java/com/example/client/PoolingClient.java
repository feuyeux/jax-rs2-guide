package com.example.client;

import javax.ws.rs.client.ClientBuilder;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.grizzly.connector.GrizzlyConnectorProvider;

public class PoolingClient extends Jaxrs2Client {
	public PoolingClient() {
		buildClient();
	}

	void buildClient() {
		final ClientConfig clientConfig = new ClientConfig();
		final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(20000);
		cm.setDefaultMaxPerRoute(10000);
		clientConfig.property(ApacheClientProperties.CONNECTION_MANAGER, cm);
		clientConfig.connectorProvider(new GrizzlyConnectorProvider());
		client = ClientBuilder.newClient(clientConfig);
		checkConfig();
	}
}
