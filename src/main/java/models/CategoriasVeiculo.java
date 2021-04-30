package models;

import javax.persistence.*;

@Entity
@Table(name = "categorias_veiculo")
public class CategoriasVeiculo {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nome;

        public CategoriasVeiculo() {
        }

    @Override
    public String toString() {
        return "Categorias: " +
                "id = " + id +
                ", nome = " + nome;
    }

    public CategoriasVeiculo(String nome) {
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

}
