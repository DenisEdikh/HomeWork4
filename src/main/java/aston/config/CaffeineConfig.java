//package aston.config;
//
//import com.github.benmanes.caffeine.cache.Caffeine;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.jcache.JCacheCacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.cache.Caching;
//import javax.cache.spi.CachingProvider;
//import java.util.concurrent.TimeUnit;
//
//@Configuration
//public class CaffeineConfig {
//
//    @Bean
//    public CacheManager cacheManager() {
//        return new JCacheCacheManager(jCacheManager());
//    }
//
//    @Bean
//    public javax.cache.CacheManager jCacheManager() {
//        CachingProvider cachingProvider = Caching.getCachingProvider();
//        return cachingProvider.getCacheManager();
//    }
//
////    @Bean
////    public CacheManager cacheManager() {
////        org.springframework.cache.caffeine.CaffeineCacheManager cacheManager = new org.springframework.cache.caffeine.CaffeineCacheManager();
////
////        cacheManager.setCaffeine(Caffeine.newBuilder()
////                .initialCapacity(100)
////                .maximumSize(1000)
////                .expireAfterWrite(10, TimeUnit.MINUTES)
////        );
////        return cacheManager;
////    }
//}