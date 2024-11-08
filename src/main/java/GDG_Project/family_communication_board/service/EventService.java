package GDG_Project.family_communication_board.service;

import GDG_Project.family_communication_board.entity.Event;
import GDG_Project.family_communication_board.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public void createEvent(Event event) {
        eventRepository.createEvent(event);
    }
}