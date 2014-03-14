# Java Restful Web Service使用指南 #
## 5 REST客户端 ##

- 参考2 JAX-RS2快速实现 5simple-service-webapp-spring-jpa-jquery

    protected Client getClient(TestContainer tc, ApplicationHandler applicationHandler) {
        ClientConfig cc = tc.getClientConfig();

        if (cc == null) {
            cc = new ClientConfig();
        }

        //check if logging is required
        if (isEnabled(TestProperties.LOG_TRAFFIC)) {
            cc.register(new LoggingFilter(LOGGER, isEnabled(TestProperties.DUMP_ENTITY)));
        }

        configureClient(cc);

        return ClientBuilder.newClient(cc);
    }

[INDEX](/README.md)