package br.csi.Dormez.controller;

import br.csi.Dormez.DTO.ReservaRequestDTO;
import br.csi.Dormez.DTO.ReservaResponseDTO;
import br.csi.Dormez.DTO.mapper.ReservaMapper;
import br.csi.Dormez.infra.TratadorDeErros;
import br.csi.Dormez.model.Reserva;
import br.csi.Dormez.service.ReservaService;
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
@RequestMapping("/reserva")
@Tag(name = "Reservas", description = "Path relacionado a operações de reserva")
public class ReservaController {

    private final ReservaService service;


    public ReservaController(ReservaService service) {
        this.service = service;

    }

    @Operation(summary = "Listar todas as reservas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Reserva.class))),
    })
    @GetMapping("/listar")
    public List<ReservaResponseDTO> listar() {
        return service.listar().stream().map(ReservaMapper::toDTO).toList();
    }

    @Operation(summary = "Buscar reserva por UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Reserva.class))),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrada", content = @Content)
    })
    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<ReservaResponseDTO> buscarPorUUID(@PathVariable String uuid) {
        Reserva reserva = this.service.buscarPorUUID(uuid);

        return ResponseEntity.ok(ReservaMapper.toDTO(reserva));
    }

    @Operation(summary = "Criar uma nova reserva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reserva criada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Reserva.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ReservaResponseDTO> salvar(@RequestBody @Valid ReservaRequestDTO dto, UriComponentsBuilder uriBuilder) {

        var quarto = service.buscarQuarto(dto.getQuartoId());
        var funcionario = service.buscarFuncionario(dto.getFuncionarioId());

        Reserva reserva = ReservaMapper.toEntity(dto, funcionario, quarto, null);
        Reserva reservaSalva = service.salvar(reserva, dto.getHospedeUUIDs());

        URI uri = uriBuilder.path("/reserva/uuid/{uuid}")
                .buildAndExpand(reservaSalva.getUuid()).toUri();

        return ResponseEntity.created(uri).body(ReservaMapper.toDTO(reservaSalva));
    }

    @Operation(summary = "Atualizar reserva pelo UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva atualizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Reserva.class))),
            @ApiResponse(responseCode = "400" , description = "Dados inválidos fornecidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrada", content = @Content)
    })
    @PutMapping("/uuid/{uuid}")
    public ResponseEntity<TratadorDeErros.MensagemSucesso> atualizarUUID(@RequestBody @Valid ReservaRequestDTO dto, @PathVariable String uuid) {
        // Busca entidades relacionadas
        var quarto = service.buscarQuarto(dto.getQuartoId());
        var funcionario = service.buscarFuncionario(dto.getFuncionarioId());

        // Converte DTO para entidade
        Reserva reserva = ReservaMapper.toEntity(dto, funcionario, quarto, null);
        reserva.setUuid(UUID.fromString(uuid));

        // Atualiza reserva com hóspedes
        service.atualizarUUID(reserva, dto.getHospedeUUIDs());

        return ResponseEntity.ok(new TratadorDeErros.MensagemSucesso("Reserva atualizada com sucesso!"));
    }

    @Operation(summary = "Excluir reserva pelo UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Reserva deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrado", content = @Content)
    })
    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity deletarUUID(@PathVariable String uuid) {

        this.service.deletarUUID(uuid);
        return ResponseEntity.noContent().build();

    }

}
