package com.alura.finalProject.randomPhrase.service;

import com.alura.finalProject.randomPhrase.DTO.PhrasesDTO;
import com.alura.finalProject.randomPhrase.model.Phrases;
import com.alura.finalProject.randomPhrase.repository.PhraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/*@Service
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
}*/

@Service
public class PhraseService {


    @Autowired
    private PhraseRepository repository;

    /*public PhrasesDTO receivingRandomPhrase() {
        Phrases phrase = repository.searchRandomPhrase();
        return new PhrasesDTO(phrase.getTitle(), phrase.getPhrase(), phrase.getCharacter(), phrase.getPoster());
    }*/

    public PhrasesDTO receivingRandomPhrase() {
        // Para teste local - REMOVER depois
        if (isLocalEnvironment()) {
            return new PhrasesDTO("Teste Local", "Frase de teste local!", "Teste", "poster.jpg");
        }

        // Código normal para produção
        try {
            Phrases phrases = repository.searchRandomPhrase();

            // ADICIONE ESTA PARTE QUE ESTAVA FALTANDO:
            if (phrases != null) {
                return new PhrasesDTO(
                        phrases.getTitle(),
                        phrases.getPhrase(),
                        phrases.getCharacter(),
                        phrases.getPoster()
                );
            } else {
                return new PhrasesDTO("Aviso", "Nenhuma frase encontrada no banco", "Sistema", "");
            }

        } catch (Exception e) {
            return new PhrasesDTO("Erro", e.getMessage(), "", "");
        }
    }

    private boolean isLocalEnvironment() {
        return !System.getenv().containsKey("PORT"); // Render sempre tem PORT
    }

}
