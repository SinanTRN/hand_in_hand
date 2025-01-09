package com.example.hand_in_hand.dao.contracts;

import com.example.hand_in_hand.entities.models.VolunteerActivities;

import java.util.List;


public interface VolunteerActivitiesDAO {
    VolunteerActivities save(VolunteerActivities entity);
    List<VolunteerActivities> findAll(); // Veritabanında bulunan tüm VolunteerActivity nesnelerini listeler.
    VolunteerActivities findById(int id);// Veritabanında verilen ID e sahip VolunteerActivities nesnesini alıp döner.
    void update(VolunteerActivities entity);//Veritabanındaki mevcut bir VolunteerActivity nesnesini günceller.
    // Parametre olarak aldığı VolunteerActivity nesnesinin içeriğine göre veritabanındaki ilgili aktivitenin bilgilerini yeniler.
    void deleteFindId(int id); //Belirtilen id'ye sahip VolunteerActivity nesnesini veritabanından siler.
    List<VolunteerActivities> getVolunteerActivitiesBystatus(int status); //Veritabanında belirtilen statüye sahip VolunteerActivity nesnelerini listeler.



}
