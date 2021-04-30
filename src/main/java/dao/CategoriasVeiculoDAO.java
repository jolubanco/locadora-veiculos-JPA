package dao;

import models.CategoriasVeiculo;
import models.Veiculos;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoriasVeiculoDAO {

    private EntityManager entityManager;

    public CategoriasVeiculoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(CategoriasVeiculo categoria){
        entityManager.persist(categoria);
    }

    public List<CategoriasVeiculo> buscarTodos(){
        String jpql = "SELECT c FROM CategoriasVeiculo c";
        return entityManager.createQuery(jpql,CategoriasVeiculo.class).getResultList();
    }

    public CategoriasVeiculo buscarPorId(Long id){
        return entityManager.find(CategoriasVeiculo.class,id);
    }
}
