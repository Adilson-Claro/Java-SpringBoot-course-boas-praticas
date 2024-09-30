package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.validacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTutorComAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao{

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Autowired
    TutorRepository tutorRepository;

    public void validar(SolicitacaoAdocaoDto dto){
        boolean tutorComOutraAvaliacaoEmAndamento = tutorRepository.existsByIdTutorAndStatus(dto.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO);
            if (tutorComOutraAvaliacaoEmAndamento) {
                throw new validacaoException("Tutor já possui outra adoção aguardando avaliação!");
            }
    }
}

