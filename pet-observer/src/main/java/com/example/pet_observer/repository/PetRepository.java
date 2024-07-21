package com.example.pet_observer.repository;

import com.example.pet_observer.model.Pet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PetRepository extends CrudRepository<Pet, Long> {

    @Query("SELECT p FROM Pet p LEFT JOIN FETCH p.vaccines")
    List<Pet> findAll();
}
