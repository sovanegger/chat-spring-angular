package cz.prf.uai.tomsovsky.chat.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"cz.prf.uai.tomsovsky.chat.controller"})
public class ChatWebConfig extends WebMvcConfigurerAdapter {
	
	/**
	 * Jackson has to support Hibernate specifics like lazy fetching.
	 * 
	 *  https://github.com/FasterXML/jackson-datatype-hibernate
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(jacksonMessageConverter());
	}
	
	@Bean 
	public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(new HibernateAwareObjectMapper());
		converter.setObjectMapper(new JodaAwareObjectMapper());
		return converter;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		registry.addResourceHandler("/clientside/**").addResourceLocations("/clientside/").
					setCachePeriod(31556926);
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/clientside/");
		viewResolver.setSuffix(".html");
		return viewResolver;
	}
}
