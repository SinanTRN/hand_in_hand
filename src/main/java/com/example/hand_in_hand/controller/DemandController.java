package com.example.hand_in_hand.controller;

import com.example.hand_in_hand.annotations.RoleRequired;
import com.example.hand_in_hand.entities.dto.DemandResponse;
import com.example.hand_in_hand.entities.models.Demand;
import com.example.hand_in_hand.service.contracts.DemandService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/demands")
public class DemandController {
    private final DemandService demandService;

    @GetMapping
    public List<DemandResponse>getAllDemands(){
        return demandService.getAllDemands();
    }

    @GetMapping("/{id}")
    public DemandResponse getDemandById(@PathVariable int id){
        return demandService.getDemandById(id);
    }

    @PostMapping
    public Demand save(@RequestBody Demand demand){
        demand.setId(0);
        return demandService.save(demand);

    }

    @PutMapping("/{id}")
    public Demand update(@RequestBody Demand demand){
        demandService.update(demand);
        return demand;
    }

    @RoleRequired(role="ADMIN")
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable int id){
        demandService.deleteById(id);
        return "Demand with id : " + id + " deleted successfully";
    }

}

