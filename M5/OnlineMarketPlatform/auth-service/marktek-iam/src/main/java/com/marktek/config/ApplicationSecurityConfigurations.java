package com.marktek.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Spring Configuration class containing the 
 * application-wide security configurations.
 * This includes a public/private key pair used 
 * for signing JWT tokens and a PasswordEncoder
 * used to one-way-encode passwords.  
 */
@Configuration
public class ApplicationSecurityConfigurations {

    /**
     * The public/private KeyPair used to sign 
     * JWT tokens with. Returns a map mapping a key ID to a public/private key pair.
     * The key ID will be used in a JWK set to identify the key pair used for
     * signing the JWT token. The key ID will also be encoded as `kid` inside the 
     * JWT token's header.
     * @return the key pair map, mapping a key id to a key pair. 
     * @throws NoSuchAlgorithmException in case the Key algorithm does not exist.
     */
    @Bean
    public Map<String, KeyPair> publicPrivateKeyPair() throws NoSuchAlgorithmException {
        KeyPair keyPair = loadKeyPairFromFile(); 
        HashMap<String, KeyPair>  keyPairMap = new HashMap<>();
        keyPairMap.put("key-id-0", keyPair);
        return keyPairMap;
    }
    
    /**
     * Exposes a PasswordEncode bean which will be used for one-way encoding
     * passwords. This will make sure passwords are not stored in plain text,
     * but as a hash, which will be compared to the hashed, entered user password
     * during authentication. This is what Facebook missed... ;)
     */

    private KeyPair loadKeyPairFromFile() {
        
        // The keystore was created like this:
        //      keytool -genkey -alias jwtKeys -keyalg RSA -sigalg SHA256withRSA -keysize 2048 -validity 3650 -keypass password -keystore jwtKeys.jks -storepass password
        // followed by: 
        //      keytool -importkeystore -srckeystore jwtKeys.jks -destkeystore jwtKeys.jks -deststoretype pkcs12
        // See: http://javaevangelist.blogspot.com/2016/08/how-to-generate-sha-2-sha-256-self.html
        
        // See: https://www.baeldung.com/spring-security-oauth-jwt
        // See: http://blog.marcosbarbero.com/centralized-authorization-jwt-spring-boot2/
        // See: https://gist.github.com/ygotthilf/baa58da5c3dd1f69fae9
        // See: http://www.java2s.com/Code/Java/Security/RetrievingaKeyPairfromaKeyStore.htm
        
        KeyStoreKeyFactory keyStoreKeyFactory = 
          new KeyStoreKeyFactory(new ClassPathResource("jwtKeys.jks"), "password".toCharArray());
        return keyStoreKeyFactory.getKeyPair("jwtKeys");
    }

}
