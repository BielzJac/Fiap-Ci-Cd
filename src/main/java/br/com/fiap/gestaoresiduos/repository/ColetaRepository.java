package br.com.fiap.gestaoresiduos.repository;

import br.com.fiap.gestaoresiduos.model.Coleta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColetaRepository extends JpaRepository<Coleta, Long> {
}
