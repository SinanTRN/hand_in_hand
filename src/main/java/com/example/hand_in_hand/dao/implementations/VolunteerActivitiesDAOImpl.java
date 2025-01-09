package com.example.hand_in_hand.dao.implementations;

import com.example.hand_in_hand.dao.contracts.VolunteerActivitiesDAO;
import com.example.hand_in_hand.entities.models.VolunteerActivities;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@AllArgsConstructor
public class VolunteerActivitiesDAOImpl implements VolunteerActivitiesDAO {
    private EntityManager entityManagerNesnesi;

    @Override
    public VolunteerActivities save(VolunteerActivities entity) {
        return entityManagerNesnesi.merge(entity);
    }

    @Override
    public List<VolunteerActivities> findAll(){
        TypedQuery<VolunteerActivities> query = entityManagerNesnesi.createQuery("SELECT c FROM VolunteerActivities c", VolunteerActivities.class);
        return query.getResultList();
    }

    @Override
    public VolunteerActivities findById(int id) {
        return entityManagerNesnesi.find(VolunteerActivities.class, id);
    }

    @Override
    public void update(VolunteerActivities entity) {
        entityManagerNesnesi.merge(entity);
    }


    @Override
    public void deleteFindId(int id) {
        VolunteerActivities volunteerActivities = entityManagerNesnesi.find(VolunteerActivities.class, id);
        entityManagerNesnesi.remove(volunteerActivities);
    }

    @Override
    public List<VolunteerActivities> getVolunteerActivitiesBystatus(int status) {
        TypedQuery<VolunteerActivities> query = entityManagerNesnesi.createQuery("SELECT c FROM VolunteerActivities c WHERE c.status = :status", VolunteerActivities.class);
        query.setParameter("status", status);
        return query.getResultList();
    }

}
