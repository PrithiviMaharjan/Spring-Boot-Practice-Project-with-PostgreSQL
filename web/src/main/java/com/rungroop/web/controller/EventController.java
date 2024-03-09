package com.rungroop.web.controller;

import com.rungroop.web.dto.EventDto;
import com.rungroop.web.models.Event;
import com.rungroop.web.models.UserEntity;
import com.rungroop.web.security.SecurityUtil;
import com.rungroop.web.service.EventService;
import com.rungroop.web.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EventController {

    private final EventService eventService;
    private final UserService userService;

    @Autowired
    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping("/events")
    public String listEvents(Model model) {
        String userEmail = SecurityUtil.getSessionUser();
        if (userEmail != null) {
            UserEntity user = userService.findByEmail(userEmail);
            model.addAttribute("user", user);
        }

        List<EventDto> events = eventService.findAllEvents();
        model.addAttribute("events", events);
        return "events-list";
    }

    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable(name = "clubId") Long clubId, Model model) {
        Event event = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "events-create";
    }

    @PostMapping("/events/{clubId}")
    public String createEvent(
            @PathVariable(value = "clubId") Long clubId,
            @ModelAttribute("event") EventDto eventDto,
            BindingResult result,
            Model model) {

        if (result.hasErrors()){
            model.addAttribute("event", eventDto);
            return "clubs-create";
        }
        eventService.createEvent(clubId, eventDto);
        return "redirect:/clubs/" + clubId;
    }

    @GetMapping("/events/search")
    public String searchEvent(@RequestParam(value = "query") String query, Model model) {
        model.addAttribute("events", eventService.searchEvent(query));
        return "events-list";
    }

    @GetMapping("/events/{eventId}")
    public String eventDetail(@PathVariable("eventId") Long eventId, Model model) {
        String userEmail = SecurityUtil.getSessionUser();
        if (userEmail != null) {
            UserEntity user = userService.findByEmail(userEmail);
            model.addAttribute("user", user);
        }

        EventDto eventDto = eventService.findEventById(eventId);
        model.addAttribute("event", eventDto);
        return "events-detail";
    }

    @GetMapping("/events/{eventId}/edit")
    public String editEventForm(@PathVariable("eventId") Long eventId, Model model) {
        EventDto eventDto = eventService.findEventById(eventId);
        model.addAttribute("event", eventDto);
        return "events-edit";
    }

    @PostMapping("/events/{eventId}/edit")
    public String updateEvent(
            @PathVariable("eventId") Long eventId,
            @Valid @ModelAttribute("event") EventDto eventDto,
            BindingResult result,
            Model model) {

        if (result.hasErrors()){
            model.addAttribute("event", eventDto);
            return "events-edit";
        }
        EventDto newEventDto = eventService.findEventById(eventId);
        eventDto.setId(eventId);
        eventDto.setClub(newEventDto.getClub());
        eventService.updateEvent(eventDto);
        return "redirect:/events";
    }

    @GetMapping("events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId) {
        eventService.deleteEventById(eventId);
        return "redirect:/events";
    }

}
