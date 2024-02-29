package com.rungroop.web.mapper;

import com.rungroop.web.dto.EventDto;
import com.rungroop.web.models.Event;

public class EventMapper {

    public static Event mapToEvent(EventDto eventDto){
        return Event.builder()
                .id(eventDto.getId())
                .club(eventDto.getClub())
                .name(eventDto.getName())
                .type(eventDto.getType())
                .startTime(eventDto.getStartTime())
                .endTIme(eventDto.getEndTIme())
                .createdOn(eventDto.getCreatedOn())
                .updatedOn(eventDto.getUpdatedOn())
                .build();
    }

    public static EventDto mapToEventDto(Event event){
        return EventDto.builder()
                .id(event.getId())
                .club(event.getClub())
                .name(event.getName())
                .type(event.getType())
                .startTime(event.getStartTime())
                .endTIme(event.getEndTIme())
                .createdOn(event.getCreatedOn())
                .updatedOn(event.getUpdatedOn())
                .build();
    }
}
