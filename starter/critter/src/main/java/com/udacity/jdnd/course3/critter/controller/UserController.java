package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dataDTO.CustomerDTO;
import com.udacity.jdnd.course3.critter.dataDTO.EmployeeDTO;
import com.udacity.jdnd.course3.critter.dataDTO.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PetService petService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) throws Exception {
        Customer customer = dtoToCustomer(customerDTO);
        return customerToDTO(customerService.save(customer));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<CustomerDTO> dtoList = new ArrayList<>();
        List<Customer> customers = customerService.getAllCustomers();

        for(Customer customer : customers){
            dtoList.add(customerToDTO(customer));
        }
        return dtoList;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) throws Exception {
        Pet pet = petService.getPetById(petId);
        Customer customer = pet.getCustomer();
        return customerToDTO(customer);
        //return customerToDTO(customerService.getCustomerByPetId(petId));

    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = dtoToEmployee(employeeDTO);
        return employeeToDTO(employeeService.save(employee));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) throws Exception {
        return employeeToDTO(employeeService.findEmployeeById(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) throws Exception {
        employeeService.setAvailabiltyForEmployee(daysAvailable,employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> employees = employeeService.findAllEmployeeForService(employeeDTO.getDate(),employeeDTO.getSkills());
        return employees.stream().map(e -> employeeToDTO(e)).collect(Collectors.toList());
    }


    private EmployeeDTO employeeToDTO(Employee employee){
        EmployeeDTO dto = new EmployeeDTO();
        BeanUtils.copyProperties(employee, dto);
        return dto;
    }

    private Employee dtoToEmployee(EmployeeDTO dto){
        Employee employee = new Employee();
        BeanUtils.copyProperties(dto, employee);
        return employee;
    }

    private Customer dtoToCustomer(CustomerDTO dto) throws Exception {
        Customer customer = new Customer();
        BeanUtils.copyProperties(dto, customer);
        List<Long> petIds = dto.getPetIds();

        List<Pet> pets = new ArrayList<>();
        if(petIds != null && petIds.size() != 0){
            for(Long id : petIds){
                pets.add(petService.getPetById(id));
            }
        }
        customer.setPetIds(pets);
        return customer;
    }

    private CustomerDTO customerToDTO(Customer customer){
        CustomerDTO dto = new CustomerDTO();
        BeanUtils.copyProperties(customer, dto);
        List<Pet> pets = customer.getPetIds();

        List<Long> petIds = new ArrayList<>();
        if(pets.size() != 0){
            for(Pet p : pets){
                Long id = p.getId();
                petIds.add(id);
            }
        }
        dto.setPetIds(petIds);
        return dto;
    }

}