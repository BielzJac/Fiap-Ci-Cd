package br.com.fiap.gestaoresiduos.controller;

import br.com.fiap.gestaoresiduos.dto.ColetaDto;
import br.com.fiap.gestaoresiduos.model.Coleta;
import br.com.fiap.gestaoresiduos.model.Caminhao;
import br.com.fiap.gestaoresiduos.model.Recipiente;
import br.com.fiap.gestaoresiduos.service.ColetaService;
import br.com.fiap.gestaoresiduos.service.CaminhaoService;
import br.com.fiap.gestaoresiduos.service.RecipienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/coletas")
public class ColetaController {

    @Autowired
    private ColetaService coletaService;

    @Autowired
    private CaminhaoService caminhaoService;

    @Autowired
    private RecipienteService recipienteService;

    @GetMapping("/todos")
    @ResponseStatus(HttpStatus.OK)
    public List<ColetaDto> getAllColetas() {
        return coletaService.getAllColetas().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/adicionar")
    @ResponseStatus(HttpStatus.CREATED)
    public ColetaDto saveColeta(@RequestBody ColetaDto coletaDto) {
        Coleta coleta = convertToEntity(coletaDto);
        return convertToDto(coletaService.saveColeta(coleta));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ColetaDto getColetaById(@PathVariable Long id) {
        return convertToDto(coletaService.getColetaById(id));
    }

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ColetaDto updateColeta(@PathVariable Long id, @RequestBody ColetaDto coletaDto) {
        Coleta coleta = convertToEntity(coletaDto);
        return convertToDto(coletaService.updateColeta(id, coleta));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteColeta(@PathVariable Long id) {
        coletaService.deleteColeta(id);
    }

    private ColetaDto convertToDto(Coleta coleta) {
        ColetaDto coletaDto = new ColetaDto();
        coletaDto.setId(coleta.getId());
        coletaDto.setCaminhaoId(coleta.getCaminhao().getId());
        coletaDto.setRecipienteId(coleta.getRecipiente().getId());
        coletaDto.setData(coleta.getData());
        return coletaDto;
    }

    private Coleta convertToEntity(ColetaDto coletaDto) {
        Coleta coleta = new Coleta();
        coleta.setCaminhao(caminhaoService.getCaminhaoById(coletaDto.getCaminhaoId()));
        coleta.setRecipiente(recipienteService.getRecipienteById(coletaDto.getRecipienteId()));
        coleta.setData(coletaDto.getData());
        return coleta;
    }
}
