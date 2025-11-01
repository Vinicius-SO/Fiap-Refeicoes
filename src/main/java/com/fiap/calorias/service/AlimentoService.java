package com.fiap.calorias.service;

import com.fiap.calorias.DTO.AlimentoCadastroDTO;
import com.fiap.calorias.DTO.AlimentoExibicaoDTO;
import com.fiap.calorias.model.Alimento;
import com.fiap.calorias.repository.AlimentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
public class AlimentoService {

    public Double calcularCalorias(Double proteinas, Double carboidratos, Double gorduras){
        Double calorias = (proteinas * 4) + (carboidratos * 4) + (gorduras * 9);
        return calorias;
    }

    @Autowired
    public AlimentoRepository alimentoRepository;

    public AlimentoExibicaoDTO salvarAlimento(AlimentoCadastroDTO alimentoDTO){

        Alimento alimento = new Alimento();
        BeanUtils.copyProperties(alimentoDTO, alimento);

        alimento.setTotalCalorias(
                calcularCalorias(
                        alimento.getQuantidadeProteina(),
                        alimento.getQuantidadeCarboidrato(),
                        alimento.getQuantidadeGorduras()
                )
        );

        Alimento alimentoSalvo = alimentoRepository.save(alimento);
        return new AlimentoExibicaoDTO(alimentoSalvo);

    }

    public AlimentoExibicaoDTO buscarPorId(Long id){
        Optional<Alimento> alimentoOptional =
                alimentoRepository.findById(id);

        if (alimentoOptional.isPresent()){
            return new AlimentoExibicaoDTO(alimentoOptional.get());
        } else {
            throw new RuntimeException("Alimento não existe!");
        }
    }

    public List<AlimentoExibicaoDTO> listarAlimentosPorFaixaDeCalorias(Double caloriaMinima, Double caloriaMaxima){
        return alimentoRepository
                .listarAlimentosPorFaixaDeCalorias(caloriaMinima, caloriaMaxima)
                .stream()
                .map(AlimentoExibicaoDTO::new)
                .toList();
    }

    public List<AlimentoExibicaoDTO> listarAlimentosPorSequenciaDeCaracteres(String sequence){
        return alimentoRepository
                .listarAlimentosPorSequenciaDeCaracteres(sequence)
                .stream()
                .map(AlimentoExibicaoDTO::new)
                .toList();
    }


    public AlimentoExibicaoDTO buscarPorNome(String nome){
        Optional<Alimento> alimentoOptional =
                alimentoRepository.buscarPorNome(nome);

        if (alimentoOptional.isPresent()){
            return new AlimentoExibicaoDTO(alimentoOptional.get());
        } else {
            throw new RuntimeException("Alimento não existe!");
        }
    }

    @RequestMapping(value = "/alimentos", params = "totalCaloriaMenorQue")
    public List<AlimentoExibicaoDTO> buscarPorTotalDeCaloriasMenorQue(@Param("totalCaloriaMenorQue") Double totalCaloriaMenorQue){
              return alimentoRepository.findByTotalCaloriasLessThanEqual(totalCaloriaMenorQue)
                      .stream()
                      .map(AlimentoExibicaoDTO::new)
                      .toList();
    }

    public Page<AlimentoExibicaoDTO> listarTodos(Pageable paginacao){
        return alimentoRepository
                .findAll(paginacao)
                .map(AlimentoExibicaoDTO::new);
    }

    public void excluir(Long id){
        Optional<Alimento> alimentoOptional =
                alimentoRepository.findById(id);

        if (alimentoOptional.isPresent()){
            alimentoRepository.delete(alimentoOptional.get());
        } else {
            throw new RuntimeException("Alimento não encontrado!");
        }
    }

    public AlimentoExibicaoDTO atualizar(AlimentoCadastroDTO alimentoDTO){
        Optional<Alimento> alimentoOptional =
                alimentoRepository.findById(alimentoDTO.alimentoId());

        if (alimentoOptional.isPresent()){
            Alimento alimento = new Alimento();
            BeanUtils.copyProperties(alimentoDTO, alimento);

            alimento.setTotalCalorias(
                    calcularCalorias(
                            alimento.getQuantidadeProteina(),
                            alimento.getQuantidadeCarboidrato(),
                            alimento.getQuantidadeGorduras()
                    )
            );

            return new AlimentoExibicaoDTO(alimentoRepository.save(alimento));
        } else {
            throw new RuntimeException("Alimento não encontrado!");
        }
    }
}
