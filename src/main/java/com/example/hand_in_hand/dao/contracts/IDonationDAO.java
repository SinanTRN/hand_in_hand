package com.example.hand_in_hand.dao.contracts;

import com.example.hand_in_hand.entities.models.Donation;

import java.util.List;

public interface IDonationDAO {
    List<Donation> getAllDonations();
    Donation getDonationById(int id);
    Donation addDonation(Donation donation);
    void updateDonation(Donation donation);
    void deleteDonation(int id);
}
