package com.fantasystocks.service.impl;

import com.fantasystocks.dao.model.SessionDao;
import com.fantasystocks.entity.Session;
import com.fantasystocks.service.model.SessionService;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class SessionServiceImpl implements SessionService {
    @Autowired
    private SessionDao sessionDao;

    @Transactional
    @Override
    public void add(Session session) {
        sessionDao.add(session);
    }

    @Transactional
    @Override
    public Session get(long sessionID) {
        return sessionDao.get(sessionID);
    }

}
