package br.csi.Dormez.repository;

import br.csi.Dormez.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    public Funcionario findFuncionarioByUuid(UUID uuid);
    public void deleteFuncionarioByUuid(UUID uuid);

}
