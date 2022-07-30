package com.br.senai.controller;

import com.br.senai.entity.Cargo;
import com.br.senai.service.CargoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    private CargoService service;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MapConverter mapConverter;

    @PostMapping
    public ResponseEntity<?> inserir(
            @RequestBody
            Map<String, Object> cargoMap){
        Cargo novoCargo = mapper
                .convertValue(cargoMap, Cargo.class);
        Cargo cargoSalvo = service.inserir(novoCargo);
        return ResponseEntity.created(
                URI.create(
                        "/cargos-/id/" + cargoSalvo.getId()
                )
        ).build();
    }

    @PutMapping
    public ResponseEntity<?> alterar(
            @RequestBody
            Map<String, Object> cargoMap){
        Cargo cargoSalvo = mapper
                .convertValue(cargoMap, Cargo.class);
        Cargo cargoAtualizado = service.alterar(cargoSalvo);
        return ResponseEntity.ok(mapConverter.toJsonMap(cargoAtualizado));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> buscarPor(
            @PathVariable("id") Integer id)
            throws JsonProcessingException {
        Cargo cargoEncontrado = service.buscarPor(id);
        String json = mapper.writeValueAsString(cargoEncontrado);
        JSONObject jsonObj = new JSONObject(json);
        return ResponseEntity.ok(jsonObj.toMap());
    }


    @GetMapping(value = "/descricao-curta/{descricaoCurta}")
    public ResponseEntity<?> listarPor(
            @PathVariable(name = "descricaoCurta")
            String descricaoCurta){
        return ResponseEntity.ok(mapConverter
                .toJsonList(service.listarPor(descricaoCurta)));
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<?> excluirPor(
            @PathVariable(name = "id")
            Integer id){
        this.service.excluirPor(id);
        return ResponseEntity.noContent().build();
    }
}
