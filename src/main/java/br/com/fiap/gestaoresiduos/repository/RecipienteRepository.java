package br.com.fiap.gestaoresiduos.repository;

import br.com.fiap.gestaoresiduos.model.Recipiente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipienteRepository extends JpaRepository<Recipiente, Long> {
}
