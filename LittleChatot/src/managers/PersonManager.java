/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import java.io.*;
import java.util.*;
import models.Person;

/**
 *
 * @author Costi
 */
public class PersonManager 
{
    Map<String, Person> _personsByName;
    
    public Person getPersonWithName(String name)
    {
        return _personsByName.get(name);
    }
    
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
    
}
