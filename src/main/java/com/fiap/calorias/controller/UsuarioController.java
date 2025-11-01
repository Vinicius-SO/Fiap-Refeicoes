package com.fiap.calorias.controller;

import com.fiap.calorias.DTO.UsuarioCadastroDTO;
import com.fiap.calorias.DTO.UsuarioExibicaoDTO;
import com.fiap.calorias.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDTO salvar(@Valid @RequestBody UsuarioCadastroDTO usuario){
        return usuarioService.salvarUsuario(usuario);
    }

    @GetMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioExibicaoDTO> litarTodos(){
        return usuarioService.listarTodos();
    }

    @GetMapping("/usuarios/{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibicaoDTO buscarPorId(@PathVariable Long usuarioId){
        return usuarioService.buscarPorId(usuarioId);
    }


    @RequestMapping(value = "/usuarios", params = "email")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibicaoDTO buscarPorEmail(@RequestParam String email){
        return usuarioService.buscarPorEmail(email);
    }

    @DeleteMapping("/usuarios/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long usuarioId){
        usuarioService.excluir(usuarioId);
    }

    @PutMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibicaoDTO atualizar(@RequestBody UsuarioCadastroDTO usuario){
        return usuarioService.atualizar(usuario);
    }

}