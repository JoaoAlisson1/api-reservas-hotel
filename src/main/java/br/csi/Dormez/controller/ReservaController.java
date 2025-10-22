package br.csi.Dormez.controller;

import br.csi.Dormez.DTO.ReservaDTO;
import br.csi.Dormez.model.Funcionario;
import br.csi.Dormez.model.Quarto;
import br.csi.Dormez.model.Reserva;
import br.csi.Dormez.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserva")
@Tag(name = "Reservas", description = "Path relacionado a operações de reserva")
public class ReservaController {

    private ReservaService service;
    public ReservaController(ReservaService service) {this.service = service;}

    @Operation(summary = "Listar todas as reservas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Reserva.class))),
    })
    @GetMapping("/listar")
    public List<Reserva> listar() {return service.listar();}

    @Operation(summary = "Buscar reserva por UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva encontrada",
               content = @Content(mediaType = "application/json",
               schema = @Schema(implementation = Reserva.class))),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrada", content = @Content)
    })
    @GetMapping("/uuid/{uuid}")
    public Reserva reserva(@PathVariable String uuid) {return this.service.buscarPorUUID(uuid);}

    @Operation(summary = "Criar uma nova reserva", description = "Cria uma nova reserva e adiciona a lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reserva criada com sucesso",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Reserva.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content)
    })
    @PostMapping
    public void salvar(@RequestBody ReservaDTO dto) {

        Reserva reserva = new Reserva();

        reserva.setCheckIn(dto.getCheckIn());
        reserva.setCheckOut(dto.getCheckOut());
        reserva.setValorTotal(dto.getValorTotal());
        reserva.setStatus(Enum.valueOf(br.csi.Dormez.model.StatusReserva.class, dto.getStatus()));

        Funcionario func = new Funcionario();
        func.setId(dto.getFuncionarioId());
        reserva.setFuncionario(func);

        Quarto quarto = new Quarto();
        quarto.setId(dto.getQuartoId());
        reserva.setQuarto(quarto);

        this.service.salvar(reserva, dto.getHospedeUUIDs());}

    @Operation(summary = "Atualizar reserva pelo UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva atualizada com sucesso",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Reserva.class))),
            @ApiResponse(responseCode = "400" , description = "Dados inválidos fornecidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrada", content = @Content)
    })
    @PutMapping("/{uuid}")
    public void atualizarUUID(@RequestBody ReservaDTO dto, @PathVariable String uuid) {

        Reserva reserva = new Reserva();

        reserva.setUuid(java.util.UUID.fromString(uuid));

        reserva.setCheckIn(dto.getCheckIn());
        reserva.setCheckOut(dto.getCheckOut());
        reserva.setValorTotal(dto.getValorTotal());
        reserva.setStatus(Enum.valueOf(br.csi.Dormez.model.StatusReserva.class, dto.getStatus()));

        Funcionario func = new Funcionario();
        func.setId(dto.getFuncionarioId());
        reserva.setFuncionario(func);

        Quarto quarto = new Quarto();
        quarto.setId(dto.getQuartoId());
        reserva.setQuarto(quarto);

        // Passa a reserva e a lista de hospede UUIDs para o service
        this.service.atualizarUUID(reserva, dto.getHospedeUUIDs());}

    @Operation(summary = "Excluir reserva pelo UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Reserva deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrado", content = @Content)
    })

    @DeleteMapping("/uuid/{uuid}")
    public void deletarUUID(@PathVariable String uuid) {this.service.deletarUUID(uuid);}
}
