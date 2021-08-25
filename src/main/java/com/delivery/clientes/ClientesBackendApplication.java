package com.delivery.clientes;

import com.delivery.clientes.interceptor.LoggingRequestInterceptor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableAsync
public class ClientesBackendApplication implements CommandLineRunner {

	@Value("${micros.timeout}")
	private Integer timeout;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ClientesBackendApplication.class, args);
	}

	@Bean
	@Qualifier("restInterceptor")
	public RestTemplate createRestTemplateInterceptor() {
		RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new LoggingRequestInterceptor());
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}

	@Bean
	public HttpHeaders httpHeaders() {
		return new HttpHeaders();
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
		return mapper;
	}

	@Override
	public void run(String... args) throws Exception {
		String  password =  "12345";

		for (int i = 0; i < 4 ; i++) {
			String passwordBcrypt =  passwordEncoder.encode(password);
			System.out.println(passwordBcrypt);

		}

	}
}
