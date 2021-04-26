package test;

import dao.CategoriasFuncionarioDAO;
import dao.FuncionariosDAO;
import dao.LoginDAO;
import models.CategoriasFuncionario;
import models.Funcionarios;
import models.Login;
import util.JPAUtil;

import javax.persistence.EntityManager;

public class CadastroFuncionario {

    public static void main(String[] args) {

        CategoriasFuncionario categoriasFuncionario = new CategoriasFuncionario("Atendente");
        Login login = new Login("tonho","123");
        Funcionarios funcionario = new Funcionarios("Tonho das Massas",categoriasFuncionario,login);

        EntityManager entityManager = JPAUtil.getEntityManager();
        CategoriasFuncionarioDAO categoriasFuncionarioDAO = new CategoriasFuncionarioDAO(entityManager);
        LoginDAO loginDAO = new LoginDAO(entityManager);
        FuncionariosDAO funcionariosDAO = new FuncionariosDAO(entityManager);

        entityManager.getTransaction().begin();
        categoriasFuncionarioDAO.cadastrar(categoriasFuncionario);
        loginDAO.cadastrar(login);
        funcionariosDAO.cadastrar(funcionario);
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
