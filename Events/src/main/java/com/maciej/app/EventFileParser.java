package com.maciej.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

public class EventFileParser {
    private static final Logger logger = Logger.getLogger(EventFileParser.class.getName());
    public static List<Event> parseEventFile(String path) {
        logger.info("Parsing file");
        EventParser eventParser = new EventParser();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(path), StandardCharsets.UTF_8)) {
            reader.lines().forEach(json -> {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readTree(json);
                    eventParser.parse(jsonNode);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return eventParser.getEvents();
    }
}
