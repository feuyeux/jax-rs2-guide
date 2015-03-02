package com.example.springboot.web;

import com.example.springboot.domain.Hello;
import com.example.springboot.service.HelloService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Component
@Path("hello")
@Produces({"application/javascript;charset=UTF-8", "application/json;charset=UTF-8", "text/javascript;charset=UTF-8"})
public class HelloResource {
    private static final Log log = LogFactory.getLog(HelloResource.class);

    @Autowired
    HelloService helloService;

    @GET
    @Path("/ok")
    public Hello ok() {
        Hello hello = helloService.say();
        log.warn(hello);
        return hello;
    }
}
