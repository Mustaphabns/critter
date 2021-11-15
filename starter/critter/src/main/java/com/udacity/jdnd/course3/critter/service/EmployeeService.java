package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.EmployeeSkill;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(long id) throws Exception {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new Exception("Unable to find employee with id: "+ id);
        }
    }

    public void setAvailabiltyForEmployee(Set<DayOfWeek> daysAvailable, long employeeId) throws Exception {
        Employee employee =  findEmployeeById(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployeeForService(LocalDate date, Set<EmployeeSkill> skills){
        List<Employee> employees = employeeRepository.findEmployeesByDaysAvailableAndSkillsIn(date.getDayOfWeek(), skills);
        List<Employee> availableEmployees = new ArrayList<>();
        for(Employee employee : employees){
            if(employee.getSkills().containsAll(skills)){
                availableEmployees.add(employee);
            }
        }
        return availableEmployees;
    }
}
