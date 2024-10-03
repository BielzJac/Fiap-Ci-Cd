package br.com.fiap.gestaoresiduos.controller;

import br.com.fiap.gestaoresiduos.dto.CaminhaoDto;
import br.com.fiap.gestaoresiduos.model.Caminhao;
import br.com.fiap.gestaoresiduos.service.CaminhaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/caminhoes")
public class CaminhaoController {

    @Autowired
    private CaminhaoService caminhaoService;

    @GetMapping("/todos")
    @ResponseStatus(HttpStatus.OK)
    public List<CaminhaoDto> getAllCaminhoes() {
        return caminhaoService.getAllCaminhoes().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/adicionar")
    @ResponseStatus(HttpStatus.CREATED)
    public CaminhaoDto saveCaminhao(@RequestBody CaminhaoDto caminhaoDto) {
        Caminhao caminhao = convertToEntity(caminhaoDto);
        return convertToDto(caminhaoService.saveCaminhao(caminhao));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CaminhaoDto getCaminhaoById(@PathVariable Long id) {
        return convertToDto(caminhaoService.getCaminhaoById(id));
    }

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CaminhaoDto updateCaminhao(@PathVariable Long id, @RequestBody CaminhaoDto caminhaoDto) {
        Caminhao caminhao = convertToEntity(caminhaoDto);
        return convertToDto(caminhaoService.updateCaminhao(id, caminhao));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCaminhao(@PathVariable Long id) {
        caminhaoService.deleteCaminhao(id);
    }

    private CaminhaoDto convertToDto(Caminhao caminhao) {
        CaminhaoDto caminhaoDto = new CaminhaoDto();
        caminhaoDto.setId(caminhao.getId());
        caminhaoDto.setLocalizacaoAtual(caminhao.getLocalizacaoAtual());
        caminhaoDto.setStatus(caminhao.getStatus());
        return caminhaoDto;
    }

    private Caminhao convertToEntity(CaminhaoDto caminhaoDto) {
        Caminhao caminhao = new Caminhao();
        caminhao.setLocalizacaoAtual(caminhaoDto.getLocalizacaoAtual());
        caminhao.setStatus(caminhaoDto.getStatus());
        return caminhao;
    }
}
