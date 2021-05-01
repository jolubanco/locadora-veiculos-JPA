package dao;

import models.Usuarios;
import models.Veiculos;

import javax.persistence.EntityManager;
import java.util.List;

public class UsuariosDAO {

    public EntityManager entityManager;

    public UsuariosDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Usuarios usuario){
        entityManager.persist(usuario);
        System.out.println("Usuário Cadastrado com Sucesso!");
    }

    public Usuarios buscarPorUsername(String username){
        String jpql = "SELECT u FROM Usuarios u WHERE u.login.username =: usernameEnviado";
        return entityManager.createQuery(jpql,Usuarios.class).setParameter("usernameEnviado",username).getSingleResult();
    }

    public List<Usuarios> buscarTodos(){
        String jpql = "SELECT u FROM Usuarios u";
        return entityManager.createQuery(jpql,Usuarios.class).getResultList();
    }

    public void desativarCliente(String cpf){
        String jpql = "UPDATE Usuarios SET status = 0 WHERE cpf = :cpfInformado";
        entityManager.createQuery(jpql).setParameter("cpfInformado",cpf).executeUpdate();
        System.out.println("Cliente Desativado com Sucesso!");
    }


    //tratar exception caso não exista o usuario
//    public Usuarios buscaUsuarioPorUsuario(String usuario){
//        String jpql = "SELECT f FROM Funcionarios f where f.login.usuario =: usuarioEnviado";
//        return entityManager.createQuery(jpql,Funcionarios.class).setParameter("usuarioEnviado",usuario).getSingleResult();
//    }
}
