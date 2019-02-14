package fr.logparser.main;

import fr.logparser.IParser;
import fr.logparser.impl.Parser;
import fr.logparser.models.Filter;
import fr.logparser.models.Server;
import fr.logparser.repository.ILogRepository;
import fr.logparser.repository.impl.LogLoader;
import fr.logparser.repository.impl.LogRepository;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * Main classe du log parser
 *
 */
public class Main {

    public static void main(String[] args) throws IOException {

        if(args == null || args.length == 0) {
            throw new RuntimeException("File must not be empty : " +
                    "java -cp log-parser-1.0-SNAPSHOT.jar fr.logparser.main.Main <file>");
        }

        ILogRepository logRepository = new LogRepository(args[0], new LogLoader());
        IParser parser = new Parser(logRepository);


        List<Server> servers = parser.getConnectedServers(Filter.BY_HOUR);

        System.out.println("################ - Servers list here : ");
        servers.forEach(System.out::println);

        System.out.println("################ - Server with the max number of connections : ");
        servers.stream().max(Comparator.comparing(Server::getNumberOfConnections)).ifPresent(
                System.out::println
        );


    }

}
