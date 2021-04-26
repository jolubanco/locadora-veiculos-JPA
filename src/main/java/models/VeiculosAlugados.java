package models;

import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "veiculos_alugados")
public class VeiculosAlugados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Clientes cliente;
    @ManyToOne
    private Veiculos veiculo;
    private LocalDate dataInicio;
    private Date dataFinal;
    private BigDecimal valorDiaria;
    private BigDecimal valorLocacao;
    private int status = 1;

    public VeiculosAlugados(){}

    public VeiculosAlugados(Clientes cliente, Veiculos veiculo, Date dataFinal) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataFinal = dataFinal;
        this.dataInicio = LocalDate.now();
    }

    @Override
    public String toString() {
        return "VeiculosAlugados{" +
                "id=" + id +
                ", cliente=" + cliente + //recuperar apenas o cpf
                ", veiculo=" + veiculo + //recuperar apenas a placa
                ", dataInicio=" + dataInicio +
                ", dataFinal=" + dataFinal +
                ", valorDiaria=" + valorDiaria +
                ", valorLocacao=" + valorLocacao +
                ", status=" + status +
                '}';
    }

    public BigDecimal getValorLocacao() {
        return valorLocacao;
    }

    public void setValorLocacao(BigDecimal valorLocacao) {
        this.valorLocacao = valorLocacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Veiculos getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculos veiculo) {
        this.veiculo = veiculo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
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
}
