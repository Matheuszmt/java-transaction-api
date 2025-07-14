package com.matheuszindev.transaction_api.business.services;

import com.matheuszindev.transaction_api.controller.dtos.TransacaoRequestDTO;
import com.matheuszindev.transaction_api.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j

public class TransacaoService {

    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

    public void adicionarTransacao(TransacaoRequestDTO dto) {
        log.info("Iniciada o processamento de gravar transações" + dto);
        if (dto.dataHora().isAfter(OffsetDateTime.now())){
        log.error("O data hora deve ser posterior a hoje");
         new UnprocessableEntity("Data e Hora maiores que a data e hora atual");
    }
        if(dto.valor() < 0) {
            log.info("Valor não pode ser menor que zero");
            throw new UnprocessableEntity("Valor não pode ser menor do que zero");

        }
        listaTransacoes.add(dto);
        log.info("Transações adicionadas com Sucesso.");
    }

    public void limparTransacoes() {
        log.info("Iniciado a limpeza das transações.");
        listaTransacoes.clear();
        log.info("Transações removidas com sucesso.");
    }

    public List<TransacaoRequestDTO> buscarTransacoes(Integer intervaloBusca) {
        log.info("Iniciado a busca de transações por tempo: " + intervaloBusca);
        OffsetDateTime dataHoraIntevalo = OffsetDateTime.now().minusSeconds(intervaloBusca);
        log.info("Retorno de transações com sucesso.");
        return listaTransacoes.stream()
                .filter(transacao -> transacao.dataHora()
                        .isAfter(dataHoraIntevalo)).toList();
    }
}
