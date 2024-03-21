package com.tga.search.adapters.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tga.search.adapters.dto.UserInfoDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class LocalAuthenticationService {

    private final String introspectionUri="http://localhost:8081";

//    public LocalAuthenticationService(@Value("${auth.introspection-uri}") String introspectionUri) {
//        this.introspectionUri = "http://localhost:8080/";
//    }

//    public UserInfoDTO introspect(String token) {
//        String clientSecret = "turbopassword";
//        String clientId = "turboclient";
//        String auth="Basic " + Base64Utils.encodeToString((clientId + ":" + clientSecret).getBytes());
//        final String PATH = "/oauth/check_token";
//        Function<WebClient.RequestBodySpec, WebClient.RequestHeadersSpec<?>> formData = request -> request
//                .body(BodyInserters.fromFormData("token", token));
//        WebClient webClient = WebClient.builder()
//                .baseUrl(this.introspectionUri)
//                .build();
//        String rawResponse = webClient.post()
//                .uri(PATH,formData)
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//                .header(HttpHeaders.AUTHORIZATION, auth)
//                .retrieve()
//                .onStatus(HttpStatus::is4xxClientError, response -> {
//                    throw new OAuth2IntrospectionException("Unauthorized");
//                })
//                .onStatus(HttpStatus::is5xxServerError, response -> {
//                    throw new OAuth2IntrospectionException("Unauthorized");
//                })
//                .bodyToMono(String.class)
//                .block();
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            UserInfoDTO userInfoDTO = objectMapper.readValue(rawResponse, UserInfoDTO.class);
//            return userInfoDTO;
//        } catch (JsonProcessingException e) {
//            throw new OAuth2IntrospectionException("Unauthorized");
//        }
//    }

    public UserInfoDTO introspect(String token) {
        String clientSecret = "turbopassword";
        String clientId = "turboclient";
        String auth = "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes(StandardCharsets.UTF_8));
        final String PATH = "/oauth/check_token";
        String requestBody = "token=" + URLEncoder.encode(token, StandardCharsets.UTF_8);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .uri(URI.create(introspectionUri + PATH))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .header(HttpHeaders.AUTHORIZATION, auth)
                .build();

        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= HttpURLConnection.HTTP_BAD_REQUEST) {
                throw new OAuth2IntrospectionException("Unauthorized");
            }

            String rawResponse = response.body();

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                UserInfoDTO userInfoDTO = objectMapper.readValue(rawResponse, UserInfoDTO.class);
                return userInfoDTO;
            } catch (JsonProcessingException e) {
                throw new OAuth2IntrospectionException("Unauthorized");
            }
        } catch (IOException | InterruptedException e) {
            throw new OAuth2IntrospectionException("Unauthorized");
        }
    }
}
