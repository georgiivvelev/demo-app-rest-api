package com.georgivelev.demoapprestapi.entities.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;

@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @UuidGenerator
    private String id;
    @Enumerated(EnumType.STRING)
    private UserAuthorities name;
    @ManyToMany(mappedBy = "userAuthorities")
    private Set<User> users;

    public Authority() {
    }
    public Authority(UserAuthorities name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserAuthorities getName() {
        return name;
    }

    public void setName(UserAuthorities name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
