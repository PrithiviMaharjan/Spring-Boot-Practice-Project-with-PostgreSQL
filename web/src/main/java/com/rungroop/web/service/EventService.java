package com.rungroop.web.service;

import com.rungroop.web.dto.EventDto;
import com.rungroop.web.models.Event;

import java.util.List;

public interface EventService {

    void createEvent(Long clubId, EventDto eventDto);

    List<EventDto> findAllEvents();

    List<EventDto> searchEvent(String query);
}
