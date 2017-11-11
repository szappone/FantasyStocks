package com.fantasystocks.service.model;

import com.fantasystocks.entity.Player;
import com.fantasystocks.entity.PlayerInSession;

import java.util.List;

public interface PlayerInSessionService {
    void add(PlayerInSession pis);
    List<Long> getAll(String playerName);
}
