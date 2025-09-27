package br.csi.Dormez.controller;

import br.csi.Dormez.model.Funcionario;
import br.csi.Dormez.service.FuncionarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void salvar(@RequestBody Funcionario funcionario) {
        this.service.salvar(funcionario);
    }

    @PutMapping("/uuid")
    public void atualizarUUID(@RequestBody Funcionario funcionario) {
        this.service.atualizarUUID(funcionario);
    }

    @DeleteMapping("/uuid/{uuid}")
    public void deletarUUID(@PathVariable String uuid) {
        this.service.deletarUUID(uuid);
    }
}
