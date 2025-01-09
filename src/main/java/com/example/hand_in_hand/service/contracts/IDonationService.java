package com.example.hand_in_hand.service.contracts;

import com.example.hand_in_hand.entities.models.Donation;

import java.util.List;

public interface IDonationService {
    List<Donation> getAllDonations();
    Donation getDonationById(Integer id);
    Donation addDonation(Donation donation);
    void updateDonation(Donation donation);
    void deleteDonation(Integer id);

}
