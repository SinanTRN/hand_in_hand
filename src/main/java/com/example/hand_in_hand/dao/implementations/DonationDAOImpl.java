package com.example.hand_in_hand.dao.implementations;

import com.example.hand_in_hand.dao.contracts.IDonationDAO;
import com.example.hand_in_hand.entities.models.Donation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@AllArgsConstructor
@Repository
public class DonationDAOImpl implements IDonationDAO {
    private final EntityManager em;

    @Override
    public List<Donation> getAllDonations() {
        TypedQuery<Donation> query = em.createQuery("SELECT d FROM Donation d", Donation.class);
        return query.getResultList();
    }

    @Override
    public Donation getDonationById(int id) {
        return em.find(Donation.class, id);
    }

    @Override
    public Donation addDonation(Donation donation) {
        return em.merge(donation);
    }

    @Override
    public void updateDonation(Donation donation) {
        em.merge(donation);
    }

    @Override
    public void deleteDonation(int id) {
        Donation donation = em.find(Donation.class, id);
        em.remove(donation);
    }
}
