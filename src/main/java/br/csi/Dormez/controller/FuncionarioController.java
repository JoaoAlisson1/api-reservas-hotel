package br.csi.Dormez.controller;

import br.csi.Dormez.model.Funcionario;
import br.csi.Dormez.service.FuncionarioService;
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
import java.util.UUID;

@RestController
@RequestMapping("/funcionario")
@Tag(name = "Funcionários", description = "Path relacionado a operações de funcionários")
public class FuncionarioController {

    private FuncionarioService service;
    public FuncionarioController(FuncionarioService service) {this.service = service;}

    @Operation(summary = "Listar todos os funcionários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Funcionario.class))),
    })
    @GetMapping("/listar")
    public List<Funcionario> listar() {
        return service.listar();
    }

    @Operation(summary = "Buscar funcionário por UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário encontrado",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Funcionario.class))),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado", content = @Content)
    })
    @GetMapping("/uuid/{uuid}")
    public Funcionario funcionario(@PathVariable String uuid) {
        return this.service.buscarPorUUID(uuid);
    }

    @PostMapping()
    @Operation(summary = "Criar um novo funcionário", description = "Cria um novo funcionário e o adiciona a lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionário criado com sucesso",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Funcionario.class))),
            @ApiResponse(responseCode = "404", description = "Dados inválidos fornecidos", content = @Content)
    })
    public ResponseEntity<Funcionario> salvar(@RequestBody @Valid Funcionario funcionario, UriComponentsBuilder uriBuilder) {
        this.service.salvar(funcionario);
        URI uri = uriBuilder.path("/funcionario/{uuid}").buildAndExpand(funcionario.getUuid()).toUri();
        return ResponseEntity.created(uri).body(funcionario);
    }

    @Operation(summary = "Atualizar funcionário pelo UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Funcionario.class))),
            @ApiResponse(responseCode = "404", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado", content = @Content)
    })

    @PutMapping("/{uuid}")
    public ResponseEntity<Funcionario> atualizarUUID(@RequestBody @Valid Funcionario funcionario, @PathVariable String uuid) {
        funcionario.setUuid(UUID.fromString(uuid));
        this.service.atualizarUUID(funcionario);
        return ResponseEntity.ok(funcionario);
    }

    @Operation(summary = "Excluir funcionário pelo UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Funcionário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado", content = @Content)
    })

    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<Funcionario> deletarUUID(@PathVariable String uuid) {

        this.service.deletarUUID(uuid);
        return ResponseEntity.noContent().build();
    }
}
