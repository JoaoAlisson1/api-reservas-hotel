package br.csi.Dormez.controller;

import br.csi.Dormez.model.Funcionario;
import br.csi.Dormez.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private FuncionarioService service;
    public FuncionarioController(FuncionarioService service) {this.service = service;}

    @GetMapping("/listar")
    public List<Funcionario> listar() {
        return service.listar();
    }

    @GetMapping("/uuid/{uuid}")
    public Funcionario funcionario(@PathVariable String uuid) {
        return this.service.buscarPorUUID(uuid);
    }

    @PostMapping()
    public ResponseEntity<Funcionario> salvar(@RequestBody @Valid Funcionario funcionario, UriComponentsBuilder uriBuilder) {
        this.service.salvar(funcionario);
        URI uri = uriBuilder.path("/funcionario/{uuid}").buildAndExpand(funcionario.getUuid()).toUri();
        return ResponseEntity.created(uri).body(funcionario);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Funcionario> atualizarUUID(@RequestBody @Valid Funcionario funcionario, @PathVariable String uuid) {
        funcionario.setUuid(UUID.fromString(uuid));
        this.service.atualizarUUID(funcionario);
        return ResponseEntity.ok(funcionario);
    }

    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<Funcionario> deletarUUID(@PathVariable String uuid) {

        this.service.deletarUUID(uuid);
        return ResponseEntity.noContent().build();
    }
}
