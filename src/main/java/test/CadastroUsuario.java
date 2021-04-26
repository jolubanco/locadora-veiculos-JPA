package test;

import dao.CategoriasUsuarioDAO;
import dao.LoginDAO;
import dao.UsuariosDAO;
import models.CategoriasUsuario;
import models.Login;
import models.Usuarios;
import util.JPAUtil;

import javax.persistence.EntityManager;

public class CadastroUsuario {

    public static void main(String[] args) {

        CategoriasUsuario categoriasUsuario1 = new CategoriasUsuario("Atendente");
        CategoriasUsuario categoriasUsuario2 = new CategoriasUsuario("Cliente");
        Login login1 = new Login("Joao","joao2114");
        Login login2 = new Login("Marcelo","marcelo1999");
        Usuarios usuario1 = new Usuarios("Joao", "08326702943",categoriasUsuario1,login1);
        Usuarios usuario2 = new Usuarios("Marcelo", "1022525255",categoriasUsuario2,login2);

        EntityManager entityManager = JPAUtil.getEntityManager();
        LoginDAO loginDAO = new LoginDAO(entityManager);
        CategoriasUsuarioDAO categoriasUsuarioDAO = new CategoriasUsuarioDAO(entityManager);
        UsuariosDAO usuariosDAO = new UsuariosDAO(entityManager);

        entityManager.getTransaction().begin();
        loginDAO.cadastrar(login1);
        loginDAO.cadastrar(login2);
        categoriasUsuarioDAO.cadastrar(categoriasUsuario1);
        categoriasUsuarioDAO.cadastrar(categoriasUsuario2);
        usuariosDAO.cadastrar(usuario1);
        usuariosDAO.cadastrar(usuario2);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
