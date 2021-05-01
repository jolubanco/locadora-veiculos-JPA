package test;

import dao.*;
import models.*;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Date;

public class CadastroVeiculoAlugado {
    public static void main(String[] args) {
        Login login = new Login("t","2114");
        CategoriasUsuario categoriasUsuario = new CategoriasUsuario("Design");
        Usuarios usuario = new Usuarios("Tonho","1010",categoriasUsuario,login);
        CategoriasVeiculo categoriasVeiculo = new CategoriasVeiculo("Moto");
        Veiculos veiculo = new Veiculos("Renaut","AAA-1010",new BigDecimal("35000"),categoriasVeiculo);
        VeiculosAlugados veiculosAlugados = new VeiculosAlugados(usuario,veiculo,new Date(2020,04,30),3);
        usuario.adicionarVeiculoAlugado(veiculosAlugados);

        System.out.println(usuario.getHistorioVeiculosAlugados());
        //corrigir as datas iniciais e finais

        EntityManager entityManager = JPAUtil.getEntityManager();
        LoginDAO loginDAO = new LoginDAO(entityManager);
        UsuariosDAO usuarioDAO = new UsuariosDAO(entityManager);
        VeiculosDAO veiculosDAO = new VeiculosDAO(entityManager);
        CategoriasVeiculoDAO categoriasVeiculoDAO = new CategoriasVeiculoDAO(entityManager);
        CategoriasUsuarioDAO categoriasUsuarioDAO = new CategoriasUsuarioDAO(entityManager);
        VeiculosAlugadosDAO veiculosAlugadosDAO = new VeiculosAlugadosDAO(entityManager);

        entityManager.getTransaction().begin();
        usuario.adicionarVeiculoAlugado(veiculosAlugados);
        categoriasVeiculoDAO.cadastrar(categoriasVeiculo);
        veiculosDAO.cadastrar(veiculo);
        loginDAO.cadastrar(login);

        categoriasUsuarioDAO.cadastrar(categoriasUsuario);
        usuarioDAO.cadastrar(usuario);
        veiculosAlugadosDAO.cadastrar(veiculosAlugados);

        entityManager.getTransaction().commit();
        entityManager.close();
    }




}
