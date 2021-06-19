package com.maciej.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventParserTest {

    private final EventParser eventParser = new EventParser();

    @Test
    public void shouldReturnListOfEvents() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode1 = objectMapper.readTree("{\"id\":\"scsmbstgra\", " +
                "\"state\":\"STARTED\", " +
                "\"type\":\"APPLICATION_LOG\", " +
                "\"host\":\"12345\", " +
                "\"timestamp\":1491377495212}");
        JsonNode jsonNode2 = objectMapper.readTree("{\"id\":\"scsmbstgrb\", " +
                "\"state\":\"STARTED\", " +
                "\"timestamp\":1491377495213}");
        JsonNode jsonNode3 = objectMapper.readTree("{\"id\":\"scsmbstgrc\", " +
                "\"state\":\"FINISHED\", " +
                "\"timestamp\":1491377495218}");
        JsonNode jsonNode4 = objectMapper.readTree("{\"id\":\"scsmbstgra\", " +
                "\"state\":\"FINISHED\", " +
                "\"type\":\"APPLICATION_LOG\", " +
                "\"host\":\"12345\", " +
                "\"timestamp\":1491377495217}");
        JsonNode jsonNode5 = objectMapper.readTree("{\"id\":\"scsmbstgrc\", " +
                "\"state\":\"STARTED\", " +
                "\"timestamp\":1491377495210}");
        JsonNode jsonNode6 = objectMapper.readTree("{\"id\":\"scsmbstgrb\", " +
                "\"state\":\"FINISHED\", " +
                "\"timestamp\":1491377495216}");
        eventParser.parse(jsonNode1);
        eventParser.parse(jsonNode2);
        eventParser.parse(jsonNode3);
        eventParser.parse(jsonNode4);
        eventParser.parse(jsonNode5);
        eventParser.parse(jsonNode6);

        List<Event> events = new ArrayList<>();
        events.add(new Event("scsmbstgra", 5, "APPLICATION_LOG", "12345", true));
        events.add(new Event("scsmbstgrc", 8, null, null, true));
        events.add(new Event("scsmbstgrb", 3, null, null, false));

        assertEquals(eventParser.getEvents().size(), events.size());
        assertEquals(eventParser.getEvents(), events);
    }

    @Test
    public void listsOfEventsShouldNotBeEqual() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode1 = objectMapper.readTree("{\"id\":\"scsmbstgra\", " +
                "\"state\":\"STARTED\", " +
                "\"type\":\"APPLICATION_LOG\", " +
                "\"host\":\"12345\", " +
                "\"timestamp\":1491377495212}");
        JsonNode jsonNode2 = objectMapper.readTree("{\"id\":\"scsmbstgrb\", " +
                "\"state\":\"STARTED\", " +
                "\"timestamp\":1491377495213}");
        JsonNode jsonNode3 = objectMapper.readTree("{\"id\":\"scsmbstgrc\", " +
                "\"state\":\"FINISHED\", " +
                "\"timestamp\":1491377495218}");
        JsonNode jsonNode4 = objectMapper.readTree("{\"id\":\"scsmbstgra\", " +
                "\"state\":\"FINISHED\", " +
                "\"type\":\"APPLICATION_LOG\", " +
                "\"host\":\"12345\", " +
                "\"timestamp\":1491377495217}");
        JsonNode jsonNode5 = objectMapper.readTree("{\"id\":\"scsmbstgrc\", " +
                "\"state\":\"STARTED\", " +
                "\"timestamp\":1491377495210}");
        JsonNode jsonNode6 = objectMapper.readTree("{\"id\":\"scsmbstgrb\", " +
                "\"state\":\"FINISHED\", " +
                "\"timestamp\":1491377495216}");
        eventParser.parse(jsonNode1);
        eventParser.parse(jsonNode2);
        eventParser.parse(jsonNode3);
        eventParser.parse(jsonNode4);
        eventParser.parse(jsonNode5);
        eventParser.parse(jsonNode6);

        List<Event> events = new ArrayList<>();
        events.add(new Event("scsmbstgrx", 1, "APPLICATION_LOG", "12345", false));
        events.add(new Event("scsmbstgry", 6, null, null, true));

        assertNotEquals(eventParser.getEvents().size(), events.size());
        assertNotEquals(eventParser.getEvents().size(), events.size());
    }
}