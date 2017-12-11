package com.alibaba.boot.autoconfigure.dubbo;

import com.alibaba.dubbo.config.AbstractConfig;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.config.spring.beans.factory.annotation.ReferenceAnnotationBeanPostProcessor;
import com.alibaba.dubbo.config.spring.beans.factory.annotation.ServiceAnnotationBeanPostProcessor;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Set;

import static com.alibaba.boot.autoconfigure.dubbo.DubboProperties.PREFIX;
import static java.util.Collections.emptySet;

/**
 * Dubbo Auto {@link Configuration}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ApplicationConfig
 * @see Service
 * @see Reference
 * @since 1.0.0
 */
@Configuration
@ConditionalOnProperty(prefix = PREFIX, name = "enabled", matchIfMissing = true, havingValue = "true")
@ConditionalOnClass(AbstractConfig.class)
public class DubboAutoConfiguration {

    /**
     * The name of property for {@link DubboProperties#getPackagesToScan()}
     */
    protected static final String PACKAGES_TO_SCAN_PROPERTY_NAME = "packagesToScan";

    /**
     * Creates {@link ServiceAnnotationBeanPostProcessor} Bean
     *
     * @return {@link ServiceAnnotationBeanPostProcessor}
     */
    @ConditionalOnProperty(prefix = PREFIX, name = PACKAGES_TO_SCAN_PROPERTY_NAME)
    @Bean
    public ServiceAnnotationBeanPostProcessor serviceAnnotationBeanPostProcessor(Environment environment) {
        RelaxedPropertyResolver resolver = new RelaxedPropertyResolver(environment, PREFIX + ".");
        Set<String> packagesToScan = resolver.getProperty(PACKAGES_TO_SCAN_PROPERTY_NAME, Set.class, emptySet());
        return new ServiceAnnotationBeanPostProcessor(packagesToScan);
    }

    /**
     * Creates {@link ReferenceAnnotationBeanPostProcessor} Bean if Absent
     *
     * @return {@link ReferenceAnnotationBeanPostProcessor}
     */
    @ConditionalOnMissingBean
    @Bean(name = ReferenceAnnotationBeanPostProcessor.BEAN_NAME)
    public ReferenceAnnotationBeanPostProcessor referenceAnnotationBeanPostProcessor() {
        return new ReferenceAnnotationBeanPostProcessor();
    }


    @Configuration
    @EnableConfigurationProperties(DubboProperties.class)
    @EnableDubboConfig
    public static class DubboConfiguration {

    }

}
