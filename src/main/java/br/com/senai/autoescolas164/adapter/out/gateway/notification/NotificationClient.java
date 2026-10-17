package br.com.senai.autoescolas164.adapter.out.gateway.notification;

import br.com.senai.autoescolas164.adapter.out.gateway.notification.dto.NotificationRequest;
import br.com.senai.autoescolas164.adapter.out.gateway.notification.enumeration.EventType;
import br.com.senai.autoescolas164.adapter.out.gateway.notification.enumeration.NotificationChannel;
import br.com.senai.autoescolas164.application.core.domain.Instrucao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Component
public class NotificationClient {
    private final RestClient restClient = null;

    @Value("${microservice.notifier.url}")
    private String notifierUrl;

    public void notificarAgendamento(Instrucao instrucao){
        String author = SecurityContextHolder.getContext().getAuthentication().getName();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
        String dataFormatada = instrucao.getDataHora().format(formatter);

        Map<String, Object> payload = Map.of(
                "acao", "agendada",
                "id", instrucao.getId(),
                "aluno", instrucao.getAluno().getNome(),
                "instrutor", instrucao.getInstrutor().getNome(),
                "especialidade", instrucao.getInstrutor().getEspecialidade(),
                "datahora", dataFormatada
        );

        NotificationRequest request = new NotificationRequest(
                EventType.SCHEDULING,
                NotificationChannel.EMAIL,
                List.of(
                        "email.daautoescola@gmail.com.br",
                        instrucao.getAluno().getEmail(),
                        instrucao.getInstrutor().getEmail()
                ),
                LocalDateTime.now(),
                "Nova instrução agendada",
                "email_notification",
                payload,
                author
        );

        restClient
                .post()
                .uri(notifierUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .toBodilessEntity();
    }
}
