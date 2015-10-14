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
import java.util.*;
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
            Question newq = new Question(questionGroupNum,questionString);
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
