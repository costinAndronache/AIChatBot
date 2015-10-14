/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Costi
 */
public class Person {
    
    private String _job;
    private int _age;
    private String _name;
    private List<Integer> _answeredQuestions;
    
    
    public Person(String name, List<Integer> answeredQs, int age, String job)
    {
        _name = name;
        _answeredQuestions = answeredQs;
        _job = job;
        _age = age;
    }
    
    
    public String getName()
    {
        return _name;
    }
    
    public boolean hasAnsweredQuestionGroup(int questionGroup)
    {
        return _answeredQuestions.contains(questionGroup);
    }
    
    public void addAskedGroup(int group)
    {
        _answeredQuestions.add(group);
    }
    
    
    public void writeToReader(BufferedWriter writer) throws IOException
    {
        writer.write(_name + "\n" + _job + "\n");
        String ageS = "" + _age;
        writer.write(ageS + ""
                + "\n");
        
        String qNumS = ""+_answeredQuestions.size();
        writer.write(qNumS + "\n");
        for(Integer qId : _answeredQuestions)
        {
            String sqID = "" + qId;
            writer.write(sqID + "\n");
        }
    }
    
    public static Person createPersonFromReader(BufferedReader reader) throws IOException
    {
       String name = reader.readLine();
       String job = reader.readLine();
       String ageAsString = reader.readLine();
     
       String numOfQsString = reader.readLine();
       
       int numOfQs = Integer.parseInt(numOfQsString);
       List<Integer> list = new ArrayList<>();
       
       for(int i=0; i<numOfQs; i++)
       {
           String num = reader.readLine();
           int qId = Integer.parseInt(num);
           list.add(qId);
       }
       
       return new Person(name, list,Integer.parseInt(ageAsString),job);
    }
    
}
