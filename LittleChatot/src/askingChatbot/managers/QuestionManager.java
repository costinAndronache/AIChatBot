/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package askingChatbot.managers;

import askingChatbot.interfaces.QuestionProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import dataBase.DataBase;
import models.Person;
import models.Question;

/**
 *
 * @author Costi
 */
public class QuestionManager implements QuestionProvider {
    
    private Map<Integer,List<Question>> _questions;
    
    @Override
    public int getRandomCategoryWithIgnoreList(List<Integer> ignoredCategories)
    {
        return 2;
    }
    
    @Override
    public Question randomQuestionFromCategory(int category)
    {
        List<Question> ls = this.questionListForCategory(category);
        if(ls != null)
        {
            
        }
        return null;
    }
    
    public Question randomQuestionByType(Person p, String type)
    {
    	DataBase db = DataBase.getInstance();
    	int maxim = 0;
    	try {
			maxim = db.maxNrQuestionsByType(type);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	for (int i=0; i<=1000; i++) {
    		int aux = (int )(Math.random() * maxim + 1);
    		int group = 0;
    		try {
    			ResultSet rs = db.viewQuestionsByType(type);
				int n=1;
				while (rs.next()) {
		        	group = rs.getInt(1);
		        	if (n==aux) break;
		        	n++;
		        }
				
				rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
    		if (!p.hasAnsweredQuestionGroup(group)) 
    		{
    			String[] intrebari = new String[255];
    			
    			try {
					intrebari = db.viewQuestionsVersion(group);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    			int nrQuestion = (int)(Math.random() * intrebari.length + 1);
    			
    			
    			try {
					db.addQuestionAsked(p.getName(),group);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			p.addAskedGroup(group);
    			Question q = new Question(group,intrebari[nrQuestion],"");
    			return q;
    		}
    	}
    	
		return null;
    }
    
    
    public Question randomQuestion(Person p)
    {
    	DataBase db = DataBase.getInstance();
    	int maxim = 0;
    	try {
			maxim = db.maxNrQuestions();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	for (int i=0; i<=1000; i++) {
    		int group = (int )(Math.random() * maxim + 1);
    		if (!p.hasAnsweredQuestionGroup(group)) 
    		{
    			String[] intrebari = new String[255];
    			
    			try {
					intrebari = db.viewQuestionsVersion(group);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    			int nrQuestion = (int)(Math.random() * intrebari.length + 1);
    			
    			
    			try {
					db.addQuestionAsked(p.getName(),group);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			p.addAskedGroup(group);
    			Question q = new Question(group,intrebari[nrQuestion],"");
    			return q;
    		}
    	}
    	
		return null;
    }
    
    public QuestionManager() throws IOException
    {
        
    }
    
    public QuestionManager(BufferedReader reader) throws IOException
    {
        _questions = new HashMap<>();
        String numberString = reader.readLine();
        int numOfQuestions =  Integer.parseInt(numberString);
        
        for(int i=1; i<=numOfQuestions;i++)
        {
            String questionGroupString = reader.readLine();
            String questionString = reader.readLine();
            int questionGroupNum = Integer.parseInt(questionGroupString);
            
            List<Question> ls = this.questionListForCategory(questionGroupNum);
            Question newq = new Question(questionGroupNum,questionString,"");
            ls.add(newq);
        }
    }
    
    private List<Question> questionListForCategory(int category)
    {
        List<Question> list = _questions.get(category);
        
        if (list == null)
        {
            list = new ArrayList<>();
            _questions.put(category, list);
        }
        
        return list;
    }
    
    
}
