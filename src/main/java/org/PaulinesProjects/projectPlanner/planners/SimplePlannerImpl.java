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

    public SimplePlannerImpl() {
        eventList = new ArrayList<>();
    }

    @Override
    public void addEvent(LocalDateTime beginOfEvent, LocalDateTime endOfEvent, String event) {
        eventList.add(new SimpleEventImpl(eventList.size(), beginOfEvent, endOfEvent, event));
    }

    @Override
    public void addEventOfToday(LocalTime timeOfBegin, LocalTime timeOfEnd, String event) {
        eventList.add(new SimpleEventImpl(eventList.size(),
                LocalDateTime.of(LocalDate.now(), timeOfBegin),
                LocalDateTime.of(LocalDate.now(), timeOfEnd),
                event));
    }

    @Override
    public void addEventOfTomorrow(LocalTime timeOfBegin, LocalTime timeOfEnd, String event) {
        eventList.add(new SimpleEventImpl(eventList.size(),
                LocalDateTime.of(LocalDate.now().plusDays(1), timeOfBegin),
                LocalDateTime.of(LocalDate.now().plusDays(1), timeOfEnd),
                event));
    }

    @Override
    public String getInformationAboutAllEvents() {
        return eventList.stream()
                .map(Event::getInformationAboutEvent)
                .collect(Collectors.joining());
    }

    @Override
    public String getInformationAboutEventsByDate(LocalDate beginOfEvent) {
        return eventList.stream()
                .filter(t -> t.getBeginOfEvent().toLocalDate().equals(beginOfEvent))
                .map(Event::getInformationAboutEvent)
                .collect(Collectors.joining());
    }

    @Override
    public void deleteEventById(int idOfEvent) {
        eventList.removeIf(t -> t.getIdOfEvent() == idOfEvent);
    }
}
