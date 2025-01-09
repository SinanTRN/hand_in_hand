package com.example.hand_in_hand.service.implementations;

import com.example.hand_in_hand.dao.implementations.VolunteerActivitiesDAOImpl;
import com.example.hand_in_hand.dao.contracts.LocationDAO;
import com.example.hand_in_hand.entities.exceptions.notFoundExceptions.implementations.LocationNotFoundException;
import com.example.hand_in_hand.entities.exceptions.notFoundExceptions.implementations.VolunteerActivitiesNotFoundException;
import com.example.hand_in_hand.entities.models.Location;
import com.example.hand_in_hand.entities.models.VolunteerActivities;
import com.example.hand_in_hand.service.contracts.VolunteerActivitesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class VolunteerActivitiesServiceImpl implements VolunteerActivitesService {
    private VolunteerActivitiesDAOImpl volunteerActivitiesDAO;
    private LocationDAO locationRepository;

    @Transactional
    @Override
    public VolunteerActivities save(VolunteerActivities entity) {
        // Öncelikle Location nesnesinin varlığını kontrol ediyoruz
        Location location = locationRepository.getLocationById(entity.getLocation().getId());
        if (location == null) {
            throw new LocationNotFoundException("Location not found for the id: " + entity.getLocation().getId());
        }

        // Location bilgisi mevcut, YourEntity nesnesini kaydedebiliriz
        entity.setLocation(location);
        return volunteerActivitiesDAO.save(entity);
    }


    @Override
    public List<VolunteerActivities> findAll() {
        List<VolunteerActivities> volunteerActivities = volunteerActivitiesDAO.findAll();
        if (volunteerActivities.isEmpty()) {
            throw new VolunteerActivitiesNotFoundException("No volunteer activities found");
        }
        return volunteerActivities;
    }


    @Override
    public VolunteerActivities findById(int id) {
        VolunteerActivities volunteerActivities = volunteerActivitiesDAO.findById(id);
        if (volunteerActivities == null) {
            throw new VolunteerActivitiesNotFoundException("Volunteer activity not found for the id: " + id);
        }
        return volunteerActivities;
    }

    @Transactional
    @Override
    public void update(VolunteerActivities entity) {
        if(volunteerActivitiesDAO.findById(entity.getId()) == null){
            throw new VolunteerActivitiesNotFoundException("Volunteer activity not found for the id: " + entity.getId());
        }
        if(locationRepository.getLocationById(entity.getLocation().getId()) == null){
            throw new LocationNotFoundException("Location not found for the id: " + entity.getLocation().getId());
        }
        else {
            entity.setLocation(locationRepository.getLocationById(entity.getLocation().getId()));
            volunteerActivitiesDAO.update(entity);
        }
    }

    @Transactional
    @Override
    public void deleteFindId(int id) {
        VolunteerActivities volunteerActivities = volunteerActivitiesDAO.findById(id);
        if (volunteerActivities == null) {
            throw new VolunteerActivitiesNotFoundException("Volunteer activity not found for the id: " + id);
        }
        volunteerActivitiesDAO.deleteFindId(id);
    }

    @Override
    public List<VolunteerActivities> getVolunteerActivitiesBystatus(int status) {
        List<VolunteerActivities> volunteerActivities = volunteerActivitiesDAO.getVolunteerActivitiesBystatus(status);
        if (volunteerActivities.isEmpty()) {
            throw new VolunteerActivitiesNotFoundException("No volunteer activities found for the status: " + status);
        }
        return volunteerActivities;
    }
}
