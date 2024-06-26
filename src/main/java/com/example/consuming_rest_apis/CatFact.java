package com.example.consuming_rest_apis;

import jakarta.persistence.*;

@Entity
public class CatFact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 1000)
    private String fact;
    private int length;

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
