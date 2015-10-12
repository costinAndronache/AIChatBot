/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;
import models.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author Costi
 */
public class ChatManager {
    
    PersonManager pm;
    QuestionManager qm;
    Scanner sc;
    public ChatManager() throws Exception
    {
        
        FileReader persReader = new FileReader(new File("persoane.txt"));
        BufferedReader persBR = new BufferedReader(persReader);
        
        pm = new PersonManager(persBR);
        
        FileReader qsReader = new FileReader(new File("persoane.txt"));
        BufferedReader qsBR = new BufferedReader(persReader);
        
        qm = new QuestionManager(qsBR);
        
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
