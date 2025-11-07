package br.csi.Dormez.controller;

import br.csi.Dormez.DTO.FuncionarioRequestDTO;
import br.csi.Dormez.DTO.FuncionarioResponseDTO;
import br.csi.Dormez.DTO.mapper.FuncionarioMapper;
import br.csi.Dormez.infra.TratadorDeErros;
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

    private  final FuncionarioService service;
    public FuncionarioController(FuncionarioService service) {this.service = service;}

    @Operation(summary = "Listar todos os funcionários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Funcionario.class))),
    })
    @GetMapping("/listar")
    public List<FuncionarioResponseDTO> listar() {
        return service.listar().stream().map(FuncionarioMapper::toResponseDTO).toList();
    }

    @Operation(summary = "Buscar funcionário por UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário encontrado",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Funcionario.class))),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado", content = @Content)
    })
    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<FuncionarioResponseDTO> buscarPorUUID(@PathVariable String uuid) {
        //Busca a entidade Funcionario pelo UUID usando o service
        Funcionario funcionario = service.buscarPorUUID(uuid);

        // Converte a entidade para DTO para não expor diretamente o modelo
        FuncionarioResponseDTO responseDTO = FuncionarioMapper.toResponseDTO(funcionario);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping()
    @Operation(summary = "Criar um novo funcionário", description = "Cria um novo funcionário e o adiciona a lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionário criado com sucesso",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Funcionario.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content)
    })
    public ResponseEntity<FuncionarioResponseDTO> salvar(@RequestBody @Valid FuncionarioRequestDTO dto, UriComponentsBuilder uriBuilder) {
        Funcionario funcionario = this.service.salvar(FuncionarioMapper.toEntity(dto)); // converte o DTO em entidade direto dentro do método
        FuncionarioResponseDTO response = FuncionarioMapper.toResponseDTO(funcionario);

        URI uri = uriBuilder.path("/funcionario/{uuid}").buildAndExpand(funcionario.getUuid()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @Operation(summary = "Atualizar funcionário pelo UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Funcionario.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado", content = @Content)
    })

    @PutMapping("/{uuid}")
    public ResponseEntity<TratadorDeErros.MensagemSucesso> atualizarUUID(@RequestBody @Valid FuncionarioRequestDTO dto, @PathVariable String uuid) {
        Funcionario funcionario = FuncionarioMapper.toEntity(dto);
        funcionario.setUuid(UUID.fromString(uuid));

        this.service.atualizarUUID(funcionario);


        return ResponseEntity.ok(new TratadorDeErros.MensagemSucesso("Funcionário atualizado com sucesso!"));
    }

    @Operation(summary = "Excluir funcionário pelo UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Funcionário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado", content = @Content)
    })

    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<TratadorDeErros.MensagemSucesso> deletarUUID(@PathVariable String uuid) {

        this.service.deletarUUID(uuid);
        return ResponseEntity.noContent().build();
    }
}
