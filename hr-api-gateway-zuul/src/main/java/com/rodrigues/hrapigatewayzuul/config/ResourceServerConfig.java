package com.rodrigues.hrapigatewayzuul.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private JwtTokenStore tokenStrore;
	
	//Rotas publica.
	//A rota tem que estar corretas conforme o application properties (zuul.routes.payroll.path)
	private static final String[] PUBLIC = {"/hr-oauth/oauth/token"};
	private static final String[] OPERATOR = {"/hr-worker/**"};
	private static final String[] ADMIN = {"/hr-payroll/**", "/hr-user/**", "/actuator/**", "/hr-worker/actuator/**",  "/hr-oauth/actuator/**"};
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStrore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(PUBLIC)							//No Caminho definido no vetor PUBLIC
		.permitAll()        							//permit all = todos teram total acesso, obs: mesmo sem log
		.antMatchers(HttpMethod.GET, OPERATOR)			//Endpoint OPERATOR ,Autorização somente para gets
		.hasAnyRole("OPERATOR", "ADMIN")				//Permit apenas para os operator e amin
		.antMatchers(ADMIN)								//EndPoint admin
		.hasRole("ADMIN")								//Apenas os admins poderar acessar
		.anyRequest().authenticated();					//Para as rotas não definidas, vão exigir que o usuario esteja autenticado
		
		http.cors().configurationSource(corsConfigurationSource());
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		//Quem vai ser altorizado a asessar meu dominio, vai ser all, recebe uma lista.
		corsConfig.setAllowedOrigins(Arrays.asList("*"));
		//Quais metodos http vai ser permitido
		corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "PATCH"));
		//Aceitar credenciais
		corsConfig.setAllowCredentials(true);
		//Quais cabeçalhos permitir
		corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		//"/**" todos caminhos
		source.registerCorsConfiguration("/**", corsConfig);
		return source;
		
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()) );
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
	
}
