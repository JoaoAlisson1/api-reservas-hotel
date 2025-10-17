package br.csi.Dormez.controller;

import br.csi.Dormez.model.Quarto;
import br.csi.Dormez.service.QuartoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/quarto")
public class QuartoController {

    private QuartoService service;
    public QuartoController(QuartoService service) {this.service = service;}

    @GetMapping("/listar")
    public List<Quarto> listar() {
        return service.listar();
    }

    @PostMapping()
    public ResponseEntity<Quarto> salvar(@RequestBody @Valid Quarto quarto, UriComponentsBuilder uriBuilder) {
        this.service.salvar(quarto);
        URI uri = uriBuilder.path("/quarto/{id}").buildAndExpand(quarto.getId()).toUri();
        return ResponseEntity.created(uri).body(quarto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quarto> atualizar(@RequestBody @Valid Quarto quarto, @PathVariable Long id) {
        this.service.atualizar(quarto, id);
        return ResponseEntity.ok().body(quarto);
    }

    @GetMapping("/id/{id}")
    public Quarto buscarPorId(@PathVariable Long id) {
        return this.service.buscarPorId(id);
    }

    @GetMapping("/numero/{numero}")
    public Quarto buscarPorNumero(@PathVariable int numero) {
        return service.buscarPorNumero(numero);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Quarto> deletar(@PathVariable Long id) {

        this.service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
