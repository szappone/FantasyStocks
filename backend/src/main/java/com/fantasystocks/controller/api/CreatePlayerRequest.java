package com.fantasystocks.controller.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePlayerRequest {
    private String playerName;
}
