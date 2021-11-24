package com.example.accessingdatamysql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Persona {
  @Id //questo fa capire ad hibernate che esso sarà la chiave primaria della tabella
  @GeneratedValue(strategy=GenerationType.AUTO) //si autoincrementa
  private Integer id;
  private String name, lastname, email;
  private int eta;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

public String getLastname() {
	return lastname;
}

public void setLastname(String lastname) {
	this.lastname = lastname;
}

public int getEta() {
	return this.eta;
}
public void setEta(int eta) {
	this.eta = eta;
}

}