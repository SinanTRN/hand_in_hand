package com.example.hand_in_hand.service.implementations;

import com.example.hand_in_hand.dao.contracts.LocationDAO;
import com.example.hand_in_hand.entities.exceptions.notFoundExceptions.implementations.LocationNotFoundException;
import com.example.hand_in_hand.entities.models.Location;
import com.example.hand_in_hand.service.contracts.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationDAO locationDAO;

    @Transactional
    @Override
    public Location save(Location location) {
        location.setId(0);
        return locationDAO.save(location);
    }

    @Transactional
    @Override
    public void deleteLocationById(int id) {
        Location location = locationDAO.getLocationById(id);
        if (location == null) {
            throw new LocationNotFoundException("Location not found for the id: " + id);
        }
        locationDAO.deleteLocationById(id);
    }

    @Transactional
    @Override
    public void updateLocation(Location location) {
        Location checkLocation = locationDAO.getLocationById(location.getId());
        if (checkLocation == null) {
            throw new LocationNotFoundException("Location not found for the id: " + location.getId());
        }
        locationDAO.updateLocation(location);
    }

    @Override
    public Location getLocationById(int id) {
        Location location = locationDAO.getLocationById(id);
        if (location == null) {
            throw new LocationNotFoundException("Location not found for the id: " + id);
        }
        return locationDAO.getLocationById(id);
    }

    @Override
    public List<Location> getAllLocations() {
        List<Location> locations = locationDAO.getAllLocations();
        if (locations.isEmpty()) {
            throw new LocationNotFoundException("No locations found");
        }
        return locationDAO.getAllLocations();
    }
}
