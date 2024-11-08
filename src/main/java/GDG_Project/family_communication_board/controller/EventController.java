package GDG_Project.family_communication_board.controller;

import GDG_Project.family_communication_board.entity.Event;
import GDG_Project.family_communication_board.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<String> createEvent(@RequestBody Event event) {
        eventService.createEvent(event);
        return ResponseEntity.ok("일정이 생성되었습니다. ");
    }
}
