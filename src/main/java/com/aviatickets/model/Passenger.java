package com.aviatickets.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "passengers")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birthday_day")
    private String birthday;

    @OneToMany(mappedBy = "passenger", fetch = FetchType.EAGER)
    private List<Ticket> ticketCollections;

    public Passenger() {
    }

    public Passenger(int id, String firstName, String lastName, String birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public List<Ticket> getTicketCollections() {
        return ticketCollections;
    }

    public void setTicketCollections(List<Ticket> ticketCollections) {
        this.ticketCollections = ticketCollections;
    }

    @Override
    public String toString() {
        return "Passengers{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
