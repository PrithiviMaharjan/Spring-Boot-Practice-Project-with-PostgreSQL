package com.rungroop.web.service;

import com.rungroop.web.models.Event;

import java.util.List;

public interface EventService {

    List<Event> findAllEvents();
    void saveEvent();
}
