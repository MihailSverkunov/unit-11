package com.github.siberianintegrationsystems.restApp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Setter
@Getter
public class Session extends BaseEntity {

    @Column
    String name;

    @Column
    Double percent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
