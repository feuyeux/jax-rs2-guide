package com.example.client.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestDefaultClient.class, TestApacheClient.class, TestGrizzlyClient.class, TestPoolingClient.class })
public class BasicTestSuite {
}
