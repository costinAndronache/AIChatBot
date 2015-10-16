/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author Costi
 */
public class Question implements Serializable
{
    
    private String _questionString;
    private int _category;
    private String _type;
    
    public Question(int category, String questionString, String _type)
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
    
    public String getType()
    {
        return _type;
    }
    
}
