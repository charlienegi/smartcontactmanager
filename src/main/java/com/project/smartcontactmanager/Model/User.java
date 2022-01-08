package com.project.smartcontactmanager.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String Name;
    private String email;
    private String password;
    private String ImageUrl;
    @Column(length = 5000)
    private String about;
    private boolean enabled;
    private String role;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<User> lists=new ArrayList<>();
}
