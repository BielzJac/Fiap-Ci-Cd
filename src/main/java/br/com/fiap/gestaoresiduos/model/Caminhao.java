package br.com.fiap.gestaoresiduos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_caminhoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Caminhao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "localizacao_atual")
    private String localizacaoAtual;

    @Column(name = "status")
    private String status;
}
