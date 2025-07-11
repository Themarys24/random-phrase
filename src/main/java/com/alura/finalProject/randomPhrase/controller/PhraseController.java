package com.alura.finalProject.randomPhrase.controller;

import com.alura.finalProject.randomPhrase.DTO.PhrasesDTO;
import com.alura.finalProject.randomPhrase.service.PhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/phrases") // Adicione esta linha
public class PhraseController {

    @Autowired
    private PhraseService service;

    @GetMapping("/random") // Agora será /api/phrases/random
    public PhrasesDTO receivingRandomPhrase(){
        return service.receivingRandomPhrase();
    }

    @GetMapping("/") // Para listar todas (opcional)
    public String listPhrases(){
        return "Endpoint para listar frases";
    }

}
