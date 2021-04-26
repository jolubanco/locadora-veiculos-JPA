package test;

import dao.VeiculosAlugadosDAO;
import models.VeiculosAlugados;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class SelectVeiculosAlugados {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        VeiculosAlugadosDAO veiculosAlugadosDAO = new VeiculosAlugadosDAO(entityManager);

        List<VeiculosAlugados> listaVeiculosAlugados = veiculosAlugadosDAO.buscarTodos();
        listaVeiculosAlugados.forEach(p -> System.out.println(p.toString()));
    }
}
