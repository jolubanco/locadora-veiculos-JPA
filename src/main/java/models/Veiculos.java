package models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "veiculos")
public class Veiculos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //verificar a strategy
    private Long id;
    private String marca;
    private String placa;
    private String modelo;
    private int numeroPortas;
    private BigDecimal valor;
    private String cor;
    private int ano;
    private LocalDate dataCadastro = LocalDate.now();
    @ManyToOne
    private CategoriasVeiculo categoria;
    //@Column(nullable = false, columnDefinition = "TINYINT(1)")
    private int status = 1; //corrigir o formato, está estranho no MySQL

    public Veiculos(){}

    public Veiculos (String marca, String placa, BigDecimal valor, CategoriasVeiculo categoria){
        this.marca = marca;
        this.placa = placa;
        this.valor = valor;
        this.categoria = categoria;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getNumeroPortas() {
        return numeroPortas;
    }

    public void setNumeroPortas(int numeroPortas) {
        this.numeroPortas = numeroPortas;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public CategoriasVeiculo getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriasVeiculo categoria) {
        this.categoria = categoria;
    }
}
