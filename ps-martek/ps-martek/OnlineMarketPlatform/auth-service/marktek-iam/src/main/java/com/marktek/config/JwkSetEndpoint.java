package com.marktek.config;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.oauth2.sdk.id.Issuer;
import com.nimbusds.openid.connect.sdk.SubjectType;
import com.nimbusds.openid.connect.sdk.op.OIDCProviderMetadata;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;
import java.security.KeyPair;
import java.security.Principal;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@FrameworkEndpoint
class JwkSetEndpoint {
    private Map<String, KeyPair> keyPairMapping;
    private IssuerProvider issuerProvider;
    @Autowired
    public JwkSetEndpoint(Map<String, KeyPair> keyPairMapping, IssuerProvider issuerProvider) {
        this.keyPairMapping = keyPairMapping;
        this.issuerProvider = issuerProvider;
    }
    @GetMapping("/.well-known/jwks.json")
    @ResponseBody
    public JSONObject getKeySet(Principal principal) {
        Entry<String, KeyPair> keyMapEntry = keyPairMapping.entrySet().iterator().next();
        String keyId = keyMapEntry.getKey();
        KeyPair keyPair = keyMapEntry.getValue();
        
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey)
                                .keyID(keyId)
                                .algorithm(JWSAlgorithm.RS256)
                                .keyUse(KeyUse.SIGNATURE)
                                .build();
        
        
        JWKSet jwkSet = new JWKSet(key);
       
        // String publicKeyInfo = "-----BEGIN PUBLIC KEY-----\n" + publicKey.getEncoded() + "\n-----END PUBLIC KEY-----";
        //
        // key info as a "value" property in the first key of the `keys` array.
        return jwkSet.toPublicJWKSet().toJSONObject();
    }
    
    @GetMapping("/.well-known/openid-configuration")
    @ResponseBody
    public JSONObject getOpenIdConfiguration(Principal principal) {
        Issuer issuer = issuerProvider.getIssuer();
        
        String jwksetUriString = issuer.getValue() + "/.well-known/jwks.json";
        String authorizationEndpointUriString = issuer.getValue() + "/oauth/authorize";
        String tokenEndpointUriString = issuer.getValue() + "/oauth/token";
        
        URI jwksetUri = URI.create(jwksetUriString);
        URI authorizationEndpointUri = URI.create(authorizationEndpointUriString);
        URI tokenEndpointUri = URI.create(tokenEndpointUriString);
        
        List<SubjectType> subjectTypes = new ArrayList<SubjectType>(); 
        subjectTypes.add(SubjectType.PUBLIC);
        
        OIDCProviderMetadata metadata = new OIDCProviderMetadata(issuer, subjectTypes, jwksetUri);
        metadata.setAuthorizationEndpointURI(authorizationEndpointUri);
        metadata.setTokenEndpointURI(tokenEndpointUri);
        // ... more config.
        // https://authentication.eu10.hana.ondemand.com/.well-known/openid-configuration
        return metadata.toJSONObject();
    }
    
}
