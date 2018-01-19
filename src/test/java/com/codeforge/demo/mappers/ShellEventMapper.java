package com.codeforge.demo.mappers;

import com.codeforge.demo.model.ShellEventModel;

public class ShellEventMapper implements IShellObjectMapper {

    public static void map(String tag, String value, ShellEventModel shellEvent){

        if(tag.equalsIgnoreCase("URL")) {
            shellEvent.getEvent().setUrl(value);
        } else if (tag.equalsIgnoreCase("timezone")) {
            shellEvent.getEvent().setTimezone(value);
        } else if (tag.equalsIgnoreCase("eventName")) {
            shellEvent.getEvent().setEventName(value);
        } else if (tag.equalsIgnoreCase("eventDateTime")) {
            shellEvent.getEvent().setOnsaleDateTime(value);
        } else if (tag.equalsIgnoreCase("onsaleDateTime")) {
            shellEvent.getEvent().setOnsaleDateTime(value);
        } else if (tag.equalsIgnoreCase("venue")) {
            shellEvent.getEvent().setVenueId(value);
        } else if (tag.equalsIgnoreCase("categoryId")) {
                shellEvent.getEvent().getCategoryIds().add(Integer.parseInt(value));
        } else if (tag.equalsIgnoreCase("attractionId")) {
                shellEvent.getEvent().getAttractionIds().add(Integer.parseInt(value));
        } else if (tag.equalsIgnoreCase("eventId")) {
            shellEvent.getEvent().setEventId(value);
        }
    }
}
