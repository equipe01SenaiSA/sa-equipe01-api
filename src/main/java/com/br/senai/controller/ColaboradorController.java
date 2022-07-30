package com.br.senai.controller;


import com.br.senai.entity.Colaborador;
import com.br.senai.service.ColaboradorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {

    @Autowired
    private ColaboradorService service;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MapConverter mapConverter;

    @PostMapping
    public ResponseEntity<?> inserir(
            @RequestBody
            Map<String, Object> colaboradorMap){
        Colaborador novoColaborador = mapper
                .convertValue(colaboradorMap, Colaborador.class);
        Colaborador colaboradorSalvo = service.inserir(novoColaborador);
        return ResponseEntity.created(
                URI.create(
                        "/colaboradores-/id/" + colaboradorSalvo.getId()
                )
        ).build();
    }

    @PutMapping
    public ResponseEntity<?> alterar(
            @RequestBody
            Map<String, Object> colaboradorMap){
        Colaborador colaboradorSalvo = mapper
                .convertValue(colaboradorMap, Colaborador.class);
        Colaborador colaboradorAtualizado = service.alterar(colaboradorSalvo);
        return ResponseEntity.ok(mapConverter.toJsonMap(colaboradorAtualizado));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> buscarPor(
            @PathVariable("id") Integer id)
            throws JsonProcessingException {
        Colaborador colaboradorEncontrado = service.buscarPor(id);
        String json = mapper.writeValueAsString(colaboradorEncontrado);
        JSONObject jsonObj = new JSONObject(json);
        return ResponseEntity.ok(jsonObj.toMap());
    }

    
//        @GetMapping(value = "/nome/{nome}")
//        public ResponseEntity<?> listarPor(
//                @PathVariable(name = "nome")
//                String nome){
//            return ResponseEntity.ok(mapConverter
//                    .toJsonList(service.listarPor(nome)));
//        }
    
    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<?> excluirPor(
            @PathVariable(name = "id")
            Integer id){
        this.service.excluirPor(id);
        return ResponseEntity.noContent().build();
    }
}
