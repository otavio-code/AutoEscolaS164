package br.com.senai.autoescolas164.adapter.out.gateway.notification.dto;

import br.com.senai.autoescolas164.adapter.out.gateway.notification.enumeration.EventType;
import br.com.senai.autoescolas164.adapter.out.gateway.notification.enumeration.NotificationChannel;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record NotificationRequest(
        EventType type,
        NotificationChannel channel,
        List<String> contacts,
        LocalDateTime requestedAt,
        String subject,
        String template,
        Map<String, Object> payload,
        String author){
}
