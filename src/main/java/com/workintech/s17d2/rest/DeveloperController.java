package com.workintech.s17d2.rest;


import com.workintech.s17d2.model.Developer;
import com.workintech.s17d2.model.Experience;
import com.workintech.s17d2.model.JuniorDeveloper;
import com.workintech.s17d2.model.MidDeveloper;
import com.workintech.s17d2.tax.Taxable;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/developers")
public class DeveloperController {
    public Map<Integer, Developer> developers;
    private Taxable taxable;

    public DeveloperController(Taxable taxable) {
        this.taxable = taxable;
    }

    @PostConstruct
    public void init() {
        developers = new HashMap<>();
        developers.put(1, new JuniorDeveloper(1, "John", 1000d));
        developers.put(2, new MidDeveloper(2, "Jane", 2000d));
        developers.put(3, new JuniorDeveloper(3, "Doe", 3000d));
    }

    @GetMapping
    public List<Developer> getDevelopers() {
        return developers.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Developer getDeveloper(@PathVariable Integer id) {
        return developers.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addDeveloper(@RequestBody Developer developer) {
        if (developer.getExperience() != null) {
            switch (developer.getExperience()) {
                case JUNIOR -> developers.put(developer.getId(), new JuniorDeveloper(developer.getId(), developer.getName(), developer.getSalary() - (developer.getSalary() * taxable.getSimpleTaxRate())));
                case MID -> developers.put(developer.getId(), new MidDeveloper(developer.getId(), developer.getName(), developer.getSalary() - (developer.getSalary() * taxable.getMiddleTaxRate())));
                case SENIOR -> developers.put(developer.getId(), new MidDeveloper(developer.getId(), developer.getName(), developer.getSalary() - (developer.getSalary() * taxable.getUpperTaxRate())));
            }
        }
    }

    @PutMapping("/{id}")
    public void updateDeveloper(@PathVariable Integer id, @RequestBody Developer developer) {
        developers.replace(id, developer);
    }

    @DeleteMapping("/{id}")
        public void deleteDeveloper(@PathVariable Integer id) {
        developers.remove(id);
    }


}
