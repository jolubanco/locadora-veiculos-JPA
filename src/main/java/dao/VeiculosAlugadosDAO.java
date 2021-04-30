package dao;


import models.VeiculosAlugados;

import javax.persistence.EntityManager;
import java.util.List;

public class VeiculosAlugadosDAO {

    private EntityManager entityManager;

    public VeiculosAlugadosDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void cadastrar(VeiculosAlugados veiculosAlugado){
        entityManager.persist(veiculosAlugado);
        System.out.println("Locação realizada com sucesso!");
    }

    public List<VeiculosAlugados> buscarTodos(){
        String jpql = "SELECT v FROM VeiculosAlugados v";
        return entityManager.createQuery(jpql,VeiculosAlugados.class).getResultList();
    }
}
