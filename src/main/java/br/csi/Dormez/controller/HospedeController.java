package br.csi.Dormez.controller;

import br.csi.Dormez.model.Hospede;
import br.csi.Dormez.service.HospedeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hospede")
public class HospedeController {

    private HospedeService service;

    public HospedeController(HospedeService service) {this.service = service;}

    @GetMapping("/listar")
    public List<Hospede> listar() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<Hospede> salvar(@RequestBody @Valid Hospede hospede, UriComponentsBuilder uriBuilder) {
        this.service.salvar(hospede);
        URI uri = uriBuilder.path("/hospede/{uuid}").buildAndExpand(hospede.getUuid()).toUri();
        return ResponseEntity.created(uri).body(hospede);
    }

    @GetMapping("/uuid/{uuid}")
    public Hospede hospede(@PathVariable String uuid) {
        return this.service.buscarPorUUID(uuid);
    }

    @GetMapping("/nome/{nome}")
    public Hospede buscarPorNome(@PathVariable String nome) {
        return this.service.buscarPorNome(nome);
    }

    @GetMapping("/cpf/{cpf}")
    public Hospede buscarPorCpf(@PathVariable String cpf) {
        return this.service.buscarPorCpf(cpf);
    }

    @PutMapping("/uuid/{uuid}")
    public ResponseEntity<Hospede> atualizarUUID(@RequestBody @Valid Hospede hospede, @PathVariable String uuid) {
        hospede.setUuid(UUID.fromString(uuid));
        this.service.atualizarUUID(hospede);
        return ResponseEntity.ok(hospede);
    }

    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<Hospede> deletarPorUUID(@PathVariable String uuid) {
        this.service.deletarPorUUID(uuid);
        return ResponseEntity.noContent().build();
    }
}
