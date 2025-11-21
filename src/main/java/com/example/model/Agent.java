package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "agents")
public class Agent {

    @Id
    private long id;

    private String type;
}
