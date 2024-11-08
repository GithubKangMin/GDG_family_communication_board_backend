package GDG_Project.family_communication_board.repository;

import GDG_Project.family_communication_board.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class EventRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createEvent(Event event) {
        String sql = "INSERT INTO event (title, description, event_date, user_id) VALUES (?, ?, ?, ?)";
    }
}