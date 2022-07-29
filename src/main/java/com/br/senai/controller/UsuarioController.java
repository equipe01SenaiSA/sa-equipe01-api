package com.br.senai.controller;

import com.br.senai.entity.Usuario;
import com.br.senai.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController{


    @Autowired
    private UsuarioService service;

    @Autowired
    private ObjectMapper mapper;

    @GetMapping
    public ResponseEntity<?> logar (
    		@Param("login") String login,
    		@Param("senha") String senha)
            throws JsonProcessingException {
        Usuario usuarioEncontrado = service.buscarLoginESenha(login, senha);
        String json = mapper.writeValueAsString(usuarioEncontrado);
        JSONObject jsonObj = new JSONObject(json);
        return ResponseEntity.ok(jsonObj.toMap());
    }
}
