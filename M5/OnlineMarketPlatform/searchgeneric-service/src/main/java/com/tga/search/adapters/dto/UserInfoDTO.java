package com.tga.search.adapters.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoDTO {
    private final Boolean active;
    private final Integer exp;
    private final List<String> authorities;
    private final String client_Id;
    private final List<String> scope;

    public UserInfoDTO(@JsonProperty(value = "active") Boolean active,
                       @JsonProperty(value = "exp") Integer exp,
                       @JsonProperty(value = "authorities") List<String> authorities,
                       @JsonProperty(value = "client_id") String client_Id,
                       @JsonProperty(value = "scope") List<String> scope) {
        this.active = active;
        this.exp = exp;
        this.authorities = authorities;
        this.client_Id = client_Id;
        this.scope = scope;
    }
}