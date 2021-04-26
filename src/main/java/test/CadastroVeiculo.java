package test;

import dao.CategoriasVeiculoDAO;
import dao.VeiculosDAO;
import models.CategoriasVeiculo;
import models.Veiculos;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroVeiculo {

    public static void main(String[] args) {
        CategoriasVeiculo categoriasVeiculo = new CategoriasVeiculo("Carro");
        Veiculos veiculo = new Veiculos("Renaut","AAA-1010",new BigDecimal("35000"),categoriasVeiculo);

        EntityManager entityManager = JPAUtil.getEntityManager();
        VeiculosDAO veiculosDAO = new VeiculosDAO(entityManager);
        CategoriasVeiculoDAO categoriasVeiculoDAO = new CategoriasVeiculoDAO(entityManager);

        entityManager.getTransaction().begin();
        categoriasVeiculoDAO.cadastrar(categoriasVeiculo);
        veiculosDAO.cadastrar(veiculo);
        entityManager.getTransaction().commit();
        entityManager.close();
    }



}
