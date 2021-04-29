package models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    @Column(name = "data_cadastro")
    private LocalDate dataCadastro = LocalDate.now();
    private int status = 1;

    //necesario configurr quando existe relaciomento bidirecional
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<VeiculosAlugados> historioVeiculosAlugados = new ArrayList<>();

    @ManyToOne
    private CategoriasUsuario categoria;

    @OneToOne
    private Login login;

    public Usuarios() {
    }

    public Usuarios(String nome, String cpf, CategoriasUsuario categoria, Login login) {
        this.nome = nome;
        this.cpf = cpf;
        this.categoria = categoria;
        this.login = login;
    }

    public void adicionarVeiculoAlugado(VeiculosAlugados veiculosAlugados){
        veiculosAlugados.setUsuario(this);
        this.historioVeiculosAlugados.add(veiculosAlugados);
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

    public List<VeiculosAlugados> getHistorioVeiculosAlugados() {
        return historioVeiculosAlugados;
    }

    public void setHistorioVeiculosAlugados(List<VeiculosAlugados> historioVeiculosAlugados) {
        this.historioVeiculosAlugados = historioVeiculosAlugados;
    }

    public CategoriasUsuario getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriasUsuario categoria) {
        this.categoria = categoria;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}


