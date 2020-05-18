package pers.conan.easystorage.test;

import java.math.BigDecimal;

import pers.conan.easystorage.annotation.Column;
import pers.conan.easystorage.annotation.PrimaryKey;
import pers.conan.easystorage.annotation.Structure;

public class Team implements Structure {
    
    @Column("ID")
    @PrimaryKey
    private BigDecimal id;
    
    @Column("NAME")
    private String name;
    
    public Team() {
        
    }
    
    public BigDecimal getId() {
        return id;
    }
    public void setId(BigDecimal id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Team [id=" + id + ", name=" + name + "]";
    }
    
    
}
