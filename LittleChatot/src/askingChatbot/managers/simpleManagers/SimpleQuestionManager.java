/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package askingChatbot.managers.simpleManagers;

import askingChatbot.interfaces.*;
import java.util.*;
import models.*;
/**
 *
 * @author Costi
 */
public class SimpleQuestionManager implements QuestionProvider{

    private Map<Integer, List<String>> questionMap;
    
    public SimpleQuestionManager()
    {
        this.setDummyData();
    }
    
    
    private void setDummyData()
    {
        String whatsYourNameStrings[] = {"And what is your name?", 
                                         "Can I ask for your name?",
                                         "May I know your name?"};
        
        String whatsYourAgeStrings[] = { "Age pls?", "Pls age?", "Age age pls"};
        String whatDoYouDoStrings[] = {"What do you do?", "What do you work as?"};
        
        this.questionMap = new HashMap<>();
        this.questionMap.put(GlobalQuestionGroupIDs.QUESTION_GROUP_ASK_FOR_NAME, 
                             Arrays.asList(whatsYourNameStrings));
        
        this.questionMap.put(GlobalQuestionGroupIDs.QUESTION_GROUP_ASK_FOR_AGE, 
                Arrays.asList(whatsYourAgeStrings));
        
        this.questionMap.put(GlobalQuestionGroupIDs.QUESTION_GROUP_ASK_FOR_JOB,
                            Arrays.asList(whatDoYouDoStrings));
    }
    
    @Override
    public int getRandomCategoryWithIgnoreList(List<Integer> ignoredCategories)
    {
        int[] ids = {GlobalQuestionGroupIDs.QUESTION_GROUP_ASK_FOR_AGE,
                     GlobalQuestionGroupIDs.QUESTION_GROUP_ASK_FOR_JOB,
                     GlobalQuestionGroupIDs.QUESTION_GROUP_ASK_FOR_NAME};
                     
        Random r = new Random();
        int actualRand = r.nextInt();
        if (actualRand < 0) 
        {
            actualRand = -actualRand;
            
        }
        int index = actualRand % ids.length;
        
        return ids[index];
    }

    @Override
    public Question randomQuestionFromCategory(int category) 
    {
       List<String> list = this.questionMap.get(category);
        if (list == null) 
        {
            return null;
        }
       
        Random rand = new Random();
        int actualRand = rand.nextInt();
        if (actualRand < 0) 
        {
            actualRand = -actualRand;
        }
        int r = actualRand % list.size();
        String question = list.get(r);
        return new Question(category, question, "alfa");
    }
    
}
