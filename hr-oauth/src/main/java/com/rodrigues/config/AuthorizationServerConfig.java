package com.rodrigues.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	@Autowired
	private BCryptPasswordEncoder passowordEnconder;
	
	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;
	
	@Autowired
	private JwtTokenStore tokenStore;
	
	@Autowired
	private AuthenticationManager authenticationManeger;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	
	//Configurar a autenticação com base nas credencias do applicativo (client) e tambem para configurar com o tipo do grandType
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory() 
		.withClient("myappname123")                                      //client_id que é o nome da applicação
		.secret(passowordEnconder.encode("myappsecret123"))              //client_password a senha da applicação
		.scopes("read", "write")                                         //Autorizações do clente em questão
		.authorizedGrantTypes("password")                                //o tipo de criptografia que vai usar nas credenciais do usuario - usaremos o tipo password
		.accessTokenValiditySeconds(86400);                              // Tempo de expiração deste token, em ms
	}

	
	//Configurar o processamento do token
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManeger).tokenStore(tokenStore).accessTokenConverter(accessTokenConverter);
	}
	
	

}
