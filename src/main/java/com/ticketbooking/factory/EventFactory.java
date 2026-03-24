package com.ticketbooking.factory;

import com.ticketbooking.model.Event;
import com.ticketbooking.model.enums.EventType;

public class EventFactory {

    public static Event createEvent(EventType type, String title, String description,
                                    Integer durationMins, String language, String genre) {
        return switch (type) {
            case MOVIE -> Event.builder()
                    .eventType(type).title(title).description(description)
                    .durationMins(durationMins).language(language).genre(genre)
                    .build();
            case CONCERT -> Event.builder()
                    .eventType(type).title(title).description(description)
                    .durationMins(durationMins).genre(genre)
                    .build();
            case SPORTS -> Event.builder()
                    .eventType(type).title(title).description(description)
                    .build();
            case THEATRE -> Event.builder()
                    .eventType(type).title(title).description(description)
                    .durationMins(durationMins).language(language)
                    .build();
        };
    }
}
