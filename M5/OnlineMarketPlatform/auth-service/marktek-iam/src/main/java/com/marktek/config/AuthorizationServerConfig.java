package com.marktek.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	static final String CLIEN_ID = "turboclient";
	static final String CLIENT_SECRET = "turbopassword";
	static final String GRANT_TYPE_PASSWORD = "password";
	static final String AUTHORIZATION_CODE = "authorization_code";
    static final String REFRESH_TOKEN = "refresh_token";
    static final String IMPLICIT = "implicit";
	static final String SCOPE_READ = "read";
	static final String SCOPE_WRITE = "write";
    static final String TRUST = "trust";
	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;
    static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;
	private AuthenticationManager authenticationManager;
	private Map<String, KeyPair> keyPairMapping;
	private IssuerProvider issuer;
	private BCryptPasswordEncoder encoder;

		public AuthorizationServerConfig(AuthenticationConfiguration authenticationConfiguration,
										 Map<String, KeyPair> keyPairMapping,
										 IssuerProvider issuer, BCryptPasswordEncoder encoder) throws Exception {
			this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
			this.keyPairMapping = keyPairMapping;
			this.issuer=issuer;
			this.encoder=encoder;
		}

	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

		configurer
				.inMemory()
				.withClient(CLIEN_ID)
				.secret(encoder.encode(CLIENT_SECRET)) //with the new hibernate pom its mandatory
				.authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT )
				.redirectUris("https://oauth.pstmn.io/v1/browser-callback")
				.scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
				.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS).
				refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS)
				.autoApprove(true);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore())
				.accessTokenConverter(accessTokenConverter())
				.authenticationManager(authenticationManager);
	}
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) {
		security
				.tokenKeyAccess("permitAll()")
//				.checkTokenAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()") //avoid flooding of bad request or slow attack
				.allowFormAuthenticationForClients() // (1)
		;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {

		Map<String, String> headers = new HashMap<String, String>();
		Map.Entry<String, KeyPair> keyPairEntry = keyPairMapping.entrySet().iterator().next();
		headers.put("kid", keyPairEntry.getKey());

		HeadersEncodingJwtAccessTokenConverter converter = new HeadersEncodingJwtAccessTokenConverter(headers);
		converter.setKeyPair(keyPairEntry.getValue());

		// set a custom user token converter that properly encodes the
		// user name of the authenticated user as the `sub` claim in the JWT token
		// as should be the case, recent libraries.
		DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
		accessTokenConverter.setUserTokenConverter(new SubjectAndIssuerAttributeUserTokenConverter(issuer));
		converter.setAccessTokenConverter(accessTokenConverter);

		return converter;
	}

	/**
	 * Legacy Authorization Server does not support a custom name for the user parameter, so we'll need
	 * to extend the default. By default, it uses the attribute {@code user_name} in the JWT.
	 * However, it would be better to adhere to the {@code sub} property defined in the
	 * <a target="_blank" href="https://tools.ietf.org/html/rfc7519">JWT Specification</a>.
	 *
	 * Additionally, this will place any granted (web-security) authorities / roles of the user
	 * to the `authorities` (NOT scopes) property inside the JWT token.
	 *
	 * It will also encode the Issuer into the {@code iss} claim of the JWT token.
	 */
	private class SubjectAndIssuerAttributeUserTokenConverter extends DefaultUserAuthenticationConverter {

		private static final String SUBJECT_CLAIM = "sub";
		private static final String ISSUER_CLAIM = "iss";
		private IssuerProvider issuerProvider;

		/**
		 * A token converter that encodes the subject ({@code sub}) and
		 * Issuer ({@code iss}) into the JWT token.
		 * Uses a request-scoped issuer to derive the issuer URI from the
		 * incoming HTTP request.
		 */
		public SubjectAndIssuerAttributeUserTokenConverter(IssuerProvider issuerProvider) {
			this.issuerProvider = issuerProvider;
		}

		@Override
		public Map<String, ?> convertUserAuthentication(Authentication authentication) {
			Map<String, Object> response = new LinkedHashMap<String, Object>();
			response.put(SUBJECT_CLAIM, authentication.getName());
			response.put(ISSUER_CLAIM, issuerProvider.getIssuer().getValue());

			if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
				response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
			}
			return response;
		}
	}
}