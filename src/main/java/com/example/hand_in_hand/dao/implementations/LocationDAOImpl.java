package com.example.hand_in_hand.dao.implementations;

import com.example.hand_in_hand.annotations.RoleRequired;
import com.example.hand_in_hand.dao.contracts.LocationDAO;
import com.example.hand_in_hand.entities.models.Location;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationDAOImpl implements LocationDAO {
    private EntityManager em;
    @Autowired
    public LocationDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Location save(Location location) {
        return em.merge(location);
    }


    @Override
    public void deleteLocationById(int id) {
        Location location = em.find(Location.class, id);
        em.remove(location);
    }

    @Override
    public void updateLocation(Location location) {
        em.merge(location);
    }

    @Override
    public Location getLocationById(int id) {
        return em.find(Location.class, id);
    }

    @Override
    public List<Location> getAllLocations() {
        TypedQuery<Location> query = em.createQuery("SELECT l FROM Location l", Location.class);
        return query.getResultList();
    }
}
