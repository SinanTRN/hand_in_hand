package com.example.hand_in_hand.service.contracts;

import com.example.hand_in_hand.entities.dto.DemandResponse;
import com.example.hand_in_hand.entities.models.Demand;
import java.util.List;

public interface DemandService {
     Demand save(Demand demand);
     List<DemandResponse>getAllDemands();
     DemandResponse getDemandById(int id);
     void update(Demand demand);
     void deleteById(int id);
     DemandResponse ConvertToResponse(Demand demand);

}
