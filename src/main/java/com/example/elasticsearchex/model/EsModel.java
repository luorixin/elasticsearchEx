package com.example.elasticsearchex.model;

import java.util.Date;

public class EsModel {
  private String id;
  private String name;
  private int age;
  private Date date;

  @Override
  public String toString() {
    return "EsModel{" +
      "id='" + id + '\'' +
      ", name='" + name + '\'' +
      ", age=" + age +
      ", date=" + date +
      '}';
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public Date getDate() {
    return date;
  }
}
