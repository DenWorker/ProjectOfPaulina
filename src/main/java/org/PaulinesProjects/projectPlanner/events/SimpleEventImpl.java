package org.PaulinesProjects.projectPlanner.events;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class SimpleEventImpl implements Event {
    private int idOfEvent;
    private LocalDateTime beginOfEvent;
    private LocalDateTime endOfEvent;
    private String event;


    @Override
    public String getInformationAboutEvent() {
        return "\n" + "***********************************" + "\n" +
                "id мероприятия: " + idOfEvent + "\n" +
                "Начало: " + beginOfEvent + "\n" +
                "Конец: " + endOfEvent + "\n" +
                "Мероприятие: " + event + "\n" +
                "***********************************" + "\n";
    }

}
