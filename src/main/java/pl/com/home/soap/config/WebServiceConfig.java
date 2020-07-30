package pl.com.home.soap.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
//		Spring WS używa różnych klas do obsługi wiadomości/żądań
//		tutaj jawnie podajemy temu servletowi contekst aplikacji,
//		definiujemu jako bean
//		Spring sam tego automatycznie nie wykryje
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();

		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);

		return new ServletRegistrationBean(servlet, "/ws/*");
	}

	@Bean(name = "movies")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema moviesSchema) {
//		DefaultWsdl11Definition do użycia standardu wsdl 1.1

		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("moviesPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://spring.io/guides/gs-producing-web-service");
		wsdl11Definition.setSchema(moviesSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema moviesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("movies.xsd"));
	}
}