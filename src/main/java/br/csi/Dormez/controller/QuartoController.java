package br.csi.Dormez.controller;

import br.csi.Dormez.model.Quarto;
import br.csi.Dormez.service.QuartoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/quarto")
@Tag(name = "Quartos", description = "Path relacionado a operações de quarto")
public class QuartoController {

    private QuartoService service;
    public QuartoController(QuartoService service) {this.service = service;}

    @Operation(summary = "Listar todos os quartos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Quarto.class))),
    })
    @GetMapping("/listar")
    public List<Quarto> listar() {
        return service.listar();
    }

    @Operation(summary = "Criar um novo quarto", description = "Cria um novo quarto e o adiciona a lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Quarto criado com sucesso",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Quarto.class))),
            @ApiResponse(responseCode = "404", description = "Dados inválidos fornecidos", content = @Content)
    })
    @PostMapping()
    public ResponseEntity<Quarto> salvar(@RequestBody @Valid Quarto quarto, UriComponentsBuilder uriBuilder) {
        this.service.salvar(quarto);
        URI uri = uriBuilder.path("/quarto/{id}").buildAndExpand(quarto.getId()).toUri();
        return ResponseEntity.created(uri).body(quarto);
    }

    @Operation(summary = "Atualizar quarto pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quarto atualizado com sucesso",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Quarto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Quarto não encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Quarto> atualizar(@RequestBody @Valid Quarto quarto, @PathVariable Long id) {
        this.service.atualizar(quarto, id);
        return ResponseEntity.ok().body(quarto);
    }

    @Operation(summary = "Buscar quarto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quarto encontrado",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Quarto.class))),
            @ApiResponse(responseCode = "404", description = "Quarto não encontrado", content = @Content)
    })
    @GetMapping("/id/{id}")
    public Quarto buscarPorId(@PathVariable Long id) {
        return this.service.buscarPorId(id);
    }


    @Operation(summary = "Buscar quarto por número")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quarto encontrado",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Quarto.class))),
            @ApiResponse(responseCode = "404", description = "Quarto não encontrado", content = @Content)
    })
    @GetMapping("/numero/{numero}")
    public Quarto buscarPorNumero(@PathVariable int numero) {
        return service.buscarPorNumero(numero);
    }

    @Operation(summary = "Excluir quarto pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Quarto deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Quarto não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Quarto> deletar(@PathVariable Long id) {

        this.service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
