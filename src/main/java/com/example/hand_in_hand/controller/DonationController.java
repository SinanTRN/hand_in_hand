package com.example.hand_in_hand.controller;

import com.example.hand_in_hand.annotations.RoleRequired;
import com.example.hand_in_hand.entities.models.Donation;
import com.example.hand_in_hand.service.implementations.DonationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/donations")
public class DonationController {
    private final DonationServiceImpl donationService;

    @GetMapping
    public List<Donation> getAllDonations() {
        return donationService.getAllDonations();
    }

    @GetMapping("/{id}")
    public Donation getDonationById(@PathVariable int id) {
        return donationService.getDonationById(id);
    }

    @PostMapping
    public Donation addDonation(@RequestBody Donation donation) {
        return donationService.addDonation(donation);
    }

    @PutMapping("/{id}")
    public void updateDonation(@RequestBody Donation donation) {
        donationService.updateDonation(donation);
    }

    @RoleRequired(role = "ADMIN")
    @DeleteMapping("/{id}")
    public String deleteDonation(@PathVariable int id) {
        donationService.deleteDonation(id);
        return "Donation deleted successfully";
    }
}
