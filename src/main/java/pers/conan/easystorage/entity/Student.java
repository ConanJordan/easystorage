package pers.conan.easystorage.entity;

import pers.conan.easystorage.annotation.Structure;
import pers.conan.easystorage.annotation.Table;

import java.sql.Date;

@Structure
@Table(name = "student", alias = "student")
public class Student {

    private int id;

    private String name;

    private String gender;

    private Date birthDate;

}
