package br.com.fiap.gestaoresiduos.service;

import br.com.fiap.gestaoresiduos.model.Recipiente;
import br.com.fiap.gestaoresiduos.repository.RecipienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipienteService {

    @Autowired
    private RecipienteRepository recipienteRepository;

    public List<Recipiente> getAllRecipientes() {
        return recipienteRepository.findAll();
    }

    public Recipiente saveRecipiente(Recipiente recipiente) {
        return recipienteRepository.save(recipiente);
    }

    public Recipiente getRecipienteById(Long id) {
        return recipienteRepository.findById(id).orElse(null);
    }

    public Recipiente updateRecipiente(Long id, Recipiente recipiente) {
        Recipiente existingRecipiente = recipienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Recipiente não encontrado"));
        existingRecipiente.setLocalizacao(recipiente.getLocalizacao());
        existingRecipiente.setCapacidade(recipiente.getCapacidade());
        existingRecipiente.setOcupacao(recipiente.getOcupacao());
        return recipienteRepository.save(existingRecipiente);
    }

    public void deleteRecipiente(Long id) {
        recipienteRepository.deleteById(id);
    }
}
