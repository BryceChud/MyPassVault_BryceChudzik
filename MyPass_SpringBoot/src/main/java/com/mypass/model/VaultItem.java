package com.mypass.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vault_items")
public class VaultItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long userId;
  private String category;

  // Common
  private String label;

  // Login
  private String username;
  private String password;
  private String url;

  // Credit Card
  private String cardNumber;
  private String cvv;
  private String expiry;

  // Identity
  private String fullName;
  private String dob;

  // Secure Notes
  @Column(length = 2000)
  private String notes;

  public VaultItem() {
  }

  // GETTERS + SETTERS
  public Long getId() {
    return id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  // Login
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

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  // Card
  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getCvv() {
    return cvv;
  }

  public void setCvv(String cvv) {
    this.cvv = cvv;
  }

  public String getExpiry() {
    return expiry;
  }

  public void setExpiry(String expiry) {
    this.expiry = expiry;
  }

  // Identity
  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getDob() {
    return dob;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }

  // Secure notes
  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }
}
