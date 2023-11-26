package top.fyl.oastumore.config;

import jakarta.annotation.Resource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.fyl.oastumore.interceptors.TokenInterceptor;

/**
 * @author dfysa
 * @data 26/11/2023 下午4:59
 * @description
 */

@Configuration
public class SpringMvcConfig  implements WebMvcConfigurer {

    @Resource
    private TokenInterceptor tokenInterceptor;

    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration config=new CorsConfiguration();
        config.setAllowCredentials(true);

        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setMaxAge(1800L);
        UrlBasedCorsConfigurationSource source =new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);


    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")

                .excludePathPatterns(
                        "/user/login",
                        "/api/test"
                );
    }

}
