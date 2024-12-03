package com.it.ebanking.security.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<AppUser> users;
}

