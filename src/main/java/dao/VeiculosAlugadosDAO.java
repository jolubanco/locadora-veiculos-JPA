package dao;


import models.VeiculosAlugados;

import javax.persistence.EntityManager;
import java.util.List;

public class VeiculosAlugadosDAO {

    private EntityManager entityManager;

    public VeiculosAlugadosDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void cadastrar(VeiculosAlugados veiculosAlugado){
        entityManager.persist(veiculosAlugado);
        System.out.println("Locação realizada com sucesso!");
    }

    public List<VeiculosAlugados> buscarTodos(){
        String jpql = "SELECT v FROM VeiculosAlugados v";
        return entityManager.createQuery(jpql,VeiculosAlugados.class).getResultList();
    }

    public void desativarLocacao(Long id){
        String jpql = "UPDATE VeiculosAlugados SET status = 0 WHERE id = :idInformado";
        entityManager.createQuery(jpql).setParameter("idInformado",id).executeUpdate();
        System.out.println("Locação destativada com Sucesso!");
    }

    public void desativarCliente(String cpf){
        String jpql = "UPDATE Usuarios SET status = 0 WHERE cpf = :cpfInformado";
        entityManager.createQuery(jpql).setParameter("cofInformado",cpf).executeUpdate();
        System.out.println("Cliente Desativado com Sucesso!");
    }
}
