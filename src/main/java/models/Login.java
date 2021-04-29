package models;

import com.sun.istack.NotNull;

import javax.persistence.*;
//a questao do usuario ser unico foi tratada, basta tratar a exception caso solicite criar um usuario com username repetido
@Entity
@Table(uniqueConstraints =
@UniqueConstraint(columnNames = "username", name = "login"))
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String username;
    private String senha;

    public Login(){
    }

    public Login(String usuario, String senha) {
        this.username = usuario;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return username;
    }

    public void setUsuario(String usuario) {
        this.username = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
