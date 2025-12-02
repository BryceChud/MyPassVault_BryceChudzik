package com.mypass.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;

  private String password;

  private String security1;
  private String security2;
  private String security3;

  public User() {
  }

  public User(String email, String password, String s1, String s2, String s3) {
    this.email = email;
    this.password = password;
    this.security1 = s1;
    this.security2 = s2;
    this.security3 = s3;
  }

  // GETTERS & SETTERS
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSecurity1() {
    return security1;
  }

  public void setSecurity1(String security1) {
    this.security1 = security1;
  }

  public String getSecurity2() {
    return security2;
  }

  public void setSecurity2(String security2) {
    this.security2 = security2;
  }

  public String getSecurity3() {
    return security3;
  }

  public void setSecurity3(String security3) {
    this.security3 = security3;
  }
}
