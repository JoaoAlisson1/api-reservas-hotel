package br.csi.Dormez.DTO.mapper;

import br.csi.Dormez.DTO.FuncionarioRequestDTO;
import br.csi.Dormez.DTO.FuncionarioResponseDTO;
import br.csi.Dormez.model.Funcionario;

public class FuncionarioMapper {

    public static Funcionario toEntity(FuncionarioRequestDTO dto) {

        Funcionario func = new Funcionario();
        func.setNome(dto.getNome());
        func.setEmail(dto.getEmail());
        func.setTelefone(dto.getTelefone());
        func.setCargo(dto.getCargo());

        return func;
    }

    public static FuncionarioResponseDTO toResponseDTO(Funcionario funcionario) {

        return new FuncionarioResponseDTO(
                funcionario.getUuid(),
                funcionario.getNome(),
                funcionario.getEmail(),
                funcionario.getTelefone(),
                funcionario.getCargo()
        );
    }
}
