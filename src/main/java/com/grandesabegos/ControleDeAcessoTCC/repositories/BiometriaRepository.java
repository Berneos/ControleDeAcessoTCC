package com.grandesabegos.ControleDeAcessoTCC.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.grandesabegos.ControleDeAcessoTCC.entities.Biometria;

@Repository
public interface BiometriaRepository extends JpaRepository<Biometria, Long> {

    // Retorna a biometria mais recente (com base na data)
    @Query("SELECT b FROM Biometria b ORDER BY b.data DESC LIMIT 1")
    Biometria findMostRecent();
}
