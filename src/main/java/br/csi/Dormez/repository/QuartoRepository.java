package br.csi.Dormez.repository;

import br.csi.Dormez.model.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuartoRepository extends JpaRepository<Quarto, Long> {
    public Quarto findByNumero(int numero);
}
