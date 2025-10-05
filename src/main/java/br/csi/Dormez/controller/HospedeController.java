package br.csi.Dormez.controller;

import br.csi.Dormez.model.Hospede;
import br.csi.Dormez.service.HospedeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hospede")
public class HospedeController {

    HospedeService service;

    HospedeController(HospedeService service) {this.service = service;}

    @GetMapping("/listar")
    public List<Hospede> listar() {
        return service.listar();
    }

    @PostMapping
    public void salvar(@RequestBody Hospede hospede) {
        service.salvar(hospede);
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
    public void atualizarUUID(@RequestBody Hospede hospede, @PathVariable String uuid) {
        hospede.setUuid(UUID.fromString(uuid));
        this.service.atualizarUUID(hospede);
    }

    @DeleteMapping("/uuid/{uuid}")
    public void deletarPorUUID(@PathVariable String uuid) {
        this.service.deletarPorUUID(uuid);
    }
}
