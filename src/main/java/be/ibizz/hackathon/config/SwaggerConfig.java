package be.ibizz.hackathon.config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * Class to configure the Swagger framework. This framework is used to create a documentation for the REST service the application exposes. 
 * 
 *
 */
@Configuration
@EnableSwagger
public class SwaggerConfig extends WebMvcConfigurerAdapter {
  @Autowired
  private SpringSwaggerConfig springSwaggerConfig;

  @Override
  public void configureDefaultServletHandling(
    DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  @Autowired
  public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
    this.springSwaggerConfig = springSwaggerConfig;
  }

  @Bean
  public SwaggerSpringMvcPlugin customImplementation() {
    return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
      .apiInfo(apiInfo())
      .includePatterns(".*api.*");
  }

  private ApiInfo apiInfo() {
    ApiInfo apiInfo = new ApiInfo(
      "Broodautomaat REST API",
      "",
      "",
      "",
      "",
      ""
    );

    return apiInfo;
  }
}