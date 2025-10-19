package br.csi.Dormez.controller;

import br.csi.Dormez.model.Hospede;
import br.csi.Dormez.service.HospedeService;
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
@RequestMapping("/hospede")
@Tag(name = "Hospedes", description = "Path relacionado a operações de hospedes")
public class HospedeController {

    private HospedeService service;

    public HospedeController(HospedeService service) {this.service = service;}

    @Operation(summary = "Listar todos os hospedes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Hospede.class))),
    })
    @GetMapping("/listar")
    public List<Hospede> listar() {
        return service.listar();
    }

    @Operation(summary = "Criar um novo hospede")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hospede criado com sucesso",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Hospede.class))),
            @ApiResponse(responseCode = "400" , description = "Dados inválidos fornecidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Hospede> salvar(@RequestBody @Valid Hospede hospede, UriComponentsBuilder uriBuilder) {
        this.service.salvar(hospede);
        URI uri = uriBuilder.path("/hospede/{uuid}").buildAndExpand(hospede.getUuid()).toUri();
        return ResponseEntity.created(uri).body(hospede);
    }

    @Operation(summary = "Buscar hospede por UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hospede encontrado",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Hospede.class))),
            @ApiResponse(responseCode = "404", description = "Hospede não encontrado", content = @Content)
    })
    @GetMapping("/uuid/{uuid}")
    public Hospede hospede(@PathVariable String uuid) {
        return this.service.buscarPorUUID(uuid);
    }

    @Operation(summary = "Buscar hospede por nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hospede encontrado",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Hospede.class))),
            @ApiResponse(responseCode = "404", description = "Hospede não encontrado", content = @Content)
    })
    @GetMapping("/nome/{nome}")
    public Hospede buscarPorNome(@PathVariable String nome) {
        return this.service.buscarPorNome(nome);
    }

    @Operation(summary = "Buscar hospede por CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hospede encontrado",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Hospede.class))),
            @ApiResponse(responseCode = "404", description = "Hospede não encontrado", content = @Content)
    })
    @GetMapping("/cpf/{cpf}")
    public Hospede buscarPorCpf(@PathVariable String cpf) {
        return this.service.buscarPorCpf(cpf);
    }

    @Operation(summary = "Atualizar hospede pelo UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hospede atualizado com sucesso",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Hospede.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Hospede não encontrado", content = @Content)
    })
    @PutMapping("/uuid/{uuid}")
    public ResponseEntity<Hospede> atualizarUUID(@RequestBody @Valid Hospede hospede, @PathVariable String uuid) {
        hospede.setUuid(UUID.fromString(uuid));
        this.service.atualizarUUID(hospede);
        return ResponseEntity.ok(hospede);
    }

    @Operation(summary = "Excluir Hospede pelo UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Hospede deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Hospede não encontrado")
    })
    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<Hospede> deletarPorUUID(@PathVariable String uuid) {
        this.service.deletarPorUUID(uuid);
        return ResponseEntity.noContent().build();
    }
}
