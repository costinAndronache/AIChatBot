/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package askingChatbot.managers.simpleManagers;
import askingChatbot.interfaces.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import models.Person;

import java.util.*;
import jdk.jfr.events.FileWriteEvent;
import org.json.*;

/**
 *
 * @author Costi
 */
public class SimplePersonManager implements PersonProvider, Serializable {

    private HashMap<String, Person> _personsByName;
    
    public SimplePersonManager()
    {
        _personsByName = new HashMap<>();
    }
    
    @Override
    public void addPerson(Person p) 
    {
        if (p == null) 
        {
            return;
        }
        _personsByName.put(p.getName(), p);
    }

    @Override
    public Person getPersonWithName(String name) 
    {
        return _personsByName.get(name);
    }
    
    public SimplePersonManager (BufferedReader reader) throws IOException
    {
        String numOfPersonsString = reader.readLine();
        int numOfPersons = Integer.parseInt(numOfPersonsString);
        reader.readLine();
        
        _personsByName = new HashMap<>();
        for(int i=1; i<=numOfPersons;i++)
        {
            Person p = Person.createPersonFromReader(reader);
            _personsByName.put(p.getName(), p);
        }
    }
    
    public void saveToBufferedWriter(BufferedWriter writer) throws IOException
    {
        Collection<Person> persons = _personsByName.values();
        writer.write(persons.size());
        writer.newLine();
        for (Person aPerson : persons) 
        {
            aPerson.writeToReader(writer);
        }
        
    }
    
    
    public void setupFromJSON(String jsonPath) throws Exception
    {
        _personsByName = new HashMap<>();
        byte[] bytes = Files.readAllBytes(Paths.get(jsonPath));
        String jsonString = new String(bytes);
        JSONArray array = new JSONArray(jsonString);
        
        for(int i=0; i<array.length();i++)
        {
            JSONObject obj = array.getJSONObject(i);
            Person p = this.personFromJSON(obj);
            _personsByName.put(p.getName(), p);
        }
        
    }
    
    public void writeToJSON(String jsonPath) throws Exception
    {
        JSONArray array = new JSONArray();
        for(Person p : _personsByName.values())
        {
            JSONObject obj = this.personToJSON(p);
            array.put(obj);
        }
        
        StringWriter writer = new StringWriter();
        array.write(writer);
        FileWriter fw = new FileWriter(new File(jsonPath));
        fw.write(writer.toString());
        fw.flush();
    }
    
    private Person personFromJSON(JSONObject obj)
    {
        String name, job;
        int age;
        List<Integer> answered = new ArrayList<>();
        
        name = obj.getString("name");
        age = obj.getInt("age");
        job = obj.getString("job");
        JSONArray ids = obj.getJSONArray("ids");
        
        for(int i=0; i<ids.length(); i++)
        {
            int id = ids.getInt(i);
            answered.add(id);
        }
        
        return new Person(name, answered, age, job);
    }
    
    private JSONObject personToJSON(Person p)
    {
        JSONArray idsarray = new JSONArray();
        
        JSONObject obj = new JSONObject();
        obj.put("name", p.getName());
        obj.put("job", p.getJob());
        obj.put("age", p.getAge());
        
        for (int id : p.getAnsweredQuestions()) 
        {
            idsarray.put(id);
        }
        
        obj.put("ids", idsarray);
        return obj;
    }
}
