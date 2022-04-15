package fr.evosial.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;


public class ConfigManager
    {
    private final String jsonRead;
    private final String chemin;
    public ConfigManager(String j)
        {
        Path conf = Paths.get(System.getProperty("user.dir"), j);
        chemin = conf.toString();
        jsonRead = j;
        }

    public HashMap<String,String> map() throws IOException
        {
        if (chemin == null) return null;
        Reader reader = Files.newBufferedReader(Path.of(chemin));
        if (reader.ready())
            {
            Gson gson = new Gson();
            Type type = new TypeToken<HashMap<String,String>>(){}.getType();
            HashMap<String,String> map = gson.fromJson(reader, type);
            reader.close();
            return map;
            }
        else
            {
            System.out.println("impossible de lire le fichier " + jsonRead);
            return null;
            }
        }
    public void addEntryValue(String e,String v) throws IOException
        {
        if(map().get(e) != null)return;
        String j = map().toString();
        j = j.replace("{","{\n\t\"");
        j = j.replace("=","\":\"");
        j = j.replace(", ", "\",\n\t\"");
        j = j.replace("}","\",\n\t") ;
        j += "\""+e+"\":\""+v+"\"\n}";
        FileOutputStream fOut = new FileOutputStream(chemin);
        OutputStreamWriter myOutWriter =new OutputStreamWriter(fOut);
        myOutWriter.append(j);
        myOutWriter.close();
        fOut.close();
        }

    public void setValue(String entry, String value) throws IOException
        {
        if(map().get(entry) == null)return;

        String j = map().toString();
        String c =  map().get(entry);

        if (c.equals(value))return;
        j = j.replace("{","{\n\t\"");
        j = j.replace("=","\":\"");
        j = j.replace(", ", "\",\n\t\"");
        j = j.replace(c,value);
        FileOutputStream fOut = new FileOutputStream(chemin);
        OutputStreamWriter myOutWriter =new OutputStreamWriter(fOut);
        myOutWriter.append(j);
        myOutWriter.close();
        fOut.close();
        }
    public String getJsonRead()
        {
        return jsonRead;
        }
    public String getChemin()
        {
        return chemin;
        }

    }
