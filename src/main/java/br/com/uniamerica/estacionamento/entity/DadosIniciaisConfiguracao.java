package br.com.uniamerica.estacionamento.entity;

import br.com.uniamerica.estacionamento.repository.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DadosIniciaisConfiguracao implements CommandLineRunner {
    private final ConfiguracaoRepository configuracaoRepository;

    @Autowired
    public DadosIniciaisConfiguracao(ConfiguracaoRepository configuracaoRepository) {
        this.configuracaoRepository = configuracaoRepository;
    }

    @Override
    public void run(String... args) {
        Configuracao configuracao = new Configuracao();
        configuracao.init();
        configuracaoRepository.save(configuracao);
    }
}