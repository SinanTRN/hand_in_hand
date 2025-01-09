package com.example.hand_in_hand.dao.implementations;

import com.example.hand_in_hand.dao.contracts.DemandDAO;
import com.example.hand_in_hand.entities.models.Demand;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class DemandDAOImpl implements DemandDAO {
    private EntityManager em;

    @Autowired
    protected DemandDAOImpl(EntityManager em) {
            this.em = em;
    }

    @Override
    public Demand save(Demand demand) {

        Demand dbDemand = em.merge(demand);//entityManagerNesnesi nesnesinin yanına nokta (.) koyarak, o nesne üzerinde tanımlı olan metodları veya özellikleri çağırıyoruz.
        // Java'da nesne yönelimli programlamada, bir nesneye ait metotları çağırmak için bu noktayı kullanırız.
        return dbDemand ;
    }

    @Override
    public List<Demand> getAllDemands() {
        TypedQuery<Demand> query = em.createQuery("SELECT d FROM Demand d", Demand.class);
        return query.getResultList();
    }

    @Override
    public Demand getDemandById(int id) {
        return em.find(Demand.class, id);
    }

    @Override
    public void update(Demand demand) {
        em.merge(demand);
    }

    @Override
    public void deleteById(int id) {
        Demand demand = em.find(Demand.class, id);
        //remove metodu, volunteerActivities nesnesini veritabanından silmek için çağrılır
    }
}
