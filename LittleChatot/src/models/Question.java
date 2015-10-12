/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Costi
 */
public class Question {
    
    private String _questionString;
    private int _category;
    
    public Question(int category, String questionString)
    {
        _category = category;
        _questionString = questionString;
    }
    
    public String getQuestion()
    {
        return _questionString;
    }
    
    public int getCategory()
    {
        return _category;
    }
    
}
