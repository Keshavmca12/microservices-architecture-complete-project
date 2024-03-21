package com.marktek.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationForm {
    private String mobileNo;
    private String otp;

    private String firstname;

    private String lastname;
    private String password;

    private String address;

    private String email;

    private int age;
}
