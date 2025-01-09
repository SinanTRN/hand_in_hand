package com.example.hand_in_hand.dao.contracts;

import com.example.hand_in_hand.entities.models.Demand;

import java.util.List;

public interface DemandDAO {
    public Demand save(Demand demand);
    List<Demand> getAllDemands();
    public Demand getDemandById(int id);
    void update(Demand demand);
    void deleteById(int id);

}
