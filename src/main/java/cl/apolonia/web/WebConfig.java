package cl.apolonia.web;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registro) {

        registro.addViewController("/").setViewName("index");
        registro.addViewController("/login");
        registro.addViewController("/prueba");
        registro.addViewController("/flujotrabajo");
        registro.addViewController("/ejecutarproceso");
        registro.addViewController("/funcionarios");
        registro.addViewController("/gestionartarea/**");
        registro.addViewController("/nuevaTarea/**");
        registro.addViewController("/errores/403").setViewName("error 403");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/webjars/**",
                "/img/**",
                "/css/**",
                "/js/**")
                .addResourceLocations(
                        "classpath:/static/img/",
                        "classpath:/static/css/",
                        "classpath:/static/js/");
    }

}
