package com.fiap.calorias.repository;

import com.fiap.calorias.DTO.AlimentoExibicaoDTO;
import com.fiap.calorias.model.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AlimentoRepository extends JpaRepository<Alimento, Long> {

    List<Alimento> findByTotalCaloriasLessThanEqual(Double caloria);

    @Query("SELECT a FROM Alimento a WHERE a.nome = :nome")
    Optional<Alimento> buscarPorNome(@Param("nome") String nome);

    @Query("SELECT a FROM Alimento a WHERE a.totalCalorias BETWEEN :minimo AND :maximo ORDER BY a.totalCalorias DESC")
    List<Alimento> listarAlimentosPorFaixaDeCalorias(
            @Param("minimo") Double minino,
            @Param("maximo") Double maximo
    );

    //Starting Exercises
    @Query("SELECT a FROM Alimento a WHERE a.nome Like CONCAT(:sequence,'%')")
    List<Alimento> listarAlimentosPorSequenciaDeCaracteres(
            @Param("sequence") String sequence
    );
}
