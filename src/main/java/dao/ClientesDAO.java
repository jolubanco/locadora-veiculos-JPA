package dao;

import models.Clientes;

import javax.persistence.EntityManager;

public class ClientesDAO {

    private EntityManager entityManager;

    public ClientesDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void cadastrar(Clientes cliente){
        entityManager.persist(cliente);
    }
}
