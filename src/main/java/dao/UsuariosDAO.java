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

    public Usuarios buscarPorUsername(String username){
        String jpql = "SELECT u FROM Usuarios u WHERE u.login.username =: usernameEnviado";
        return entityManager.createQuery(jpql,Usuarios.class).setParameter("usernameEnviado",username).getSingleResult();
    }

    //tratar exception caso não exista o usuario
//    public Usuarios buscaUsuarioPorUsuario(String usuario){
//        String jpql = "SELECT f FROM Funcionarios f where f.login.usuario =: usuarioEnviado";
//        return entityManager.createQuery(jpql,Funcionarios.class).setParameter("usuarioEnviado",usuario).getSingleResult();
//    }
}
