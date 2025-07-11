package com.alura.finalProject.randomPhrase.repository;

import com.alura.finalProject.randomPhrase.model.Phrases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhraseRepository extends JpaRepository<Phrases, Long> {

    // PostgreSQL usa RANDOM(), não RAND()
    @Query(value = "SELECT * FROM frases ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Phrases searchRandomPhrase();

    // Alternativa usando JPA (mais portável):
    // @Query("SELECT p FROM Phrases p ORDER BY FUNCTION('RANDOM')")
    // Phrases searchRandomPhrase();


    @Query("SELECT p FROM Phrases p")
    List<Phrases> findAllPhrases();


    @Query("SELECT COUNT(p) FROM Phrases p")
    long countPhrases();

    // Query alternativa para teste
    @Query(value = "SELECT * FROM frases LIMIT 1", nativeQuery = true)
    Phrases findFirstPhrase();
}
