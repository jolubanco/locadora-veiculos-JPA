package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Clientes extends Autenticavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private int status = 1;
    @OneToOne
    private Login login;
    //TESTE - rever a necessidade da lista
    @OneToMany(mappedBy= "cliente", cascade = CascadeType.ALL) //necesario configurr quando existe relaciomento bidirecional
    private List<VeiculosAlugados> historioVeiculosAlugados = new ArrayList<>();
    //

    public Clientes(){
    }

    public Clientes(String cpf, Login login){
        this.cpf=cpf;
        this.login = login;
    }

    //rever metodo
    public void adicionarVecAlugado(VeiculosAlugados vecAlugados){
        vecAlugados.setCliente(this);
        this.historioVeiculosAlugados.add(vecAlugados);
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
