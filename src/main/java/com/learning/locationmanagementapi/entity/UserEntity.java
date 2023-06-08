package com.learning.locationmanagementapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER_ENTITY_TABLE")
@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Column(name = "PASSWORD")
    private String password;


}
