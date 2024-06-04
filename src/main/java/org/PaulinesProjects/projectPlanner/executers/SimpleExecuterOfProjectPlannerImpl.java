package org.PaulinesProjects.projectPlanner.executers;

import lombok.AllArgsConstructor;
import org.PaulinesProjects.projectPlanner.planners.Planner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

@AllArgsConstructor
public class SimpleExecuterOfProjectPlannerImpl implements ExecuterOfProjectPlanner {
    private final Planner planner;

    @Override
    public void run() {
        Scanner consoleScanner = new Scanner(System.in);
        int command = 0;

        while (command != 7) {
            System.out.println("""
                    1 - показать все мероприятия
                    2 - показать все мероприятия определённого дня
                    3 - добавить мероприятие
                    4 - добавить мероприятие на сегодня
                    5 - добавить мероприятие на завтра
                    6 - удалить мероприятие по id
                    7 - выход
                                        
                    """);

            command = consoleScanner.nextInt();
            switch (command) {
                case 1 -> showInformationAboutAllEvents();
                case 2 -> showInformationAboutEventsByDate();
                case 3 -> addEvent();
                case 4 -> addEventOfToday();
                case 5 -> addEventOfTomorrow();
                case 6 -> deleteEventById();
            }


            System.out.println("""
                    ----------------------------------------------------------
                    """);
        }

    }

    @Override
    public void test() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        planner.addEvent(LocalDateTime.parse("2025-05-23 18:23", dateTimeFormatter), LocalDateTime.parse("2025-05-23 20:23", dateTimeFormatter), "Покодить");
        planner.addEvent(LocalDateTime.parse("2025-05-24 18:23", dateTimeFormatter), LocalDateTime.parse("2025-05-24 20:23", dateTimeFormatter), "Покушать");

        planner.addEvent(LocalDateTime.parse("2025-05-25 18:23", dateTimeFormatter), LocalDateTime.parse("2025-05-25 20:23", dateTimeFormatter), "Покушать");
        planner.addEvent(LocalDateTime.parse("2025-05-25 12:00", dateTimeFormatter), LocalDateTime.parse("2025-05-25 14:00", dateTimeFormatter), "Порисовать");
        planner.addEvent(LocalDateTime.parse("2025-05-25 14:00", dateTimeFormatter), LocalDateTime.parse("2025-05-25 16:00", dateTimeFormatter), "Позвонить подруге");

        planner.addEvent(LocalDateTime.parse("2025-05-26 18:23", dateTimeFormatter), LocalDateTime.parse("2025-05-26 20:23", dateTimeFormatter), "Покушать");
        planner.addEvent(LocalDateTime.parse("2025-05-26 12:00", dateTimeFormatter), LocalDateTime.parse("2025-05-26 13:00", dateTimeFormatter), "Посмотреть телевизор");
    }

    private void addEvent() {
        Scanner consoleScanner = new Scanner(System.in);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            System.out.println("Введите дату и время начала мероприятия в формате yyyy-MM-dd HH:mm:");
            LocalDateTime beginOfEvent = LocalDateTime.parse(consoleScanner.nextLine(), dateTimeFormatter);

            System.out.println("Введите дату и время окончания мероприятия в формате yyyy-MM-dd HH:mm:");
            LocalDateTime endOfEvent = LocalDateTime.parse(consoleScanner.nextLine(), dateTimeFormatter);

            if (beginOfEvent.isAfter(endOfEvent)) {
                throw new RuntimeException("Дата начала не может быть больше даты окончания!");
            }

            if (beginOfEvent.isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Дата начала не может быть больше чем сейчас!");
            }
            System.out.println("Введите мероприятие:");
            String event = consoleScanner.nextLine();
            planner.addEvent(beginOfEvent, endOfEvent, event);

        } catch (DateTimeParseException dateTimeParseException) {
            System.out.println("Неверный формат ввода даты и времени!");
            addEvent();
        } catch (RuntimeException runtimeException) {
            System.out.println("Возникли проблемы! " + runtimeException.getMessage());
            addEvent();
        }

    }

    private void addEventOfToday() {
        Scanner consoleScanner = new Scanner(System.in);
        try {
            System.out.println("Введите время начала мероприятия в формате HH:mm:");
            LocalTime timeOfBegin = LocalTime.parse(consoleScanner.nextLine());

            System.out.println("Введите время окончания мероприятия в формате HH:mm:");
            LocalTime timeOfEnd = LocalTime.parse(consoleScanner.nextLine());

            if (timeOfBegin.isAfter(timeOfEnd)) {
                throw new RuntimeException("Время начала не может быть больше времени окончания!");
            }

            if (timeOfBegin.isBefore(LocalTime.now())) {
                throw new RuntimeException("Время начала не может быть больше чем сейчас!");
            }

            System.out.println("Введите мероприятие:");
            String event = consoleScanner.nextLine();
            planner.addEventOfToday(timeOfBegin, timeOfEnd, event);

        } catch (DateTimeParseException dateTimeParseException) {
            System.out.println("Неверный формат ввода времени!");
            addEventOfToday();
        } catch (RuntimeException runtimeException) {
            System.out.println("Возникли проблемы! " + runtimeException.getMessage());
            addEventOfToday();
        }
    }

    private void addEventOfTomorrow() {
        Scanner consoleScanner = new Scanner(System.in);

        try {
            System.out.println("Введите время начала мероприятия в формате HH:mm:");
            LocalTime timeOfBegin = LocalTime.parse(consoleScanner.nextLine());

            System.out.println("Введите время окончания мероприятия в формате HH:mm:");
            LocalTime timeOfEnd = LocalTime.parse(consoleScanner.nextLine());

            if (timeOfBegin.isAfter(timeOfEnd)) {
                throw new RuntimeException("Время начала не может быть больше времени окончания!");
            }

            System.out.println("Введите мероприятие:");
            String event = consoleScanner.nextLine();
            planner.addEventOfTomorrow(timeOfBegin, timeOfEnd, event);

        } catch (DateTimeParseException dateTimeParseException) {
            System.out.println("Неверный формат ввода времени!");
            addEventOfTomorrow();
        } catch (RuntimeException runtimeException) {
            System.out.println("Возникли проблемы! " + runtimeException.getMessage());
            addEventOfTomorrow();
        }
    }

    private void deleteEventById() {
        Scanner consoleScanner = new Scanner(System.in);

        try {
            System.out.println("Введите id удаляемого мероприятия:");
            int idOfEvent = consoleScanner.nextInt();
            if (planner.deleteEventById(idOfEvent)) {
                System.out.println("Мероприятие с id " + idOfEvent + " было успешно удалено!");
            } else {
                System.out.println("Мероприятия с таким id не найдено");
            }
        } catch (Exception exception) {
            System.out.println("Возникли проблемы! Попробуйте ещё раз!");
            deleteEventById();
        }

    }

    private void showInformationAboutAllEvents() {
        System.out.println(planner.getInformationAboutAllEvents());
    }

    private void showInformationAboutEventsByDate() {
        Scanner consoleScanner = new Scanner(System.in);

        try {
            System.out.println("Введите дату мероприятия в формате yyyy-MM-dd:");

            LocalDate beginOfEvent = LocalDate.parse(consoleScanner.nextLine());
            System.out.println(planner.getInformationAboutEventsByDate(beginOfEvent));

        } catch (DateTimeParseException dateTimeParseException) {
            System.out.println("Неверный формат ввода даты и времени!");
            addEvent();
        } catch (Exception exception) {
            System.out.println("Возникли проблемы! Попробуйте ещё раз!");
            showInformationAboutEventsByDate();
        }
    }

}
