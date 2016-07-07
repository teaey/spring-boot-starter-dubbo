package io.dubbo.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;

@Configuration
@EnableConfigurationProperties(DubboProperties.class)
public class DubboAutoConfiguration {

    @Autowired
    private DubboProperties dubboProperties;

    @Bean
    public ApplicationConfig requestApplicationConfig() {
        return dubboProperties.getApplication();
    }

    @Bean
    public RegistryConfig requestRegistryConfig() {
        return dubboProperties.getRegistry();
    }

    @Bean
    public ProtocolConfig requestProtocolConfig() {
        return dubboProperties.getProtocol();
    }


}
