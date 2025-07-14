package com.matheuszindev.transaction_api.business.services;


import com.matheuszindev.transaction_api.controller.dtos.EstatisticasResponseDTO;
import com.matheuszindev.transaction_api.controller.dtos.TransacaoRequestDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j


public class EstatisticasService {

    public final TransacaoService transacaoService;

    public EstatisticasResponseDTO calcularEstatisticasTransacoes(Integer intervaloBusca) {

        log.info("Iniciado a busca de estatisticas pelo periodo de tempo: " + intervaloBusca);

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloBusca);

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream()
                .mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        if (transacoes.isEmpty()) {
            return new EstatisticasResponseDTO(0L,0.0,0.0,0.0,0.0);
        }
        log.info("Estatisticas retornadas com sucesso.");

        return new EstatisticasResponseDTO(estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax());
    }
}

