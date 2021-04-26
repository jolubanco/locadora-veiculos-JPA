package test;

import dao.*;
import models.*;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Date;

public class CadastroVeiculoAlugado {
    public static void main(String[] args) {
        Login login = new Login("joao","2114");
        Clientes cliente = new Clientes("08326702943",login);
        CategoriasVeiculo categoriasVeiculo = new CategoriasVeiculo("Carro");
        Veiculos veiculo = new Veiculos("Renaut","AAA-1010",new BigDecimal("35000"),categoriasVeiculo);
        VeiculosAlugados veiculosAlugados = new VeiculosAlugados(cliente,veiculo,new Date(2020,04,30));
        //corrigir as datas iniciais e finais

        EntityManager entityManager = JPAUtil.getEntityManager();
        LoginDAO loginDAO = new LoginDAO(entityManager);
        ClientesDAO clientesDAO = new ClientesDAO(entityManager);
        VeiculosDAO veiculosDAO = new VeiculosDAO(entityManager);
        CategoriasVeiculoDAO categoriasVeiculoDAO = new CategoriasVeiculoDAO(entityManager);
        VeiculosAlugadosDAO veiculosAlugadosDAO = new VeiculosAlugadosDAO(entityManager);

        entityManager.getTransaction().begin();
        categoriasVeiculoDAO.cadastrar(categoriasVeiculo);
        veiculosDAO.cadastrar(veiculo);
        loginDAO.cadastrar(login);
        clientesDAO.cadastrar(cliente);
        veiculosAlugadosDAO.cadastrar(veiculosAlugados);
        entityManager.getTransaction().commit();
        entityManager.close();
    }




}
