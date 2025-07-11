package com.alura.finalProject.randomPhrase.service;

import com.alura.finalProject.randomPhrase.DTO.PhrasesDTO;
import com.alura.finalProject.randomPhrase.model.Phrases;
import com.alura.finalProject.randomPhrase.repository.PhraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhraseService {
    @Autowired
    private PhraseRepository repository;

    public PhrasesDTO receivingRandomPhrase() {
        Phrases phrases = repository.searchRandomPhrase();
        if (phrases == null) {
            // Retorna um DTO vazio, uma mensagem padrão, ou lança exceção
            // Exemplo retornando uma frase padrão:
            return new PhrasesDTO("Nenhuma frase encontrada", "", "", "");
        }
        return new PhrasesDTO(phrases.getTitle(), phrases.getPhrase(), phrases.getCharacter(), phrases.getPoster());
    }
}
