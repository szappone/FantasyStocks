package com.fantasystocks.controller.api;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class CreatePlayerRequest {
    private String username;
}
