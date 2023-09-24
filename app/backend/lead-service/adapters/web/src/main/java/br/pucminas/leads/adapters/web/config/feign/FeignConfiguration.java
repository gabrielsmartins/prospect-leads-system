package br.pucminas.leads.adapters.web.config.feign;


import feign.Logger;
import feign.Retryer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"br.pucminas.leads.adapters.web.out.*"})
public class FeignConfiguration {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(200L,1000L,5);
    }
}
