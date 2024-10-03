package br.com.fiap.gestaoresiduos.service;

import br.com.fiap.gestaoresiduos.model.Coleta;
import br.com.fiap.gestaoresiduos.repository.ColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColetaService {

    @Autowired
    private ColetaRepository coletaRepository;

    public List<Coleta> getAllColetas() {
        return coletaRepository.findAll();
    }

    public Coleta saveColeta(Coleta coleta) {
        return coletaRepository.save(coleta);
    }

    public Coleta getColetaById(Long id) {
        return coletaRepository.findById(id).orElse(null);
    }

    public Coleta updateColeta(Long id, Coleta coleta) {
        Coleta existingColeta = coletaRepository.findById(id).orElseThrow(() -> new RuntimeException("Coleta não encontrada"));
        existingColeta.setCaminhao(coleta.getCaminhao());
        existingColeta.setRecipiente(coleta.getRecipiente());
        existingColeta.setData(coleta.getData());
        return coletaRepository.save(existingColeta);
    }

    public void deleteColeta(Long id) {
        coletaRepository.deleteById(id);
    }
}
