package com.example.hand_in_hand.service.implementations;

import com.example.hand_in_hand.dao.contracts.CategoryDAO;
import com.example.hand_in_hand.dao.contracts.DemandDAO;
import com.example.hand_in_hand.dao.contracts.UserDAO;
import com.example.hand_in_hand.entities.dto.DemandResponse;
import com.example.hand_in_hand.entities.exceptions.notFoundExceptions.implementations.CategoryNotFoundException;
import com.example.hand_in_hand.entities.exceptions.notFoundExceptions.implementations.DemandNotFoundException;
import com.example.hand_in_hand.entities.exceptions.notFoundExceptions.implementations.UserNotFoundException;
import com.example.hand_in_hand.entities.models.Category;
import com.example.hand_in_hand.entities.models.Demand;
import com.example.hand_in_hand.entities.models.User;
import com.example.hand_in_hand.service.contracts.DemandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@AllArgsConstructor
@Service
public class DemandServiceImpl implements DemandService {

    private DemandDAO demandDAO;
    private UserDAO userDAO;
    private CategoryDAO categoryDAO;

    @Transactional
    @Override
    public Demand save(Demand demand) {
        User user = userDAO.getById(demand.getNeedy().getId());
        if(user == null) {
            throw new UserNotFoundException("User not found for this id : " + demand.getNeedy().getId());
        }
        Category category = categoryDAO.getById(demand.getCategory().getId());
        if(category == null) {
            throw new CategoryNotFoundException("category not found for this id : " + demand.getCategory().getId());
        }

        demand.setNeedy(user);
        demand.setCategory(category);
        return demandDAO.save(demand);
    }

    @Override
    public List<DemandResponse> getAllDemands() {
        List<Demand> demands = demandDAO.getAllDemands();
        if (demands.isEmpty()) {
            throw new DemandNotFoundException("No demands found");
        }
        return demands.stream().map(this::ConvertToResponse).toList();
    }

    @Override
    public DemandResponse getDemandById(int id) {
        Demand demand = demandDAO.getDemandById(id);
        if (demand == null) {
            throw new DemandNotFoundException("No demand found for this id : " + id);
        }
        return ConvertToResponse(demand);
    }

    @Transactional
    @Override
    public void update(Demand demand) {
        if(demandDAO.getDemandById(demand.getId()) == null) {
            throw new DemandNotFoundException("No demand found for this id : " + demand.getId());
        }
        User user = userDAO.getById(demand.getNeedy().getId());
        if(user == null) {
            throw new UserNotFoundException("User not found for this id : " + demand.getNeedy().getId());
        }
        Category category = categoryDAO.getById(demand.getCategory().getId());
        if(category == null) {
            throw new CategoryNotFoundException("category not found for this id : " + demand.getCategory().getId());
        }

        demand.setNeedy(user);
        demand.setCategory(category);
        demandDAO.update(demand);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        Demand demand = demandDAO.getDemandById(id);
        if (demand == null) {
            throw new DemandNotFoundException("No demand found for this id : " + id);
        }
        demandDAO.deleteById(id);
    }

    @Override
    public DemandResponse ConvertToResponse(Demand demand) {
        return new DemandResponse(demand.getId(),
                                  demand.getDescription(),
                                  demand.getCategory(),
                                  demand.getNeedy());
    }

}
