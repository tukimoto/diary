package com.example.demo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class UserData {
@Id@GeneratedValue(strategy=GenerationType.IDENTITY)@Column
private long id;

@Column(length=20,nullable=false)
private String name;

@Column(length=50,nullable=false)
private String mail;

@Column(length=10,nullable=false)
private String pass;

@Column
@OneToMany(mappedBy="userdata")
private List<Diary> diaryList;


public long getId() {return id;}
public void setId(long id) {this.id=id;}

public String getName() {return name;}
public void setName(String name) {this.name=name;}

public String getMail() {return mail;}
public void setMail(String mail) {this.mail=mail;}

public String getPass() {return pass;}
public void setPass(String pass) {this.pass=pass;}

public List<Diary> getDiaryList() {return diaryList;}
public void setDiaryList(List<Diary> diaryList) {this.diaryList = diaryList;}






}
