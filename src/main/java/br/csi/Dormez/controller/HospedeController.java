package br.csi.Dormez.controller;

import br.csi.Dormez.DTO.HospedeRequestDTO;
import br.csi.Dormez.DTO.HospedeResponseDTO;
import br.csi.Dormez.DTO.mapper.HospedeMapper;
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
    public List<HospedeResponseDTO> listar() {
        return service.listar().stream().map(HospedeMapper::toResponseDTO).toList();
    }

    @Operation(summary = "Criar um novo hospede")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hospede criado com sucesso",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Hospede.class))),
            @ApiResponse(responseCode = "400" , description = "Dados inválidos fornecidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<HospedeResponseDTO> salvar(@RequestBody @Valid HospedeRequestDTO dto, UriComponentsBuilder uriBuilder) {
        Hospede hospede = service.salvar(HospedeMapper.toEntity(dto));
        HospedeResponseDTO response = HospedeMapper.toResponseDTO(hospede);
        URI uri = uriBuilder.path("/hospede/{uuid}").buildAndExpand(hospede.getUuid()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @Operation(summary = "Buscar hospede por UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hospede encontrado",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Hospede.class))),
            @ApiResponse(responseCode = "404", description = "Hospede não encontrado", content = @Content)
    })
    @GetMapping("/uuid/{uuid}")
    public ResponseEntity <HospedeResponseDTO> buscarPorUUID(@PathVariable String uuid) {
        Hospede hospede = this.service.buscarPorUUID(uuid);

        if (hospede == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 se não encontrado
        }

        HospedeResponseDTO responseDTO = HospedeMapper.toResponseDTO(hospede);
        return ResponseEntity.ok(responseDTO); // Retorna 200 OK com o DTO
    }

    @Operation(summary = "Buscar hospede por nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hospede encontrado",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Hospede.class))),
            @ApiResponse(responseCode = "404", description = "Hospede não encontrado", content = @Content)
    })
    @GetMapping("/nome/{nome}")
    public ResponseEntity<HospedeResponseDTO> buscarPorNome(@PathVariable String nome) {
        Hospede hospede =  this.service.buscarPorNome(nome);

        if (hospede == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(HospedeMapper.toResponseDTO(hospede));
    }

    @Operation(summary = "Buscar hospede por CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hospede encontrado",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Hospede.class))),
            @ApiResponse(responseCode = "404", description = "Hospede não encontrado", content = @Content)
    })
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<HospedeResponseDTO> buscarPorCpf(@PathVariable String cpf) {
        Hospede hospede = this.service.buscarPorCpf(cpf);

        if (hospede == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(HospedeMapper.toResponseDTO(hospede));
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
    public ResponseEntity<HospedeResponseDTO> atualizarUUID(@RequestBody @Valid HospedeRequestDTO dto, @PathVariable String uuid) {
        Hospede hospede = HospedeMapper.toEntity(dto);
        hospede.setUuid(UUID.fromString(uuid));
        Hospede atualizado = this.service.atualizarUUID(hospede);
        return ResponseEntity.ok(HospedeMapper.toResponseDTO(atualizado));
    }

    @Operation(summary = "Excluir Hospede pelo UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Hospede deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Hospede não encontrado")
    })
    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<Hospede> deletarPorUUID(@PathVariable String uuid) {
        Hospede hospede = service.buscarPorUUID(uuid);

        if (hospede == null) {
            return ResponseEntity.notFound().build(); // 404 se não encontrado
        }

        this.service.deletarPorUUID(uuid);

        return ResponseEntity.noContent().build();
    }
}
