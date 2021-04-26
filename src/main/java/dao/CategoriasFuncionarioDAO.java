package dao;

import models.CategoriasFuncionario;
import models.Funcionarios;

import javax.persistence.EntityManager;

public class CategoriasFuncionarioDAO {

    private EntityManager entityManager;

    public CategoriasFuncionarioDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void cadastrar(CategoriasFuncionario categoriasFuncionario){
        entityManager.persist(categoriasFuncionario);
    }
}
