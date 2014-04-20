package kmap.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.annotation.AnnotationJmxAttributeSource;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.jmx.export.assembler.MetadataMBeanInfoAssembler;
import org.springframework.jmx.export.naming.MetadataNamingStrategy;
import org.springframework.jmx.support.MBeanServerFactoryBean;

/**
 * Created by knoma on 20/04/14.
 */
@Configuration
public class MBeanConfig {

    @Bean
    public AnnotationMBeanExporter annotationMBeanExporter() {
        return new AnnotationMBeanExporter();
    }

    @Bean
    public MBeanServerFactoryBean mbeanServer() {
        MBeanServerFactoryBean mBeanServerFactoryBean = new MBeanServerFactoryBean();
        mBeanServerFactoryBean.setLocateExistingServerIfPossible(true);
        return mBeanServerFactoryBean;
    }

    @Bean
    public MetadataNamingStrategy getNamingStrategy() {
        MetadataNamingStrategy strategy = new MetadataNamingStrategy();
        strategy.setAttributeSource(new AnnotationJmxAttributeSource());
        return strategy;
    }

    @Bean
    public MetadataMBeanInfoAssembler getMbeanInfoAssembler() {
        return new MetadataMBeanInfoAssembler(new AnnotationJmxAttributeSource());
    }


    @Bean
    @Lazy
    public MBeanExporter mBeanExporter() {
        MBeanExporter mBeanExporter = new MBeanExporter();
        mBeanExporter.setAutodetect(true);
        mBeanExporter.setServer(mbeanServer().getObject());
        mBeanExporter.setNamingStrategy(getNamingStrategy());
        mBeanExporter.setAssembler(getMbeanInfoAssembler());

        return mBeanExporter;
    }
}
