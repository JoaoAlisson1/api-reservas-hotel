package br.csi.Dormez.controller;

import br.csi.Dormez.DTO.ReservaDTO;
import br.csi.Dormez.model.Funcionario;
import br.csi.Dormez.model.Quarto;
import br.csi.Dormez.model.Reserva;
import br.csi.Dormez.service.ReservaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    private ReservaService service;
    public ReservaController(ReservaService service) {this.service = service;}

    @GetMapping("/listar")
    public List<Reserva> listar() {return service.listar();}

    @GetMapping("/uuid/{uuid}")
    public Reserva reserva(@PathVariable String uuid) {return this.service.buscarPorUUID(uuid);}

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

    @DeleteMapping("/uuid/{uuid}")
    public void deletarUUID(@PathVariable String uuid) {this.service.deletarUUID(uuid);}
}
