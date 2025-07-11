package com.alura.finalProject.randomPhrase.service;

import com.alura.finalProject.randomPhrase.DTO.PhrasesDTO;
import com.alura.finalProject.randomPhrase.model.Phrases;
import com.alura.finalProject.randomPhrase.repository.PhraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhraseService {

    @Autowired
    private PhraseRepository repository;

    public PhrasesDTO receivingRandomPhrase() {
        // Para teste local
        if (isLocalEnvironment()) {
            return new PhrasesDTO("Teste Local", "Frase de teste local!", "Teste", "poster.jpg");
        }

        // Código para produção com DEBUG
        try {
            System.out.println("🔍 Iniciando busca de frases...");

            // 1. Contar frases no banco
            long totalFrases = repository.count();
            System.out.println("🔍 Total de frases no banco: " + totalFrases);

            // 2. Buscar todas as frases para debug
            List<Phrases> todasFrases = repository.findAll();
            System.out.println("🔍 FindAll retornou: " + todasFrases.size() + " frases");

            if (!todasFrases.isEmpty()) {
                System.out.println("🔍 Primeira frase: " + todasFrases.get(0).getPhrase());
                // Usar a primeira frase
                Phrases phrase = todasFrases.get(0);
                return new PhrasesDTO(
                        phrase.getTitle(),
                        phrase.getPhrase(),
                        phrase.getCharacter(),
                        phrase.getPoster()
                );
            }

            // 3. Se chegou aqui, o banco está vazio
            return new PhrasesDTO("Debug", "Banco tem " + totalFrases + " frases mas findAll = " + todasFrases.size(), "Sistema", "");

        } catch (Exception e) {
            System.out.println("🚨 Erro: " + e.getMessage());
            e.printStackTrace();
            return new PhrasesDTO("Erro Exception", e.getMessage(), "Sistema", "");
        }
    }

    private boolean isLocalEnvironment() {
        return !System.getenv().containsKey("PORT");
    }
}