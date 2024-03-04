package com.rungroop.web.service.Impl;

import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.dto.EventDto;
import com.rungroop.web.mapper.ClubMapper;
import com.rungroop.web.mapper.EventMapper;
import com.rungroop.web.models.Club;
import com.rungroop.web.models.Event;
import com.rungroop.web.repository.ClubRepository;
import com.rungroop.web.repository.EventRepository;
import com.rungroop.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private ClubRepository clubRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
        Club club = clubRepository.findById(clubId).get();
        Event event = EventMapper.mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);
    }

    @Override
    public List<EventDto> findAllEvents() {
        return eventRepository.findAll().stream().map(EventMapper::mapToEventDto).collect(Collectors.toList());
    }

    @Override
    public List<EventDto> searchEvent(String query) {
        return eventRepository.searchEvents(query).stream()
                .map(EventMapper::mapToEventDto).collect(Collectors.toList());
    }

    @Override
    public EventDto findEventById(Long eventId) {
        return EventMapper.mapToEventDto(eventRepository.findById(eventId).get());
    }

    @Override
    public void updateEvent(EventDto eventDto) {
        eventRepository.save(EventMapper.mapToEvent(eventDto));
    }

    @Override
    public void deleteEventById(Long eventId) {
        eventRepository.deleteById(eventId);
    }
}
