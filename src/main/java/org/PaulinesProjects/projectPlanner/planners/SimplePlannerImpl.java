package org.PaulinesProjects.projectPlanner.planners;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.PaulinesProjects.projectPlanner.events.Event;
import org.PaulinesProjects.projectPlanner.events.SimpleEventImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class SimplePlannerImpl implements Planner {

    private List<Event> eventList;
    private int generateValueOfId;

    public SimplePlannerImpl() {
        eventList = new ArrayList<>();
        generateValueOfId = 0;
    }

    @Override
    public void addEvent(LocalDateTime beginOfEvent, LocalDateTime endOfEvent, String event) {
        eventList.add(new SimpleEventImpl(generateValueOfId++, beginOfEvent, endOfEvent, event));
    }

    @Override
    public void addEventOfToday(LocalTime timeOfBegin, LocalTime timeOfEnd, String event) {
        eventList.add(new SimpleEventImpl(generateValueOfId++,
                LocalDateTime.of(LocalDate.now(), timeOfBegin),
                LocalDateTime.of(LocalDate.now(), timeOfEnd),
                event));
    }

    @Override
    public void addEventOfTomorrow(LocalTime timeOfBegin, LocalTime timeOfEnd, String event) {
        eventList.add(new SimpleEventImpl(generateValueOfId++,
                LocalDateTime.of(LocalDate.now().plusDays(1), timeOfBegin),
                LocalDateTime.of(LocalDate.now().plusDays(1), timeOfEnd),
                event));
    }

    @Override
    public String getInformationAboutAllEvents() {
        if (eventList.isEmpty()) {
            return "Нет запланированных мероприятий!";
        } else {
            return eventList.stream()
                    .map(Event::getInformationAboutEvent)
                    .collect(Collectors.joining());
        }
    }

    @Override
    public String getInformationAboutEventsByDate(LocalDate beginOfEvent) {
        String result = eventList.stream()
                .filter(t -> t.getBeginOfEvent().toLocalDate().equals(beginOfEvent))
                .map(Event::getInformationAboutEvent)
                .collect(Collectors.joining());

        if (result.isEmpty()) {
            return "Нет запланированных мероприятий на заданное число!";
        } else {
            return result;
        }
    }

    @Override
    public void deleteEventById(int idOfEvent) {
        eventList.removeIf(t -> t.getIdOfEvent() == idOfEvent);
    }
}
