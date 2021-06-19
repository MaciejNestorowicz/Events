package com.maciej.app;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class EventParser {
    private final Logger logger = Logger.getLogger(EventParser.class.getName());
    private final List<Event> events = new ArrayList<>();
    private final Map<String, JsonNode> eventMap = new HashMap<>();

    public void parse(JsonNode jsonNode) {
        String id = jsonNode.get("id").asText();
        logger.info("Parsing Event of ID " + id);
        if (eventMap.isEmpty()) eventMap.put(jsonNode.get("id").asText(), jsonNode);
        else {
            if (eventMap.containsKey(id)) {
                events.add(eventBuilder(eventMap.get(id), jsonNode));
                eventMap.remove(id);
            } else {
                eventMap.put(id, jsonNode);
            }
        }
    }

    private Event eventBuilder(JsonNode jsonNode1, JsonNode jsonNode2) {
        String id = jsonNode1.get("id").asText();
        int duration = getEventDuration(jsonNode1, jsonNode2);
        boolean alert = duration > 4;
        if (!jsonNode1.has("type") && !jsonNode1.has("host")) return new Event(id, duration, alert);
        else return new Event(id, duration, jsonNode1.get("type").asText(), jsonNode1.get("host").asText(), alert);
    }

    private int getEventDuration(JsonNode jsonNode1, JsonNode jsonNode2) {
        if (jsonNode1.get("state").asText().equals("STARTED"))
            return (int) (jsonNode2.get("timestamp").asLong() - jsonNode1.get("timestamp").asLong());
        else return (int) (jsonNode1.get("timestamp").asLong() - jsonNode2.get("timestamp").asLong());
    }

    public List<Event> getEvents() {
        logger.info("Parsed " + events.size() + " events");
        return events;
    }
}
