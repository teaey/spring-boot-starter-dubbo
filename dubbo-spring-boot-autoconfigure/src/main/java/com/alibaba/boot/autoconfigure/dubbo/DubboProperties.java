package com.alibaba.boot.autoconfigure.dubbo;

import com.alibaba.dubbo.config.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashSet;
import java.util.Set;

import static com.alibaba.boot.autoconfigure.dubbo.DubboProperties.PREFIX;


/**
 * Dubbo {@link ConfigurationProperties Properties} with prefix "dubbo."
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ConfigurationProperties
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = PREFIX)
public class DubboProperties {

    /**
     * The prefix of {@link DubboProperties}
     */
    public static final String PREFIX = "dubbo";

    /**
     * The packages to scan , the multiple-value is delimited by comma
     */
    private Set<String> packagesToScan = new LinkedHashSet<>();

    /**
     * {@link ApplicationConfig} property
     */
    private ApplicationConfig application;

    /**
     * {@link ModuleConfig} property
     */
    private ModuleConfig module;

    /**
     * {@link RegistryConfig} property
     */
    private RegistryConfig registry;

    /**
     * {@link ProtocolConfig} property
     */
    private ProtocolConfig protocol;

    /**
     * {@link MonitorConfig} property
     */
    private MonitorConfig monitor;

    /**
     * {@link ProviderConfig} property
     */
    private ProviderConfig provider;

    /**
     * {@link ConsumerConfig} property
     */
    private ConsumerConfig consumer;


    public ApplicationConfig getApplication() {
        return application;
    }

    public void setApplication(ApplicationConfig application) {
        this.application = application;
    }

    public ModuleConfig getModule() {
        return module;
    }

    public void setModule(ModuleConfig module) {
        this.module = module;
    }

    public RegistryConfig getRegistry() {
        return registry;
    }

    public void setRegistry(RegistryConfig registry) {
        this.registry = registry;
    }

    public ProtocolConfig getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolConfig protocol) {
        this.protocol = protocol;
    }

    public MonitorConfig getMonitor() {
        return monitor;
    }

    public void setMonitor(MonitorConfig monitor) {
        this.monitor = monitor;
    }

    public ProviderConfig getProvider() {
        return provider;
    }

    public void setProvider(ProviderConfig provider) {
        this.provider = provider;
    }

    public ConsumerConfig getConsumer() {
        return consumer;
    }

    public void setConsumer(ConsumerConfig consumer) {
        this.consumer = consumer;
    }

    public Set<String> getPackagesToScan() {
        return packagesToScan;
    }

    public void setPackagesToScan(Set<String> packagesToScan) {
        this.packagesToScan = packagesToScan;
    }

}
