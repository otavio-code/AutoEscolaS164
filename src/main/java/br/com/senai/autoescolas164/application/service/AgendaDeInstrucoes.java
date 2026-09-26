package br.com.senai.autoescolas164.application.service;

import br.com.senai.autoescolas164.adapter.in.controller.request.instrucao.DadosAgendamento;
import br.com.senai.autoescolas164.adapter.in.controller.response.instrucao.DadosDetalhamentoAgendamento;
import br.com.senai.autoescolas164.application.core.domain.Aluno;
import br.com.senai.autoescolas164.application.core.domain.Instrucao;
import br.com.senai.autoescolas164.application.core.domain.Instrutor;
import br.com.senai.autoescolas164.application.core.specification.ValidadorAgendamento;
import br.com.senai.autoescolas164.application.port.out.AlunoRepository;
import br.com.senai.autoescolas164.application.port.out.InstrucaoRepository;
import br.com.senai.autoescolas164.application.port.out.InstrutorRepository;
import br.com.senai.autoescolas164.exception.type.AlunoNotFoundException;
import br.com.senai.autoescolas164.exception.type.InstrutorNotFoundException;
import br.com.senai.autoescolas164.exception.type.ValidacaoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AgendaDeInstrucoes {
    private final InstrucaoRepository repository;
    private final AlunoRepository alunoRepository;
    private final InstrutorRepository instrutorRepository;
    private final List<ValidadorAgendamento> validadoresAgendamento;

    public DadosDetalhamentoAgendamento agendar(DadosAgendamento dados) {
        log.info("Agendamento iniciado para Aluno: {}, Instrutor: {}, Data: {}",
                dados.idAluno(),
                dados.idInstrutor(),
                dados.dataHora()
        );
        if (!alunoRepository.existsById(dados.idAluno())) {
            log.warn(
                    "Tentativa de agendamento para aluno inexistente. Aluno: {}",
                    dados.idAluno()
            );
            throw new AlunoNotFoundException("ID do aluno informado não existe!");
        }
        if (dados.idInstrutor() != null && !instrutorRepository.existsById(dados.idInstrutor())) {
            log.warn(
                    "Tentativa de agendamento com instrutor inexistente. Aluno: {}",
                    dados.idInstrutor()
            );
            throw new InstrutorNotFoundException("ID do instrutor informado não existe!");
        }
        //Validações
        log.debug("Executando os validadores do agendamento...");
        validadoresAgendamento.forEach(validador -> validador.validar(dados));

        Aluno aluno = alunoRepository.getReferenceById(dados.idAluno());
        Instrutor instrutor = escolherInstrutor(dados);
        if (instrutor == null) {
            throw new ValidacaoException("Não existe instrutor disponível para a data/hora informada!");
        }
        Instrucao instrucao = new Instrucao(
                null,
                aluno,
                instrutor,
                dados.dataHora()
        );
        Instrucao salva = repository.save(instrucao);
        log.info("Instrução agendada com sucesso!");
        return new DadosDetalhamentoAgendamento(salva);
    }

    private Instrutor escolherInstrutor(DadosAgendamento dados) {
        if (dados.idInstrutor() != null) {
            return instrutorRepository.getReferenceById(dados.idInstrutor());
        }
        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é campo obrigatório, caso o instrutor não seja informado!");
        }
        return instrutorRepository.escolherInstrutorAleatorioDisponivel(
                dados.especialidade(),
                dados.dataHora()
        );
    }
}