package com.example.hand_in_hand.service.contracts;

import com.example.hand_in_hand.entities.models.Location;

import java.util.List;

public interface LocationService {
    public Location save(Location location);
    public void deleteLocationById(int id);
    public void updateLocation(Location location);
    public Location getLocationById(int id);
    public List<Location> getAllLocations();
}
