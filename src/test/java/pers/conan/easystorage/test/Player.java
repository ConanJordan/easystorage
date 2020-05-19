package pers.conan.easystorage.test;

import java.math.BigDecimal;

import pers.conan.easystorage.annotation.Column;
import pers.conan.easystorage.annotation.PrimaryKey;
import pers.conan.easystorage.annotation.Sequence;
import pers.conan.easystorage.annotation.Structure;

public class Player implements Structure {
    
    @Column("ID")
    @PrimaryKey
    @Sequence("SEQ_PLAYER_ID")
    private BigDecimal id;
    
    @Column("FIRST_NAME")
    private String firstName;
    
    @Column("LAST_NAME")
    private String lastName;
    
    @Column("UNIFORM_NUMBER")
    private BigDecimal uniformNumber;
    
    @Column("TEAM_ID")
    private BigDecimal teamId;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getUniformNumber() {
        return uniformNumber;
    }

    public void setUniformNumber(BigDecimal uniformNumber) {
        this.uniformNumber = uniformNumber;
    }

    public BigDecimal getTeamId() {
        return teamId;
    }

    public void setTeamId(BigDecimal teamId) {
        this.teamId = teamId;
    }

    public Player() {
        super();
    }

    @Override
    public String toString() {
        return "Player [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", uniformNumber="
                + uniformNumber + ", teamId=" + teamId + "]";
    }

    public Player(String firstName, String lastName, int uniformNumber, int teamId) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.uniformNumber = BigDecimal.valueOf(uniformNumber);
        this.teamId = BigDecimal.valueOf(teamId);
    }
    
    
    
}
