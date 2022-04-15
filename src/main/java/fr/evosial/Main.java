package fr.evosial;

import fr.evosial.utils.ConfigManager;
import fr.evosial.utils.commandTools.MessageManager;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;

import java.io.IOException;



public class Main
    {

    //methode principal du programme
    public static void main(String[] args) throws IOException
        {
        ConfigManager configManager = new ConfigManager("config.json");
        //affichage
        System.out.println("_____________________________________________________");
        System.out.println("|     ###       ###            ###       ###   ###  |");
        System.out.println("|   ### ###   ### ###        ### ###      ### ###   |");
        System.out.println("|  ###   ### ###   ###      ###   ###       ###     |");
        System.out.println("| ###      ###      ###    ### ### ###     ### ###  |");
        System.out.println("|###                 ###  ###       ###   ###   ### |");
        System.out.println("-----------------------------------------------------\n\n");
        System.out.println("Démarrage du bot .... ");


        System.out.println("Activattion des logs");
        //activation des logs
        FallbackLoggerConfiguration.setTrace(true);

        System.out.println("Lecture du fichier "+configManager.getJsonRead()+" ...");

        System.out.println("\tTerminer \n");

        System.out.println("Récupération de la valeur correpondant a l'entrée Tokens");

        //Création de la connection a api discord
        DiscordApi api = new DiscordApiBuilder().setToken(configManager.map().get("tokens")).login().join();

        System.out.println("connexion à L'API: OK");
        api.addMessageCreateListener(MessageManager::create);
        System.out.println("Le chemin du bot est :" + configManager.getChemin());

        int c = api.getServerTextChannels().toArray().length + api.getServerVoiceChannels().toArray().length;

        System.out.println("\nNombre de Channel sur le discord est de : " + c);

        System.out.println("\nNombre de Channel Vocal sur le discord est de : "
                + api.getServerVoiceChannels().toArray().length);

        System.out.println("\nNombre de Channel écrit sur le discord est de : "
                + api.getServerTextChannels().toArray().length);

        System.out.println("\nNombre de Role sur le Serveur : " + api.getRoles().toArray().length);

        System.out.println("\nNombre de Personne sur le Serveur : " + api.getCachedUsers().toArray().length);

        System.out.println("\nNombre de Personne inviter sur le Serveur : "
                + api.getServerJoinListeners().toArray().length);

        System.out.println("\nNombre de Personne qui leave le Serveur : "
                + api.getServerMemberLeaveListeners().toArray().length);
        System.out.println("\nLe prefix du bot est :" + configManager.map().get("prefix"));
        System.out.println("\nLe Bot est :"+api.getStatus()+ ":) \nLets Go !!!!");
        }
    }
