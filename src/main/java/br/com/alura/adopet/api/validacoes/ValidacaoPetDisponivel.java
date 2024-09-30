package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.validacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPetDisponivel implements ValidacaoSolicitacaoAdocao{

    @Autowired
    private PetRepository petRepository;

    public void validar(SolicitacaoAdocaoDto dto) {
        boolean petAdotado = petRepository.existsByPetIdAndStatus(dto.idPet(), StatusAdocao.APROVADO);
        if (petAdotado) {
            throw new validacaoException("Pet já foi adotado");
        }
    }
}
