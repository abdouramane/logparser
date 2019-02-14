# Logparser

Le programme prend en entrée le nom d'un fichier log qui respecte le format (timestamp hostname_de_connexion hostname_auquel_on_se_connecte)
et affiche la liste des serveurs connectés dans la dernière heure (en passant le paramètre Filtre.BY_HOUR à la méthode IParser.getConnectedServers), le paramètre Filtre.ALL peut être utilisé aussi pour avoir tous les serveurs.

Exemple de fichier au bon format :

1366815793 local yoda

1366815795 brunt yoda

1366815811 ngix brunt

### Important : Le programme regarde uniquement les logs dans l'heure où le programme a été lancé (si vous utilisez le paramètre Filtre.BY_HOUR), donc assurez-vous d'avoir des données/logs dont le timestamp correpond bien à l'heure d'exec du programme.
Vous pouvez utiliser ce site pour récupérer des timestamp qui correspondent à l'heure d'exec du prog : https://www.epochconverter.com/
### Note : Un fichier d'exemple situé (src/main/resource/input.log) peut être utilisé.


# Exécuter le programme 
La classe fr.logparser.main.Main sert de point d'entrée.

Cloner le repo git ou téléchargé les sources depuis votre poste

## Dans un IDE 
- Passer le fichier de log à la fonction main de la classe Main.
- Au besoin vous pouvez changer la valeur du filtre ligne 26 par Filter.ALL par exemple : List<Server> servers = parser.getConnectedServers(Filter.BY_HOUR);

## En ligne de commande avec le Jar (logparser/executable/log-parser-1.0.jar)
- Exécuter la commande suivante "java -cp log-parser-1.0.jar fr.logparser.main.Main <fichier>" où <fichier> est votre fichier de log.

