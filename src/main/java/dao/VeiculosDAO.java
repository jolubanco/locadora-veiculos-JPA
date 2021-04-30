package dao;

import models.CategoriasVeiculo;
import models.Veiculos;

import javax.persistence.EntityManager;
import java.util.List;

public class VeiculosDAO {

    private EntityManager entityManager;

    public VeiculosDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Veiculos veiculo){
        entityManager.persist(veiculo);
        System.out.println("Veículo Cadastrado com Sucesso!");
    }

    public List<Veiculos> buscarTodos(){
        String jpql = "SELECT v FROM Veiculos v";
        return entityManager.createQuery(jpql,Veiculos.class).getResultList();
    }

    public Veiculos buscarPorPlaca(String placa){
        String jpql = "SELECT v FROM Veiculos v WHERE v.placa = :placaInformada";
        return entityManager.createQuery(jpql,Veiculos.class).setParameter("placaInformada",placa).getSingleResult();
    }
    //dúbida se recebo o objeto categoria ou o nome dela, se for o nome o usuario não deve escrever e sim selecionar e fazer categoria.getNome();
    public List<Veiculos> buscarPorCategoria(String categoria){
        String jpql = "SELECT v FROM Veiculos v WHERE v.categoria.nome = :categoriaInformada";
        return entityManager.createQuery(jpql,Veiculos.class).setParameter("categoriaInformada",categoria).getResultList();
    }

    public void desativarVeiculo(String placa){
        String jpql = "UPDATE Veiculos SET status = 0 WHERE placa = :placaInformada";
        entityManager.createQuery(jpql).setParameter("placaInformada",placa).executeUpdate();
        System.out.println("Veículo Desativado com Sucesso!");
    }
}
