package com.project.smartcontactmanager.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;
    private String name;
    private String Nickname;
    private String work;
    private String Email;
    private String Image;
    @Column(length = 5000)
    private String description;
    private String Phone;
    @ManyToOne
    private User user;
}
