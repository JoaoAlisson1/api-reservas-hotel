package br.csi.Dormez.DTO.mapper;

import br.csi.Dormez.DTO.QuartoRequestDTO;
import br.csi.Dormez.DTO.QuartoResponseDTO;
import br.csi.Dormez.model.Quarto;

public class QuartoMapper {

    public static Quarto toEntity(QuartoRequestDTO dto) {

        // Converte QuartoRequestDTO em  entidade Quarto (para salvar/atualizar)
        Quarto quarto = new Quarto();
        quarto.setNumero(dto.getNumero());
        quarto.setTipo(dto.getTipo());
        quarto.setStatus(dto.getStatus());
        quarto.setDiaria(dto.getDiaria());

        return quarto;
    }

    // Converte a entidade Quarto em QuartoResponseDTO (para retornar na API)
    public static QuartoResponseDTO toResponseDTO(Quarto quarto) {

        return new QuartoResponseDTO(
                quarto.getId(),
                quarto.getNumero(),
                quarto.getTipo(),
                quarto.getStatus(),
                quarto.getDiaria()
        );
    }
}
