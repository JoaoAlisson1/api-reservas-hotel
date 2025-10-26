package br.csi.Dormez.controller;

import br.csi.Dormez.DTO.ReservaDTO;
import br.csi.Dormez.model.Funcionario;
import br.csi.Dormez.model.Quarto;
import br.csi.Dormez.model.Reserva;
import br.csi.Dormez.repository.FuncionarioRepository;
import br.csi.Dormez.repository.QuartoRepository;
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

@RestController
@RequestMapping("/reserva")
@Tag(name = "Reservas", description = "Path relacionado a operações de reserva")
public class ReservaController {

    private final ReservaService service;
    private final QuartoRepository quartoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public ReservaController(ReservaService service, QuartoRepository quartoRepository,
                             FuncionarioRepository funcionarioRepository) {
        this.service = service;
        this.quartoRepository = quartoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Operation(summary = "Listar todas as reservas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Reserva.class))),
    })
    @GetMapping("/listar")
    public List<Reserva> listar() {
        return service.listar();
    }

    @Operation(summary = "Buscar reserva por UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Reserva.class))),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrada", content = @Content)
    })
    @GetMapping("/uuid/{uuid}")
    public Reserva reserva(@PathVariable String uuid) {
        return this.service.buscarPorUUID(uuid);
    }

    @Operation(summary = "Criar uma nova reserva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reserva criada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Reserva.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Reserva> salvar(@RequestBody @Valid ReservaDTO dto, UriComponentsBuilder uriBuilder) {

        // Buscar quarto real
        Quarto quarto = quartoRepository.findById(dto.getQuartoId())
                .orElseThrow(() -> new RuntimeException("Quarto não encontrado"));

        // Buscar funcionário real
        Funcionario func = funcionarioRepository.findById(dto.getFuncionarioId())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        // Criar reserva
        Reserva reserva = new Reserva();
        reserva.setCheckIn(dto.getCheckIn());
        reserva.setCheckOut(dto.getCheckOut());
        reserva.setStatus(Enum.valueOf(br.csi.Dormez.model.StatusReserva.class, dto.getStatus()));
        reserva.setQuarto(quarto);
        reserva.setFuncionario(func);

        // Salvar reserva com hóspedes
        service.salvar(reserva, dto.getHospedeUUIDs());

        URI uri = uriBuilder.path("/reserva/uuid/{uuid}").buildAndExpand(reserva.getUuid()).toUri();
        return ResponseEntity.created(uri).body(reserva);
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
    public ResponseEntity<Reserva> atualizarUUID(@RequestBody @Valid ReservaDTO dto, @PathVariable String uuid) {

        // Buscar quarto real
        Quarto quarto = quartoRepository.findById(dto.getQuartoId())
                .orElseThrow(() -> new RuntimeException("Quarto não encontrado"));

        // Buscar funcionário real
        Funcionario func = funcionarioRepository.findById(dto.getFuncionarioId())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        // Criar reserva temporária para atualização
        Reserva reserva = new Reserva();
        reserva.setUuid(java.util.UUID.fromString(uuid));
        reserva.setCheckIn(dto.getCheckIn());
        reserva.setCheckOut(dto.getCheckOut());
        reserva.setStatus(Enum.valueOf(br.csi.Dormez.model.StatusReserva.class, dto.getStatus()));
        reserva.setQuarto(quarto);
        reserva.setFuncionario(func);

        // Atualizar reserva com hóspedes
        service.atualizarUUID(reserva, dto.getHospedeUUIDs());

        return ResponseEntity.ok(reserva);
    }

    @Operation(summary = "Excluir reserva pelo UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Reserva deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrado", content = @Content)
    })
    @DeleteMapping("/uuid/{uuid}")
    public void deletarUUID(@PathVariable String uuid) {
        this.service.deletarUUID(uuid);
    }

}
