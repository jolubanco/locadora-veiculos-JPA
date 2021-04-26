package dao;

import models.Login;

import javax.persistence.EntityManager;

public class LoginDAO {

    private EntityManager entityManager;

    public LoginDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void cadastrar(Login login){
        entityManager.persist(login);
    }
}
