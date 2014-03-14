package com.example;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Main {
	public static final String BASE_URI = "http://localhost:8080/myapp/";

	public static HttpServer startServer() {
		final ResourceConfig rc = new ResourceConfig().packages("com.example");
		return GrizzlyHttpServerFactory.createHttpServer(URI.create(Main.BASE_URI), rc);
	}

	public static void main(String[] args) throws IOException {
		final HttpServer server = Main.startServer();
		System.out.println(String.format("Jersey app started with WADL available at " + "%sapplication.wadl\nHit enter to stop it...", Main.BASE_URI));
		System.in.read();
		server.shutdownNow();
	}
}