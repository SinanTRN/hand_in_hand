package com.example.hand_in_hand.dao.contracts;

import com.example.hand_in_hand.entities.models.Location;

import java.util.List;

public interface LocationDAO {
     Location save(Location location);
     void deleteLocationById(int id);
     void updateLocation(Location location);
     Location getLocationById(int id);
     List<Location> getAllLocations();
}
