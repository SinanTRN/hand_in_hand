package com.example.hand_in_hand.controller;

import com.example.hand_in_hand.annotations.RoleRequired;
import com.example.hand_in_hand.entities.models.VolunteerActivities;
import com.example.hand_in_hand.service.implementations.VolunteerActivitiesServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/vactivities")
public class VolunteerActivitiesController {
    private final VolunteerActivitiesServiceImpl volunteerActivitiesService;

    // Tüm aktiviteleri listelemek için GET isteği
    @GetMapping
    public List<VolunteerActivities> findAll() {
        return volunteerActivitiesService.findAll();
    }

    @GetMapping("/status/{status}")
    public List<VolunteerActivities> getVolunteerActivitiesBystatus(@PathVariable int status) {
        return volunteerActivitiesService.getVolunteerActivitiesBystatus(status);
    }

    // ID'ye göre bir aktiviteyi bulmak için GET isteği
    @GetMapping("/{id}")
    public VolunteerActivities findById(@PathVariable int id) {
        return volunteerActivitiesService.findById(id);
    }

    // Yeni bir aktivite eklemek için POST isteği
    @PostMapping
    public VolunteerActivities save(@RequestBody VolunteerActivities entity) {
        entity.setId(0);
        return volunteerActivitiesService.save(entity);
    }

    // Mevcut bir aktiviteyi güncellemek için PUT isteği
    @PutMapping("/{id}")
    public VolunteerActivities update(@RequestBody VolunteerActivities entity) {
         volunteerActivitiesService.update(entity);
         return entity;
    }

    @RoleRequired(role = "ADMIN")
    // ID'ye göre bir aktiviteyi silmek için DELETE isteği
    @DeleteMapping("/{id}")
    public String deleteFindById(@PathVariable int id) {

        volunteerActivitiesService.deleteFindId(id);
        return "Volunteer activity with id: " + id + " deleted successfully";
    }
}
