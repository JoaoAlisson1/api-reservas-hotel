package br.csi.Dormez.DTO.mapper;

import br.csi.Dormez.DTO.HospedeRequestDTO;
import br.csi.Dormez.DTO.HospedeResponseDTO;
import br.csi.Dormez.model.Hospede;

public class HospedeMapper {

    // Converte RequestDTO para entidade
    public static Hospede toEntity(HospedeRequestDTO dto) {

        Hospede hospede = new Hospede();
        hospede.setNome(dto.getNome());
        hospede.setEmail(dto.getEmail());
        hospede.setTelefone(dto.getTelefone());
        hospede.setCpf(dto.getCpf());

        return hospede;
    }

    // Converte entidade para ResponseDTO
    public static HospedeResponseDTO toResponseDTO(Hospede hospede) {

        return new HospedeResponseDTO(
                hospede.getUuid(),
                hospede.getNome(),
                hospede.getEmail(),
                hospede.getTelefone()
        );
    }
}
