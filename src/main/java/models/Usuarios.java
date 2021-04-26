package models;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private int status = 1;

    @ManyToOne
    private CategoriasUsuario categoria;

    @OneToOne
    private Login login;

    public Usuarios(){}

    public Usuarios(String nome, String cpf, CategoriasUsuario categoria, Login login) {
        this.nome = nome;
        this.cpf = cpf;
        this.categoria = categoria;
        this.login = login;
    }
}
