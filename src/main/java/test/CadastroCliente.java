package test;

import dao.CategoriasVeiculoDAO;
import dao.ClientesDAO;
import dao.LoginDAO;
import dao.VeiculosDAO;
import models.Clientes;
import models.Login;
import util.JPAUtil;

import javax.persistence.EntityManager;

public class CadastroCliente {

    public static void main(String[] args) {
        Login login = new Login("jaquin","2114");
        Clientes cliente = new Clientes("08326702943",login);

        EntityManager entityManager = JPAUtil.getEntityManager();
        LoginDAO loginDAO = new LoginDAO(entityManager);
        ClientesDAO clientesDAO = new ClientesDAO(entityManager);
        VeiculosDAO veiculosDAO = new VeiculosDAO(entityManager);
        CategoriasVeiculoDAO categoriasVeiculoDAO = new CategoriasVeiculoDAO(entityManager);


        entityManager.getTransaction().begin();
        loginDAO.cadastrar(login);
        clientesDAO.cadastrar(cliente);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
