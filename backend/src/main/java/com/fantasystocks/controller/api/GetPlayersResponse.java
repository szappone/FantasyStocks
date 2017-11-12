package com.fantasystocks.controller.api;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetPlayersResponse {
    List<String> playerNames;
}
