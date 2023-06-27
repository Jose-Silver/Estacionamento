package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.dtos.MovimentacaoDTOS;
import br.com.uniamerica.estacionamento.entity.*;
import br.com.uniamerica.estacionamento.entity.Movimentacao;
import br.com.uniamerica.estacionamento.relatorio;
import br.com.uniamerica.estacionamento.repository.*;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository repository;
    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private MarcaRepository marcaRepository;
    @Autowired
    private CondutorRepository condutorRepository;
    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    @Transactional
    public ResponseEntity<?> findById (Long id) {
        Optional < Movimentacao> movimentacaoOptional =repository.findById(id);
        if (movimentacaoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("movimentacao inexistente");

        } else {
            return ResponseEntity.status(HttpStatus.OK).body(movimentacaoOptional.get());

        }
    }

    @Transactional
    public ResponseEntity<?> findAll( ) {
        List<Movimentacao> movimentacaoes = repository.findAll();
        return  ResponseEntity.ok().body(movimentacaoes);
    }



    @Transactional
    public ResponseEntity<?> update(Long id, MovimentacaoDTOS movimentacaoDTOS) {
        Optional<Movimentacao> optionalMovimentacao = repository.findById(id);
        Condutor optionalCondutor = condutorRepository.getById(movimentacaoDTOS.getCondutor());
        Veiculo optionalVeiculo = veiculoRepository.getById(movimentacaoDTOS.getVeiculo());
        if (optionalCondutor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Condutor not found with ID: " + id);
        }
        if (optionalVeiculo == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo not found with ID: " + id);
        }

        if (optionalMovimentacao.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movimentacao not found with ID: " + id);
        } else {

            Movimentacao movimentacao1 = optionalMovimentacao.get();
//            Veiculo veiculo1 = optionalVeiculo.get();
            BeanUtils.copyProperties(movimentacaoDTOS,movimentacao1);
            movimentacao1.setAtivo(true);
//            movimentacao1.setCondutor(condutor1);
//            movimentacao1.setVeiculo(veiculo1);
            movimentacao1.setAtualizacao(LocalDateTime.now());
            movimentacao1.setEntrada(LocalDateTime.now());
            repository.save(movimentacao1);
            return ResponseEntity.ok().body("Movimentacao atualizado com sucesso");
        }
    }

    @Transactional
    public ResponseEntity<?> create(MovimentacaoDTOS movimentacaoDTOS) {
        Condutor optionalCondutor = condutorRepository.getById(movimentacaoDTOS.getCondutor());
        Veiculo optionalVeiculo = veiculoRepository.getById(movimentacaoDTOS.getVeiculo());
        if (optionalCondutor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Condutor not found ");
        }
        if (optionalVeiculo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo not found");
        }

            try {
                Movimentacao movimentacao1 = new Movimentacao();
                 Condutor condutor1 = optionalCondutor;
                 Veiculo veiculo1 = optionalVeiculo;
                BeanUtils.copyProperties(movimentacaoDTOS, movimentacao1);
                movimentacao1.setAtivo(true);
                movimentacao1.setCondutor(condutor1);
                movimentacao1.setVeiculo(veiculo1);
                movimentacao1.setAtualizacao(LocalDateTime.now());
                movimentacao1.setEntrada(LocalDateTime.now());
                repository.save(movimentacao1);
                return ResponseEntity.ok().body(movimentacao1);
            } catch (Exception e){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResponseEntity.badRequest().body(e.getCause().getCause().getLocalizedMessage());

            }
        }

    public relatorio saida (final Long id){

        // Verifica se a movimentação existe
        final Movimentacao movBanco = this.repository.findById(id).orElse(null);
        Assert.isTrue(movBanco != null, "Não foi possivel identificar o registro informado");

        // Identifica o horário da saida e calcula o tempo entre os dois horários
        final LocalDateTime saida = LocalDateTime.now();
        Duration duracao = Duration.between(movBanco.getEntrada(), saida);

        // Pega os valores de configuração
        final Configuracao config = this.configuracaoRepository.findById(1L).orElse(null);
        Assert.isTrue(config != null, "Configuracoes nao cadastradas");

        // Pega o desconto do cliente
        final Condutor condutor = this.condutorRepository.findById(movBanco.getCondutor().getId()).orElse(null);
        Assert.isTrue(condutor != null, "Condutor nao encontrado");

        // Seta saida e tempo
        movBanco.setSaida(saida);
        movBanco.setTempoHoras(duracao.toHoursPart());
        movBanco.setTempoMinutos(duracao.toMinutesPart());

        // Calcula o preco de acordo com o tempo passado
        final BigDecimal horas = BigDecimal.valueOf(duracao.toHoursPart());
        final BigDecimal minutos = BigDecimal.valueOf(duracao.toMinutesPart()).divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_EVEN);
        BigDecimal preco = config.getValorHora().multiply(horas).add(config.getValorHora().multiply(minutos));

        final BigDecimal tempoPago = condutor.getTempoPago() != null ? condutor.getTempoPago() : new BigDecimal(0);

        BigDecimal valor_desc;

        if (tempoPago.compareTo(new BigDecimal(config.getTempoParaDesconto())) >= 0) {
            valor_desc = BigDecimal.valueOf(config.getTempoDeDesconto());

            movBanco.setValorDesconto(valor_desc);
            condutor.setTempoPago(BigDecimal.ZERO);
        }else {
            valor_desc = BigDecimal.ZERO;
        }

        BigDecimal valorTotal = preco.subtract(valor_desc);
        movBanco.setValorTotal(valorTotal);

        movBanco.setValorHora(config.getValorHora());
        movBanco.setValorMinutoMulta(config.getValorMinutoMulta());

        if (config.getGerarDesconto()) {
            condutor.setTempoPago(tempoPago.add(horas.add(minutos)));
        }

        this.condutorRepository.save(condutor);
        this.repository.save(movBanco);

        return new relatorio (movBanco.getEntrada(), movBanco.getSaida(), movBanco.getCondutor(), movBanco.getVeiculo(), horas.intValue(),
                tempoPago.setScale(0, RoundingMode.HALF_EVEN),
                preco.subtract(valor_desc).setScale(2, RoundingMode.HALF_EVEN),
                valor_desc.setScale(2, RoundingMode.HALF_EVEN));
    }
/*
    @Transactional
    public ResponseEntity<?> delete (Long id) {

        Optional<Movimentacao> optionalMovimentacao = this.repository.findById(id);

        if(optionalMovimentacao.isPresent()) {
            Configuracao config = configuracaoRepository.getById(1L);

            Movimentacao movimentacao = repository.getById(optionalMovimentacao.get().getId());
            movimentacao.setValorHora(config.getValorHora());
            movimentacao.setValorMulta(config.getValorMinutoMulta().multiply(BigDecimal.valueOf(60L)));
            // CALCULAR O TEMPO DE ESTADIA DO VEICULO
            movimentacao.setSaida(LocalDateTime.now());
            Duration tempo = Duration.between(movimentacao.getEntrada(),movimentacao.getSaida());
            long horas = tempo.toHours();
            long minutos = tempo.toMinutes() % 60;
            long segundos = tempo.getSeconds() % 60;
            LocalTime localTime = LocalTime.of((int) horas, (int) minutos, (int) segundos);
            movimentacao.setTempo(localTime);
            //////////////////////////////////////////////////
            //CALCULAR O TEMPO DE MULTA
            LocalDate hoje = LocalDate.now();
            LocalTime fimExpediente = config.getFimExpediente();

            LocalDateTime fimExpedienteHoje = hoje.atTime(fimExpediente);

            Duration duration = Duration.between(movimentacao.getEntrada(), fimExpedienteHoje);


            if ( movimentacao.getTempo().getMinute() > duration.toMinutes() ){

                Duration multa = Duration.between(fimExpedienteHoje, movimentacao.getSaida());


                long minutes = duration.toMinutes();
                movimentacao.setTempoMulta(minutes);

                movimentacao.setValorMulta(config.getValorMinutoMulta().multiply(BigDecimal.valueOf(minutes)));

            }

            Condutor condutor =  condutorRepository.getById(movimentacao.getCondutor().getId());

            condutor.setTempoPago( condutor.getTempoPago() + movimentacao.getTempo().getHour());
            if (condutor.getTempoPago() > config.getTempoParaDesconto()) {
                condutor.setTempoPago( condutor.getTempoPago() - config.getTempoParaDesconto());
                condutor.setTempoDesconto( condutor.getTempoDesconto() + config.getTempoDeDesconto());
            }
            // SE O CONDUTOR TIVER DESCONTO, ENTAO SUBTRAIA O VALOR
            if (condutor.getTempoDesconto() > 0 ) {
                movimentacao.setValorTotal((config.getValorHora().multiply(BigDecimal.valueOf(movimentacao.getTempo().getHour()))).add(movimentacao.getValorMulta()).subtract(config.getValorHora().multiply(BigDecimal.valueOf(condutor.getTempoDesconto()))));


            } else {
                movimentacao.setValorTotal((config.getValorHora().multiply(BigDecimal.valueOf(movimentacao.getTempo().getHour()))).add(movimentacao.getValorMulta()));
            }
            movimentacao.setAtivo(false);
            return ResponseEntity.ok().build();

        } else {
            return ResponseEntity.notFound().build();
        }





    }


 */

    @Transactional
    public ResponseEntity<?> findByPlaca (String placa) {

        List<Movimentacao> optionalMovimentacao = this.repository.findByVeiculoPlate(placa);

        if(optionalMovimentacao.isEmpty()) {
            return ResponseEntity.notFound().build();

        } else {
            List<Movimentacao> movimentacao = this.repository.findByVeiculoPlate(placa);



            return ResponseEntity.ok().body(movimentacao);
        }





    }

}
