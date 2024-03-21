package com.marktek.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customers {

    @Id
    //Applicablle to mysql
    @GeneratedValue(strategy= GenerationType.AUTO)
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE1")
//    @SequenceGenerator(name="SEQUENCE1", sequenceName="SEQUENCE1", allocationSize=1)
    private long id;
    @Column(name="mobile_number")
    private String mobileNumber;

    @Column(name="fname")
    private String firstname;

    @Column(name="lname")
    private String lastname;
    @Column
    @JsonIgnore
    private String password;

    @Column(name="perm_address")
    private String address;

    @Column(name="email")
    private String email;

    @Column
    private int age;

}
