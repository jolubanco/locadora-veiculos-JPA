package models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints =
@UniqueConstraint(columnNames = "placa", name = "veiculos"))
public class Veiculos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //verificar a strategy
    private Long id;
    private String marca;
    @NotNull
    private String placa;
    private String modelo;
    @Column(name = "numero_portas")
    private int numeroPortas;
    private BigDecimal valor;
    @Column(name = "valor_diaria")
    private BigDecimal valorDiaria;
    private String cor;
    private int ano;
    @Column(name = "data_cadastro")
    private LocalDate dataCadastro = LocalDate.now();
    @ManyToOne (fetch = FetchType.LAZY) //faz com que o carregamento pelo banco só ocorra quando solicitado
    //o padrão é EAGER, sempre que seleciono o objeto ele carrega todos os relacionamentos
    //oneToOne e ManyToOne, gastando muita mememoria para algo que as vezes é desnecessário
    //BOA PRATICA É SEMPRE MUDAR PARA (fetch = FetchType.LAZY), PARA RELACIONAMENTOS @...ToOne
    private CategoriasVeiculo categoria;
    private int status = 1;

    @OneToMany(mappedBy= "veiculo", cascade = CascadeType.ALL)
    private List<VeiculosAlugados> historicoDeAlugueis = new ArrayList<>();

    public Veiculos(){}

    public Veiculos (String marca, String placa, BigDecimal valor, CategoriasVeiculo categoria){
        this.marca = marca;
        this.placa = placa;
        this.valor = valor;
        this.categoria = categoria;
        this.valorDiaria = valor.multiply(new BigDecimal("0.002"));
    }

    @Override
    public String toString() {
        return "Veiculos{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", numeroPortas=" + numeroPortas +
                ", valor=" + valor +
                ", cor='" + cor + '\'' +
                ", ano=" + ano +
                ", dataCadastro=" + dataCadastro +
                ", categoria=" + categoria.getNome() +
                ", status=" + status +
                ", historicoDeAlugueis=" + historicoDeAlugueis +
                '}';
    }

    public BigDecimal getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(BigDecimal valorDiaria) {
        this.valorDiaria = valorDiaria;
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
