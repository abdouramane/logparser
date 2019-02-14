package fr.logparser;

import fr.logparser.models.Filter;
import fr.logparser.models.Server;

import java.io.IOException;
import java.util.List;

public interface IParser {

    List<Server> getConnectedServers(Filter filter) throws IOException;


}
