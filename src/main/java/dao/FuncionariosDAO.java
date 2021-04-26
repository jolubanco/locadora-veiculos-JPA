package dao;

import models.Funcionarios;
import models.Login;

import javax.persistence.EntityManager;

public class FuncionariosDAO {

    private EntityManager entityManager;

    public FuncionariosDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void cadastrar(Funcionarios funcionarios){
        entityManager.persist(funcionarios);
    }

    //tratar exception caso não exista o usuario
    public Funcionarios buscaFuncionarioPorUsuario(String usuario){
        String jpql = "SELECT f FROM Funcionarios f where f.login.usuario =: usuarioEnviado";
        return entityManager.createQuery(jpql,Funcionarios.class).setParameter("usuarioEnviado",usuario).getSingleResult();
    }
}
