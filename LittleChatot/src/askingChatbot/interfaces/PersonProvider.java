/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package askingChatbot.interfaces;

import models.Person;

/**
 *
 * @author Costi
 */
public interface PersonProvider {

    void addPerson(Person p);

    Person getPersonWithName(String name);
    
}
