package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {
    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;
    public Pet save(Pet pet, long id){
        Customer customer = customerRepository.getOne(id);
        pet.setCustomer(customer);
        Pet p = petRepository.save(pet);

        return  p;
    }

    public List<Pet> getPetByCustomerId(long ownerId){
        return petRepository.findPetByCustomerId(ownerId);
    }

    public List<Pet> getAllPets(){
        return petRepository.findAll();
    }

    public Pet getPetById(long petId) throws Exception {
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (optionalPet.isPresent()) {
            return optionalPet.get();
        } else {
            throw new Exception("Unable to find pet with id: "+ petId);
        }
    }
}
