package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("from Schedule s join s.pets p join p.customer c where c.id = :id")
    List<Schedule> findScheduleByCustomerId(@Param("id") long id);

    List<Schedule> findAllByPets_Id(long id);

    List<Schedule> findAllByEmployees_Id(long id);
}
