package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    public Schedule save(Schedule schedule){return scheduleRepository.save(schedule);}

    public List<Schedule> getAllSchedules(){
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForPet(long id){
        return scheduleRepository.findAllByPets_Id(id);
    }

    public List<Schedule> getScheduleForCustomer(long id){
        return scheduleRepository.findScheduleByCustomerId(id);
    }

    public List<Schedule> getScheduleForEmployee(long id){
        return scheduleRepository.findAllByEmployees_Id(id);
    }
}
