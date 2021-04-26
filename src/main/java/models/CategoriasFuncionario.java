package models;

import javax.persistence.*;

@Entity
@Table(name = "categorias_funcionario")
public class CategoriasFuncionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public CategoriasFuncionario(){}

    public CategoriasFuncionario(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
