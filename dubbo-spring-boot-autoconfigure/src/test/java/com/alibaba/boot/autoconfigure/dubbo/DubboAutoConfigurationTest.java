package com.alibaba.boot.autoconfigure.dubbo;

import com.alibaba.dubbo.config.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * {@link DubboAutoConfiguration} Test
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        properties = {
                "dubbo.application.name = dubbo-demo-application",
                "dubbo.module.name = dubbo-demo-module",
                "dubbo.registry.address = zookeeper://192.168.99.100:32770",
                "dubbo.protocol.name=dubbo",
                "dubbo.protocol.port=20880",
                "dubbo.monitor.address=zookeeper://127.0.0.1:32770",
                "dubbo.provider.host=127.0.0.1",
                "dubbo.consumer.client=netty",
                "dubbo.packages-to-scan=com.alibaba.boot.autoconfigure.dubbo, com.alibaba.boot.autoconfigure.dubbo.condition"
        },
        classes = {DubboAutoConfigurationTest.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
@SpringBootConfiguration
@EnableAutoConfiguration
public class DubboAutoConfigurationTest {

    @Autowired
    private ObjectProvider<ApplicationConfig> applicationConfigObjectProvider;

    @Autowired
    private ObjectProvider<ModuleConfig> moduleConfigObjectProvider;

    @Autowired
    private ObjectProvider<RegistryConfig> registryConfigObjectProvider;

    @Autowired
    private ObjectProvider<MonitorConfig> monitorConfigObjectProvider;

    @Autowired
    private ObjectProvider<ProviderConfig> providerConfigObjectProvider;

    @Autowired
    private ObjectProvider<ConsumerConfig> consumerConfigObjectProvider;

    @Autowired
    private ObjectProvider<ProtocolConfig> protocolConfigObjectProvider;

    @Autowired
    private DubboProperties dubboProperties;

    @Autowired
    private Environment environment;

    @Test
    public void testConfigPrefix() {

        Assert.assertNotNull(dubboProperties);

    }

    @Test
    public void testApplicationConfig() {

        ApplicationConfig applicationConfig = applicationConfigObjectProvider.getIfAvailable();

        Assert.assertEquals("dubbo-demo-application", applicationConfig.getName());

    }

    @Test
    public void testModuleConfig() {

        ModuleConfig moduleConfig = moduleConfigObjectProvider.getIfAvailable();

        Assert.assertEquals("dubbo-demo-module", moduleConfig.getName());

    }

    @Test
    public void testRegistryConfig() {

        RegistryConfig registryConfig = registryConfigObjectProvider.getIfAvailable();

        Assert.assertEquals("zookeeper://192.168.99.100:32770", registryConfig.getAddress());

    }

    @Test
    public void testMonitorConfig() {

        MonitorConfig monitorConfig = monitorConfigObjectProvider.getIfAvailable();

        Assert.assertEquals("zookeeper://127.0.0.1:32770", monitorConfig.getAddress());

    }

    @Test
    public void testProtocolConfig() {

        ProtocolConfig protocolConfig = protocolConfigObjectProvider.getIfAvailable();

        Assert.assertEquals("dubbo", protocolConfig.getName());
        Assert.assertEquals(Integer.valueOf(20880), protocolConfig.getPort());

    }

    @Test
    public void testConsumerConfig() {

        ConsumerConfig consumerConfig = consumerConfigObjectProvider.getIfAvailable();

        Assert.assertEquals("netty", consumerConfig.getClient());

    }


}
