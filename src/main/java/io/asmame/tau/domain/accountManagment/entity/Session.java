package io.asmame.tau.domain.accountManagment.entity;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class Session {

    private LocalDateTime validUntil;
    private String token;
}
