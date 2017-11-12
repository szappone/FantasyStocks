package com.fantasystocks.service.model;

import com.fantasystocks.entity.PlayerInGame;

import java.util.List;

public interface PlayerInGameService {
    void add(PlayerInGame pis);
    List<Long> getAll(String playerName);
}
