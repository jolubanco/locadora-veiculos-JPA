package dao;

import models.Usuarios;

import javax.persistence.EntityManager;

public class UsuariosDAO {

    public EntityManager entityManager;

    public UsuariosDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Usuarios usuario){
        entityManager.persist(usuario);
    }
}
