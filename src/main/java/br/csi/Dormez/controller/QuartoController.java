package br.csi.Dormez.controller;

import br.csi.Dormez.model.Quarto;
import br.csi.Dormez.service.QuartoService;
import org.springframework.web.bind.annotation.*;

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
    public void salvar(@RequestBody Quarto quarto) {
        service.salvar(quarto);
    }

    @PutMapping("/{id}")
    public void atualizar(@RequestBody Quarto quarto, @PathVariable Long id) {
        service.atualizar(quarto, id);
    }

    @GetMapping("/id/{id}")
    public Quarto buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/numero/{numero}")
    public Quarto buscarPorNumero(@PathVariable int numero) {
        return service.buscarPorNumero(numero);
    }



    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
