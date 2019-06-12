package com.yyq.wordtrans.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reciverword")
public class WordController {
    @RequestMapping("/english")
    public String reciveEnglishWord(String message){

     return null;
    }
    @RequestMapping("chinese")
    public String reciveChineseWord(String message){

        return null;
    }
}
