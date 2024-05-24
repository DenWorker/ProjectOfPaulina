package org.PaulinesProjects.projectPlanner.events;

import java.time.LocalDateTime;

public interface Event {
    String getInformationAboutEvent();

    int getIdOfEvent();

    LocalDateTime getBeginOfEvent();
}
