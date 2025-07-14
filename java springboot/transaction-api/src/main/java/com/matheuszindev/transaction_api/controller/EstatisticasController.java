package com.matheuszindev.transaction_api.controller;

import com.matheuszindev.transaction_api.business.services.EstatisticasService;
import com.matheuszindev.transaction_api.business.services.TransacaoService;
import com.matheuszindev.transaction_api.controller.dtos.EstatisticasResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
@Slf4j

public class EstatisticasController {

    private final EstatisticasService estatisticasService;
    @GetMapping
    @Operation(description = "Endpoint responsavel por buscar estatisticas de transações.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro na busca de estatisticas de transações!"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor!")
    })

    public ResponseEntity<EstatisticasResponseDTO> buscarEstatisticas(
            @RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca) {
        return ResponseEntity.ok(
                estatisticasService.calcularEstatisticasTransacoes(intervaloBusca));
    }
}
