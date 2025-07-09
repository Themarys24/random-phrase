package com.alura.finalProject.randomPhrase.controller;

import com.alura.finalProject.randomPhrase.DTO.PhrasesDTO;
import com.alura.finalProject.randomPhrase.service.PhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhraseController {

    @Autowired
    private PhraseService service;

    @GetMapping("/series/frases")
    public PhrasesDTO receivingRandomPhrase(){
        return service.receivingRandomPhrase();
    }

}
