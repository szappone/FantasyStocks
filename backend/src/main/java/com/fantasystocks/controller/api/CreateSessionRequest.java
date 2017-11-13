package com.fantasystocks.controller.api;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Builder

public class CreateSessionRequest {
    @Getter
    private String sessionName;
    @Getter
    private List<String> players;
}
