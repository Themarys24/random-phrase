package com.alura.finalProject.randomPhrase.service;

import com.alura.finalProject.randomPhrase.DTO.PhrasesDTO;
import com.alura.finalProject.randomPhrase.model.Phrases;
import com.alura.finalProject.randomPhrase.repository.PhraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public PhrasesDTO receivingRandomPhrase() {
        // DEBUG: Contar frases no banco
        long count = repository.count();
        System.out.println("🔍 Total de frases no banco: " + count);

        Phrases phrases = repository.searchRandomPhrase();
        System.out.println("🔍 Frase encontrada: " + (phrases != null ? phrases.getPhrase() : "null"));

        if (phrases == null) {
            // Teste alternativo: buscar primeira frase disponível
            var allPhrases = repository.findAll();
            System.out.println("🔍 Total com findAll(): " + allPhrases.size());

            if (!allPhrases.isEmpty()) {
                phrases = allPhrases.get(0);
                System.out.println("🔍 Usando primeira frase como teste");
            } else {
                return new PhrasesDTO("Banco vazio - " + count + " frases", "", "", "");
            }
        }

        return new PhrasesDTO(phrases.getTitle(), phrases.getPhrase(), phrases.getCharacter(), phrases.getPoster());
    }
}
