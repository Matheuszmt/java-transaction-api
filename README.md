Receber Transações
  POST /transacao
Parâmetro	Tipo	Descrição
valor	BigDecimal	Obrigatório. O valor da transação
dataHora	OffsetDateTime	Obrigatório. O horário que a transação ocorreu
Limpar Transações
  DELETE /transacao
Calcular Estatísticas
  GET /estatistica
Parâmetro	Tipo	Descrição
intervaloSegundos	integer	Não Obrigatório O padrão default é 60s
