package br.com.fiap.gestaoresiduos.service;

import br.com.fiap.gestaoresiduos.model.Caminhao;
import br.com.fiap.gestaoresiduos.repository.CaminhaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaminhaoService {

    @Autowired
    private CaminhaoRepository caminhaoRepository;

    public List<Caminhao> getAllCaminhoes() {
        return caminhaoRepository.findAll();
    }

    public Caminhao saveCaminhao(Caminhao caminhao) {
        return caminhaoRepository.save(caminhao);
    }

    public Caminhao getCaminhaoById(Long id) {
        return caminhaoRepository.findById(id).orElse(null);
    }

    public Caminhao updateCaminhao(Long id, Caminhao caminhao) {
        Caminhao existingCaminhao = caminhaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Caminhão não encontrado"));
        existingCaminhao.setLocalizacaoAtual(caminhao.getLocalizacaoAtual());
        existingCaminhao.setStatus(caminhao.getStatus());
        return caminhaoRepository.save(existingCaminhao);
    }

    public void deleteCaminhao(Long id) {
        caminhaoRepository.deleteById(id);
    }
}
