package com.udacity.jdnd.course3.critter.entities;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
public class Employee extends User {
    @ElementCollection(targetClass = EmployeeSkill.class, fetch = FetchType.LAZY)
    private Set<EmployeeSkill> skills;
    @ElementCollection(targetClass = DayOfWeek.class, fetch = FetchType.LAZY)
    private Set<DayOfWeek> daysAvailable;

    public Employee() {
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }


}
