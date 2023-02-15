package com.example;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Person")
@ToString
@NamedQuery(name="getByNames", query="select p from Person p order by p.name")
@NamedQuery(name="getById", query="select p from Person p where p.id = :id")
@NamedQuery(name="getByLiveLocation", query="select p from Person p where p.liveLocation is not NULL")
public class Person {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    public String name;
    public String email;
    public String password;
    public String about;
    public String token;
    public String country;
    public String location;
    public double lng;
    public double lat;
    public String dob;
    public int gender;
    public int userType;
    public int userStatus;
    public String profilePicture;
    public String coverPicture;
    public boolean enablefollowme;
    public boolean sendmenotifications;
    public boolean sendTextmessages;
    public boolean enabletagging;
    public String createdAt;
    public String updatedAt;
    public double livelng;
    public double livelat;
    public String liveLocation;
    public int creditBalance;
    public int myCash;
}
