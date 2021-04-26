package dao;

import models.CategoriasUsuario;

import javax.persistence.EntityManager;

public class CategoriasUsuarioDAO {

    public EntityManager entityManager;

    public CategoriasUsuarioDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(CategoriasUsuario categoriasUsuario){
        entityManager.persist(categoriasUsuario);
    }
}
