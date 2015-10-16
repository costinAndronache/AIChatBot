/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package askingChatbot.managers.simpleManagers;

import askingChatbot.interfaces.*;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import models.*;

import org.json.*;

/**
 *
 * @author Costi
 */
public class SimpleQuestionManager implements QuestionProvider, Serializable{

    private Map<Integer, List<String>> questionMap;
    
    public SimpleQuestionManager()
    {
        
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
        
        List<Integer> ids = new ArrayList<>();
        for(int i : questionMap.keySet())
        {
            if (i == GlobalQuestionGroupIDs.QUESTION_GROUP_ASK_FOR_AGE || 
                i == GlobalQuestionGroupIDs.QUESTION_GROUP_ASK_FOR_JOB ||
                i == GlobalQuestionGroupIDs.QUESTION_GROUP_ASK_FOR_NAME)
            {
                
            }
            else
                ids.add(i);
        }

                     
        Random r = new Random();
        int actualRand = r.nextInt();
        if (actualRand < 0) 
        {
            actualRand = -actualRand;
            
        }
        int index = actualRand % ids.size();
        
        return ids.get(index);
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
    
    public void setupFromJSON(String jsonName) throws Exception
    {
        questionMap = new HashMap<>();
        byte[] jsonStringBytes = Files.readAllBytes(Paths.get(jsonName));
        String jsonString = new String(jsonStringBytes,Charset.defaultCharset());
        JSONArray array = new JSONArray(jsonString);
        
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = (JSONObject)array.getJSONObject(i);
            int categoryID = object.getInt("categoryID");
            JSONArray questionsList = object.getJSONArray("questionsList");
            List<String> list = new ArrayList<>();
            for(int j=0; j<questionsList.length(); j++)
            {
                String aString = questionsList.getString(j);
                list.add(aString);
            }
            
            questionMap.put(categoryID, list);
        }
    }
}
