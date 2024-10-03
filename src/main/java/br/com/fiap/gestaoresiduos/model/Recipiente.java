package br.com.fiap.gestaoresiduos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_recipientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Recipiente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "localizacao")
    private String localizacao;

    @Column(name = "capacidade")
    private int capacidade;

    @Column(name = "ocupacao")
    private int ocupacao;
}
