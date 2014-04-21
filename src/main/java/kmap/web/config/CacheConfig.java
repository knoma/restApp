package kmap.web.config;

import net.sf.ehcache.management.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jmx.support.MBeanServerFactoryBean;

/**
 * Created by knoma on 20/04/14.
 */
@Configuration
public class CacheConfig {


    @Qualifier("mbeanServer")
    @Autowired
    private MBeanServerFactoryBean server;

    @Bean
    public EhCacheCacheManager ehCacheCacheManager() {

        return new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
    }


    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {

        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();

        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        cacheManagerFactoryBean.setShared(true);

        return cacheManagerFactoryBean;
    }


    @Bean
    public ManagementService managementService() {

        ManagementService managementService = new ManagementService(ehCacheCacheManager().getCacheManager(), server.getObject(), true, true, true, true);
        managementService.init();
        return managementService;
    }
}
