package com.example.hand_in_hand.controller;

import com.example.hand_in_hand.annotations.RoleRequired;
import com.example.hand_in_hand.entities.models.Location;
import com.example.hand_in_hand.service.contracts.LocationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/locations")
public class LocationController {
    private final LocationService locationService;

    @GetMapping
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping("/{id}")
    public Location getLocationById(@PathVariable int id) {
        return locationService.getLocationById(id);
    }
    @PostMapping
    public Location saveLocation(@Valid @RequestBody Location location) {
        return locationService.save(location);
    }

    @RoleRequired(role="ADMIN")
    @PutMapping("/{id}")
    public Location updateLocation(@Valid @RequestBody Location location) {
        locationService.updateLocation(location);
        return location;
    }

    @RoleRequired(role="ADMIN")
    @DeleteMapping("/{id}")
    public String deleteLocationById(@PathVariable int id) {
        locationService.deleteLocationById(id);
        return "Location deleted with id: " + id;
    }

}
