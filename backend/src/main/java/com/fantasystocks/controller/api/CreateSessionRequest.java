package com.fantasystocks.controller.api;

import lombok.Data;
import com.fantasystocks.entity.Player;
import lombok.Builder;
import lombok.Getter;

@Data
@Builder

public class CreateSessionRequest {
    @Getter
    private String sessionName;
    @Getter
    private String[] players;
}
