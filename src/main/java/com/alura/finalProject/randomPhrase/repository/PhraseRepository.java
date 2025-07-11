package com.alura.finalProject.randomPhrase.repository;

import com.alura.finalProject.randomPhrase.model.Phrases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhraseRepository extends JpaRepository<Phrases, Long> {

    @Query(value = "SELECT * FROM frases ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Phrases searchRandomPhrase();


}
