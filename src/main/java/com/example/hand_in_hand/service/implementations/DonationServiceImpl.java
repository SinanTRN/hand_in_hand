package com.example.hand_in_hand.service.implementations;

import com.example.hand_in_hand.dao.contracts.CategoryDAO;
import com.example.hand_in_hand.dao.contracts.IDonationDAO;
import com.example.hand_in_hand.dao.contracts.LocationDAO;
import com.example.hand_in_hand.dao.contracts.UserDAO;
import com.example.hand_in_hand.entities.exceptions.ConflictException;
import com.example.hand_in_hand.entities.exceptions.notFoundExceptions.implementations.CategoryNotFoundException;
import com.example.hand_in_hand.entities.exceptions.notFoundExceptions.implementations.DonationNotFoundException;
import com.example.hand_in_hand.entities.exceptions.notFoundExceptions.implementations.LocationNotFoundException;
import com.example.hand_in_hand.entities.exceptions.notFoundExceptions.implementations.UserNotFoundException;
import com.example.hand_in_hand.entities.models.Category;
import com.example.hand_in_hand.entities.models.Donation;
import com.example.hand_in_hand.entities.models.Location;
import com.example.hand_in_hand.entities.models.User;
import com.example.hand_in_hand.service.contracts.IDonationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class DonationServiceImpl implements IDonationService {
    private final IDonationDAO donationDAO;
    private final UserDAO userDAO;
    private final CategoryDAO categoryDAO;
    private final LocationDAO locationDAO;

    @Override
    public List<Donation> getAllDonations() {
        List<Donation> donations = donationDAO.getAllDonations();
        if (donations.isEmpty()) {
            throw new DonationNotFoundException("No donations found");
        }
        return donationDAO.getAllDonations();
    }

    @Override
    public Donation getDonationById(Integer id) {
        Donation donation = donationDAO.getDonationById(id);
        if (donation == null) {
            throw new DonationNotFoundException("Donation with id " + id + " not found");
        }
        return donationDAO.getDonationById(id);
    }
    @Transactional
    @Override
    public Donation addDonation(Donation donation) {
        Donation exsistdonation = donationDAO.getDonationById(donation.getId());
        if (exsistdonation != null) {
            throw new ConflictException("Donation with id " + donation.getId() + " already exists");
        }
        checkDonationFields(donation);
        return donationDAO.addDonation(donation);
    }

    private void checkDonationFields(Donation donation) {
        User philanthropist = userDAO.getById(donation.getPhilanthropist().getId());
        if (philanthropist == null) {
            throw new UserNotFoundException("Philanthropist with id " + donation.getPhilanthropist().getId() + " not found");
        }
        User needy = userDAO.getById(donation.getNeedy().getId());
        if (needy == null) {
            throw new UserNotFoundException("Needy with id " + donation.getNeedy().getId() + " not found");
        }
        Category category = categoryDAO.getById(donation.getCategory().getId());
        if (category == null) {
            throw new CategoryNotFoundException("Category with id " + donation.getCategory().getId() + " not found");
        }
        Location location = locationDAO.getLocationById(donation.getLocation().getId());
        if (location == null) {
            throw new LocationNotFoundException("Location with id " + donation.getLocation().getId() + " not found");
        }
    }

    @Transactional
    @Override
    public void updateDonation(Donation donation) {
        Donation exsistdonation = donationDAO.getDonationById(donation.getId());
        if (exsistdonation == null) {
            throw new DonationNotFoundException("Donation with id " + donation.getId() + " not found");
        }
        checkDonationFields(donation);
        donationDAO.updateDonation(donation);
    }

    @Transactional
    @Override
    public void deleteDonation(Integer id) {
        Donation donation = donationDAO.getDonationById(id);
        if (donation == null) {
            throw new DonationNotFoundException("Donation with id " + id + " not found");
        }
        donationDAO.deleteDonation(id);
    }
}
