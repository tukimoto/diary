package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Diary")
public class Diary {
@Id@GeneratedValue(strategy=GenerationType.IDENTITY)@Column
private long id;

@JoinColumn
@ManyToOne
private UserData userdata;

@Column
private String diary;

@Column
private String data;

public long getId() {return id;}
public void setId(long id) {this.id=id;}



public UserData getUserdata() {return userdata;}
public void setUserdata(UserData userdata) {this.userdata = userdata;}

public String getDiary() {return diary;}
public void setDiary(String diary) {this.diary = diary;}

public String getData() {return data;}
public void setData(String data) {this.data = data;}


}
