package com.zbatis.spring.boot.autoconfigure;

import com.z.zbatis.annotations.Mapper;
import com.z.zbatis.session.SqlSessionFactory;
import com.zbatis.spring.SqlSessionFactoryBean;
import com.zbatis.spring.mapper.ClassPathMapperScanner;
import com.zbatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author: yuanjz
 * @Date: 2022-08-11 15:50
 * @Description:
 */
@Configuration
@EnableConfigurationProperties(ZbatisProperties.class)
public class ZbatisAutoConfiguration {

    private ZbatisProperties properties;

    public ZbatisAutoConfiguration(ZbatisProperties zbatisProperties) {
        this.properties = zbatisProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setConfigLocation(this.properties.getConfigLocation());
        return factory.getObject();
    }

    @Configuration
    @Import({ AutoConfiguredMapperScanRegistrar.class })
    @ConditionalOnMissingBean(MapperFactoryBean.class)
    public static class MapperScannerRegistrarNotFoundConfiguration {
    }

    public static class AutoConfiguredMapperScanRegistrar
            implements ImportBeanDefinitionRegistrar, BeanFactoryAware {

        private BeanFactory beanFactory;

        @Override
        public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
            ClassPathMapperScanner scanner = new ClassPathMapperScanner(registry);
            List<String> basePackages = AutoConfigurationPackages.get(this.beanFactory);
            scanner.setAnnotationClass(Mapper.class);
            scanner.registerFilters();
            scanner.doScan(StringUtils.toStringArray(basePackages));
        }

        @Override
        public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
            this.beanFactory = beanFactory;
        }
    }
}
