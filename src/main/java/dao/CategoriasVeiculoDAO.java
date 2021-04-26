package dao;

import models.CategoriasVeiculo;
import models.Veiculos;

import javax.persistence.EntityManager;

public class CategoriasVeiculoDAO {

    private EntityManager entityManager;

    public CategoriasVeiculoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(CategoriasVeiculo categoria){
        entityManager.persist(categoria);
    }
}
