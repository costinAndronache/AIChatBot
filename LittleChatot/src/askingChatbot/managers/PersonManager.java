/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package askingChatbot.managers;

import askingChatbot.interfaces.PersonProvider;

import java.io.*;
import java.util.*;

import dataBase.DataBase;
import models.Person;

/**
 *
 * @author Costi
 */
public class PersonManager implements PersonProvider 
{
    Map<String, Person> _personsByName;
    DataBase db = DataBase.getInstance();
    
    
    @Override
    public Person getPersonWithName(String name)
    {
        return _personsByName.get(name);
    }
    
    @Override
    public void addPerson(Person p)
    {
        _personsByName.put(p.getName(), p);
    }
    
    
    public PersonManager(BufferedReader reader) throws IOException
    {
        String numOfPersonsString = reader.readLine();
        int numOfPersons = Integer.parseInt(numOfPersonsString);
        
        _personsByName = new HashMap<>();
        for(int i=1; i<=numOfPersons;i++)
        {
            Person p = Person.createPersonFromReader(reader);
            _personsByName.put(p.getName(), p);
        }
    }
    
    public PersonManager(Person[] p) throws IOException
    {
        int numOfPersons = p.length;
        
        _personsByName = new HashMap<>();
        for(int i=1; i<=numOfPersons;i++)
        {
            _personsByName.put(p[i].getName(), p[i]);
        }
    }
    
}
