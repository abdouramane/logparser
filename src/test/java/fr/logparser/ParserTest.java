package fr.logparser;

import fr.logparser.impl.Parser;
import fr.logparser.models.Filter;
import fr.logparser.models.Server;
import fr.logparser.repository.ILogRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParserTest {

    private IParser parser;

    @Mock
    private ILogRepository logRepository;
    
    private List<Server> servers;


    @Before
    public void setUp() {
        parser = new Parser(logRepository);
        
        servers = new ArrayList<>();

        Server server = new Server();
        server.setName("local");
        server.setConnectedServers(Sets.newSet("apache", "ngix"));
        server.setNumberOfConnections(2);
        servers.add(server);

    }

    @Test
    public void testGetAllServersConnections() throws IOException {
        when(logRepository.getAllLogs()).thenReturn(Optional.ofNullable(servers));

        List<Server> result = parser.getConnectedServers(Filter.ALL);

        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("local", result.get(0).getName());
        Assert.assertEquals(2, result.get(0).getConnectedServers().size());
        Assert.assertEquals(2, result.get(0).getNumberOfConnections().intValue());
    }

    @Test
    public void testGetAllServersConnectionsByHour() {
        //TODO
    }

}
