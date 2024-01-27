package com.prueba.Prueba.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="client")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "mail")
    private String mail;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "password")
    private String password;
    @Column(name = "roles")
    private String roles;
    @OneToOne(mappedBy = "clientId")
    private Orders orders;

}
