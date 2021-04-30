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

    public CategoriasUsuario buscarPorNome(String nome){
        String jpql = "SELECT c FROM CategoriasUsuario c WHERE c.nome =: nomeInformado";
        return entityManager.createQuery(jpql,CategoriasUsuario.class).setParameter("nomeInformado",nome).getSingleResult();
    }
}
