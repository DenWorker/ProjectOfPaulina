package org.PaulinesProjects.projectPlanner.planners;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface Planner {
    void addEvent(LocalDateTime beginOfEvent, LocalDateTime endOfEvent, String event);

    void addEventOfToday(LocalTime timeOfBegin, LocalTime timeOfEnd, String event);

    void addEventOfTomorrow(LocalTime timeOfBegin, LocalTime timeOfEnd, String event);

    String getInformationAboutAllEvents();

    String getInformationAboutEventsByDate(LocalDate beginOfEvent);

    boolean deleteEventById(int idOfEvent);
}
