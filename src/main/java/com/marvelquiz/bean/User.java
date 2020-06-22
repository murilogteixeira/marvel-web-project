package com.marvelquiz.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int score;

    @Column(nullable = false)
    private int rightAnswerPercent;

    @Column(nullable = false)
    private int wrongAnswerPercent;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRightAnswerPercent() {
        return rightAnswerPercent;
    }

    public void setRightAnswerPercent(int rightAnswerPercent) {
        this.rightAnswerPercent = rightAnswerPercent;
    }

    public int getWrongAnswerPercent() {
        return rightAnswerPercent;
    }

    public void setWrongAnswerPercent(int wrongAnswerPercent) {
        this.wrongAnswerPercent = wrongAnswerPercent;
    }

    @Override
    public String toString() {
        return "username: " + username;
    }
    
}