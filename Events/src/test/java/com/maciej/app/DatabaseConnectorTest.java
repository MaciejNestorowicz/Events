package com.maciej.app;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DatabaseConnectorTest {

    @Mock
    private final DatabaseConnector databaseConnectorMock = mock(DatabaseConnector.class);

    private static final List<Event> events = new ArrayList<>();

    @BeforeAll
    public static void init() {
        events.add(new Event("scsmbstgra", 5, "APPLICATION_LOG", "12345", true));
        events.add(new Event("scsmbstgrc", 8, null, null, true));
        events.add(new Event("scsmbstgrb", 3, null, null, false));
    }

    @Test
    public void shouldConnectToTheDatabase() {
        doAnswer(dbc -> {
            assertEquals(events, dbc.getArgument(0));
            return null;
        }).when(databaseConnectorMock).handleEvents(events);
    }
}