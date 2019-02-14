package fr.logparser.repository;

import fr.logparser.models.LogItem;
import fr.logparser.models.Server;
import fr.logparser.repository.impl.LogRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LogRepositoryTest {

    private ILogRepository logRepository;

    @Mock
    private ILogLoader logLoader;

    private List<LogItem> logItems;

    @Before
    public void setUp() {

        logRepository = new LogRepository("test.log", logLoader);

        logItems = new ArrayList<>();

        LogItem logItem = new LogItem();
        logItem.setLogTime(Instant.now().minus(30, ChronoUnit.MINUTES));
        logItem.setServerHostname("local");
        logItem.setConnectedServerHostname("apache");
        logItems.add(logItem);

        logItem = new LogItem();
        logItem.setLogTime(Instant.now().minus(15, ChronoUnit.MINUTES));
        logItem.setServerHostname("local");
        logItem.setConnectedServerHostname("tomcat");
        logItems.add(logItem);

        logItem = new LogItem();
        logItem.setLogTime(Instant.now().minus(5, ChronoUnit.MINUTES));
        logItem.setServerHostname("yoda");
        logItem.setConnectedServerHostname("camel");
        logItems.add(logItem);

        logItem = new LogItem();
        logItem.setLogTime(Instant.now());
        logItem.setServerHostname("yoda");
        logItem.setConnectedServerHostname("camel");
        logItems.add(logItem);

        logItem = new LogItem();
        logItem.setLogTime(Instant.now().minus(2, ChronoUnit.HOURS));
        logItem.setServerHostname("local");
        logItem.setConnectedServerHostname("nginx");
        logItems.add(logItem);

        logItem = new LogItem();
        logItem.setLogTime(Instant.now().minus(2, ChronoUnit.HOURS));
        logItem.setServerHostname("pastry");
        logItem.setConnectedServerHostname("nginx");
        logItems.add(logItem);
    }

    @Test
    public void testGetAllServers() throws IOException {
        when(logLoader.load(anyString())).thenReturn(logItems);

        Optional<List<Server>> result = logRepository.getAllLogs();

        Assert.assertTrue(result.isPresent());

        List<Server> servers = result.get();

        Assert.assertEquals(3, servers.size());
        Assert.assertTrue(servers.stream().anyMatch(server -> "pastry".equals(server.getName())));
    }

    @Test
    public void testGetServersConnectionsByHour() throws IOException {
       when(logLoader.load(anyString())).thenReturn(logItems);

       Optional<List<Server>> result = logRepository.getLogsBy(ChronoUnit.HOURS);

        Assert.assertTrue(result.isPresent());

        List<Server> servers = result.get();

        Assert.assertEquals(2, servers.size());
        Assert.assertEquals("local", servers.get(0).getName());
        Assert.assertEquals(2, servers.get(0).getNumberOfConnections().intValue());

        Assert.assertEquals("yoda", servers.get(1).getName());
        Assert.assertEquals(2, servers.get(1).getNumberOfConnections().intValue());
        Assert.assertEquals(1, servers.get(1).getConnectedServers().size());
    }

    @Test
    public void testGetServersConnectionsByHourFails() {
        //TODO
    }
}
