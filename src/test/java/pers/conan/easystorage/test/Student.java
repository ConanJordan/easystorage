package pers.conan.easystorage.test;

import pers.conan.easystorage.annotation.Column;
import pers.conan.easystorage.annotation.PrimaryKey;
import pers.conan.easystorage.annotation.Structure;


public class Student implements Structure {
    
    @Column(value="ID")
    @PrimaryKey
    private Integer id;
    
    @Column(value="NAME")
    private String name;
    
    @Column(value="GENDER")
    private String gender;
    
    @Column(value="BIRTH_DATE")
    private String birthDate;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Student() {
        super();
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", gender=" + gender + ", birthDate=" + birthDate + "]";
    }
    
}
