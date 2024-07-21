package com.example.pet_observer.controller;

import com.example.pet_observer.model.Pet;
import com.example.pet_observer.model.Vaccine;
import com.example.pet_observer.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pets", petRepository.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String addPetForm(Model model) {
        model.addAttribute("pet", new Pet());
        return "add_pet";
    }

    @PostMapping("/add")
    public String addPetSubmit(@ModelAttribute Pet pet) {

        for (Vaccine vaccine : pet.getVaccines()) {
            vaccine.setPet(pet);
        }
        petRepository.save(pet);
        return "redirect:/pets/";

    }

    @GetMapping("/view/{id}")
    public String viewPet(@PathVariable("id") Long id, Model model) {
        Pet pet = petRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid pet Id:" + id));
        model.addAttribute("pet", pet);
        return "view_pet";
    }
}