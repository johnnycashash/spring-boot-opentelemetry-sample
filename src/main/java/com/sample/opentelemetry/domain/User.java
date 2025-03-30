package com.sample.opentelemetry.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;


@Entity
@Table(name = "users")
public class User {
    @Id
    private long id;
    private String userName;

    private String userPass;

    public User() {
    }

    public User(long id, String userName, String userPass) {
        super();
        this.id = id;
        this.userName = userName;
        this.userPass = userPass;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(userName, user.userName) && Objects.equals(userPass, user.userPass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, userPass);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                '}';
    }
}
