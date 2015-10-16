/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Costi
 */
public class Person implements Serializable{
    
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
    
    public List<Integer> getAnsweredQuestions()
    {
        return _answeredQuestions;
    }
    
    public String getName()
    {
        return _name;
    }
    
    public int getAge()
    {
        return _age;
    }
    
    public String getJob()
    {
        return _job;
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
        writer.write(_name);writer.newLine();
        writer.write(_job); writer.newLine();
        String ageS = "" + _age;
        writer.write(ageS); writer.newLine();
        
        String qNumS = ""+_answeredQuestions.size();
        writer.write(qNumS); writer.newLine();
        for(int i=0; i<_answeredQuestions.size(); i++)
        {
            Integer qId = _answeredQuestions.get(i);
            String sqID = "" + qId;
            writer.write(sqID);
            if (i<_answeredQuestions.size()-1) 
            {
              writer.newLine();  
            }
        }
    }
    
    public static Person createPersonFromReader(BufferedReader reader) throws IOException
    {
       String name = reader.readLine();
       reader.readLine();
       String job = reader.readLine();
       reader.readLine();
       String ageAsString = reader.readLine();
       reader.readLine();
       String numOfQsString = reader.readLine();
       reader.readLine();
       int numOfQs = Integer.parseInt(numOfQsString);
       List<Integer> list = new ArrayList<>();
       
       for(int i=0; i<numOfQs; i++)
       {
           String num = reader.readLine();
           reader.readLine();
           int qId = Integer.parseInt(num);
           list.add(qId);
       }
       
       return new Person(name, list,Integer.parseInt(ageAsString),job);
    }
    
}
