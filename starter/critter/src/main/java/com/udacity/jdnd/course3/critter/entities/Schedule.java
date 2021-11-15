package com.udacity.jdnd.course3.critter.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "SCHEDULE_EMPLOYEE", joinColumns = @JoinColumn(name = "SCHEDULE_ID"), inverseJoinColumns = @JoinColumn(name = "EMPLOYEE_ID"))
    private List<Employee> employees;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "SCHEDULE_PET", joinColumns = @JoinColumn(name = "SCHEDULE_ID"), inverseJoinColumns = @JoinColumn(name = "PET_ID"))
    private List<Pet> pets;

    private LocalDate date;
    @ElementCollection(targetClass = EmployeeSkill.class, fetch = FetchType.LAZY)
    private Set<EmployeeSkill> activities;

    public Schedule() {
    }

    public long getId() {
        return id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public LocalDate getDate() {
        return date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
