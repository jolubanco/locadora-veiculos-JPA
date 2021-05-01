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
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuarios usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    private Veiculos veiculo;
    @Column(name = "data_inicio")
    private LocalDate dataInicio = LocalDate.now();
    @Column(name = "data_final")
    private Date dataFinal;
    @Column(name = "valor_diaria")
    private BigDecimal valorDiaria;
    @Column(name = "valor_locacao")
    private BigDecimal valorLocacao;
    private int status = 1;

    public VeiculosAlugados(){}

    public VeiculosAlugados(Usuarios usuario, Veiculos veiculo, Date dataFinal, int numeroDiarias) {
        this.usuario = usuario;
        this.veiculo = veiculo;
        this.dataFinal = dataFinal;
        this.valorDiaria = veiculo.getValorDiaria();
        this.valorLocacao = veiculo.getValorDiaria().multiply(new BigDecimal(numeroDiarias)); // o correto é colocar o numero de diarias
    }

    @Override
    public String toString() {
        return "Veiculo Alugado: " +
                "id = " + id +
                ", cliente = " + usuario.getCpf() + //USEI RELACIONAMENTO BIDIRECIONAL
                ", veiculo = " + veiculo.getPlaca() + //recuperar apenas a placa
                ", dataInicio = " + dataInicio +
                ", dataFinal = " + dataFinal +
                ", valorDiaria = " + valorDiaria +
                ", valorLocacao = " + valorLocacao +
                ", status = " + status;
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

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
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
