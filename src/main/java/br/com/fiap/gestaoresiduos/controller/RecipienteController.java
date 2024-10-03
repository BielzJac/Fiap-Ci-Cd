package br.com.fiap.gestaoresiduos.controller;

import br.com.fiap.gestaoresiduos.dto.RecipienteDto;
import br.com.fiap.gestaoresiduos.model.Recipiente;
import br.com.fiap.gestaoresiduos.service.RecipienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recipientes")
public class RecipienteController {

    @Autowired
    private RecipienteService recipienteService;

    @GetMapping("/todos")
    @ResponseStatus(HttpStatus.OK)
    public List<RecipienteDto> getAllRecipientes() {
        return recipienteService.getAllRecipientes().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/adicionar")
    @ResponseStatus(HttpStatus.CREATED)
    public RecipienteDto saveRecipiente(@RequestBody RecipienteDto recipienteDto) {
        Recipiente recipiente = convertToEntity(recipienteDto);
        return convertToDto(recipienteService.saveRecipiente(recipiente));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RecipienteDto getRecipienteById(@PathVariable Long id) {
        return convertToDto(recipienteService.getRecipienteById(id));
    }

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RecipienteDto updateRecipiente(@PathVariable Long id, @RequestBody RecipienteDto recipienteDto) {
        Recipiente recipiente = convertToEntity(recipienteDto);
        return convertToDto(recipienteService.updateRecipiente(id, recipiente));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipiente(@PathVariable Long id) {
        recipienteService.deleteRecipiente(id);
    }

    private RecipienteDto convertToDto(Recipiente recipiente) {
        RecipienteDto recipienteDto = new RecipienteDto();
        recipienteDto.setId(recipiente.getId());
        recipienteDto.setLocalizacao(recipiente.getLocalizacao());
        recipienteDto.setCapacidade(recipiente.getCapacidade());
        recipienteDto.setOcupacao(recipiente.getOcupacao());
        return recipienteDto;
    }

    private Recipiente convertToEntity(RecipienteDto recipienteDto) {
        Recipiente recipiente = new Recipiente();
        recipiente.setLocalizacao(recipienteDto.getLocalizacao());
        recipiente.setCapacidade(recipienteDto.getCapacidade());
        recipiente.setOcupacao(recipienteDto.getOcupacao());
        return recipiente;
    }
}
