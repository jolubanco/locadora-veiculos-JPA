package models;

import javax.persistence.*;

@Entity
@Table(name = "funcionarios")
public class Funcionarios extends Autenticavel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int status = 1;

    @ManyToOne
    private CategoriasFuncionario categoria;

    @OneToOne
    private Login login;

    public Funcionarios(){}

    public Funcionarios(String nome, CategoriasFuncionario categoria, Login login) {
        this.nome = nome;
        this.categoria = categoria;
        this.login = login;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CategoriasFuncionario getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriasFuncionario categoria) {
        this.categoria = categoria;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
