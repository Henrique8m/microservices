package com.rodrigues.hrapigatewayzuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

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
	}

}
