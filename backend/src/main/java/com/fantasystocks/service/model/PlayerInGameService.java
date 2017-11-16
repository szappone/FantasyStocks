package com.fantasystocks.service.model;

import com.fantasystocks.controller.api.Session;
import com.fantasystocks.entity.Game;
import com.fantasystocks.entity.PlayerInGame;

import java.util.List;

public interface PlayerInGameService {
    void add(PlayerInGame pis);
    void remove(PlayerInGame pis);
}
