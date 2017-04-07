package app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by MIHONE on 3/27/2017.
 */

@Configuration
@EnableWebMvc
public class MVCConfig extends WebMvcConfigurerAdapter {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        configurer.enable();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/transfer").setViewName("transfer");
        registry.addViewController("/withdraw").setViewName("withdraw");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/home/add_client").setViewName("add_client");
        registry.addViewController("/home/add_account").setViewName("add_account");
        registry.addViewController("/view").setViewName("view");
        registry.addViewController("/update_client").setViewName("update_client");
        registry.addViewController("/update_employee").setViewName("update_employee");
        registry.addViewController("/add_employee").setViewName("add_employee");
        registry.addViewController("/403").setViewName("403");
        registry.addViewController("/errorpage").setViewName("errorpage");
   }
}
