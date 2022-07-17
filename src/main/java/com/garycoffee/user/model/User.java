package com.garycoffee.user.model;

import com.garycoffee.user.Enum.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 128)
    private String password;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(name = "sex", nullable = false, length = 1)
    private Sex sex;

    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Column(name = "phone", nullable = false, length = 20, unique = true)
    private String phone;

    @Column(name = "account_balance", nullable = false)
    private Integer accountBalance;

    @Column(name = "key_question_ans", nullable = false, length = 20)
    private String keyQuestionAns;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "last_modified_date", nullable = false)
    private Date lastModifiedDate;

    @Column(name = "role", nullable = false, length = 20)
    private String role;


}
