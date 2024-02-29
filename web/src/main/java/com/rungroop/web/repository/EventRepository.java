package com.rungroop.web.repository;

import com.rungroop.web.dto.EventDto;
import com.rungroop.web.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e from Event e where e.name like concat('%', :query ,'%') ")
    List<Event> searchEvents(String query);
}
