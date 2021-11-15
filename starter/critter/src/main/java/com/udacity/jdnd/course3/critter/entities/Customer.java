package com.udacity.jdnd.course3.critter.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer extends User {
    @Column
    private String phoneNumber;
    @Column
    private String notes;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pet> petIds;

    public Customer() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public List<Pet> getPetIds() {
        return petIds;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setPetIds(List<Pet> petIds) {
        this.petIds = petIds;
    }
}
