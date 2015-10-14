/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package askingChatbot.managers;
import askingChatbot.interfaces.QuestionProvider;
import askingChatbot.interfaces.PersonProvider;
import models.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author Costi
 */
public class ChatManager {
    
    PersonProvider pp;
    QuestionProvider qp;
    PersonManager pm;
    QuestionManager qm;
    Scanner sc;
    public ChatManager(QuestionProvider qp, PersonProvider pp) throws Exception
    {
        this.pp = pp;
        this.qp = qp;        
    }
    public ChatManager(QuestionManager qm, PersonManager pm) throws Exception
    {
    	 this.pm = pm;
         this.qm = qm; 
    }
    
    public void startChat()
    {
      sc = new Scanner(System.in);
       
       System.out.println("What's your name?");
       String pName = sc.nextLine();
       Person p  = pm.getPersonWithName(pName);
           
       if(p == null)
        {
           p = this.askAndCreateAPersonWithName(pName);
        }
           
        boolean done = false;
 
       while(!done)
       {
           //1. alege o categorie la random
           //2. alege o intrebare la random din categorie
           
       }
    }
      
    
    public Person askAndCreateAPersonWithName(String name)
    {
        System.out.println("What's your age?");
        int age = sc.nextInt();
        System.out.println("What do you do?");
        String job = sc.nextLine();
        
        List<Integer> newList = new ArrayList<>();
        Person p = new Person(name, newList,age,job);
        
        pm.addPerson(p);
        
        return p;
    }
    
    
}
