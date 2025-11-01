package com.fiap.calorias.controller;

import com.fiap.calorias.DTO.AlimentoCadastroDTO;
import com.fiap.calorias.DTO.AlimentoExibicaoDTO;
import com.fiap.calorias.service.AlimentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AlimentoController {

    @Autowired
    private AlimentoService alimentoService;

    @PostMapping("/alimentos")
    @ResponseStatus(HttpStatus.CREATED)
    public AlimentoExibicaoDTO salvar(@RequestBody @Valid AlimentoCadastroDTO alimento){
        return alimentoService.salvarAlimento(alimento);
    }

    @GetMapping("/alimentos")
    @ResponseStatus(HttpStatus.OK)
    public Page<AlimentoExibicaoDTO> litarTodos(Pageable paginacao){
        return alimentoService.listarTodos(paginacao);
    }

    @GetMapping("/alimentos/{alimentoId}")
    public ResponseEntity<AlimentoExibicaoDTO> buscarPorId(
            @PathVariable Long alimentoId){
        try {
            return ResponseEntity
                    .ok(alimentoService.buscarPorId(alimentoId));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/alimentos", params = "nome")
    public ResponseEntity<AlimentoExibicaoDTO> buscarPorNome(
            @RequestParam String nome){
        try {
            return ResponseEntity
                    .ok(alimentoService.buscarPorNome(nome));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/alimentos", params = "totalCaloriaMenorQue")
    @ResponseStatus(HttpStatus.OK)
    public List<AlimentoExibicaoDTO> buscarPorTotalDeCaloriasMenorQue(@RequestParam Double totalCaloriaMenorQue) {
        return alimentoService.buscarPorTotalDeCaloriasMenorQue(totalCaloriaMenorQue);
    }

    @RequestMapping(value = "/alimentos", params = {"caloriasMinima", "caloriasMaxima"})
    @ResponseStatus(HttpStatus.OK)
    public List<AlimentoExibicaoDTO> litarAlimentosPorFaixaDeCalorias(
            @RequestParam Double caloriasMinima,
            @RequestParam Double caloriasMaxima
    ){
        return alimentoService.listarAlimentosPorFaixaDeCalorias(caloriasMinima, caloriasMaxima);
    }

    @RequestMapping(value = "/alimentos", params = "sequenciaDeCaracteres")
    @ResponseStatus(HttpStatus.OK)
    public List<AlimentoExibicaoDTO> listarAlimentosPorSequenciaDeCaracteres(
            @RequestParam String sequenciaDeCaracteres
    ){
        return alimentoService.listarAlimentosPorSequenciaDeCaracteres(sequenciaDeCaracteres);
    }

    @DeleteMapping("/alimentos/{alimentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long alimentoId){
        alimentoService.excluir(alimentoId);
    }

    @PutMapping("/alimentos")
    public ResponseEntity<AlimentoExibicaoDTO> atualizar(
            @RequestBody AlimentoCadastroDTO alimentoDTO) {
        try {
            AlimentoExibicaoDTO alimentoExibicaoDTO =
                    alimentoService.atualizar(alimentoDTO);
            return ResponseEntity.ok(alimentoExibicaoDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
