package br.com.fiap.gestaoresiduos.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_coletas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Coleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "caminhao_id", referencedColumnName = "id", nullable = false)
    private Caminhao caminhao;

    @ManyToOne
    @JoinColumn(name = "recipiente_id", referencedColumnName = "id", nullable = false)
    private Recipiente recipiente;

    @Column(name = "data")
    private LocalDate data;
}
