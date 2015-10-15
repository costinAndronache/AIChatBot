/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package askingChatbot.managers.simpleManagers;
import askingChatbot.interfaces.*;
import java.io.*;
import java.util.*;
/**
 *
 * @author Costi
 */
public class SimpleAnswerKB implements AnswerQuestionKnowledgeBase {
    
    private List<String> questionList, answersList;
    
    public SimpleAnswerKB()
    {
        this.questionList = new ArrayList<>();
        this.answersList = new ArrayList<>();
    }
    
    public void readFromBuffer(BufferedReader reader) throws Exception
    {
        String numOfItemsAsString = reader.readLine();
        int numOfItems = Integer.parseInt(numOfItemsAsString);
        
        for(int i=1; i<=numOfItems; i++)
        {
            String aQuestion = reader.readLine();
            String anAnswer = reader.readLine();
            
            this.questionList.add(aQuestion);
            this.answersList.add(anAnswer);
        }
    }
    
    public  void writeToBuffer(BufferedWriter writer) throws Exception
    {
        int numOfItems = this.questionList.size();
        writer.write("" + numOfItems + "\n");
        
        for(int i=0; i<numOfItems; i++)
        {
            writer.write(this.questionList.get(i) + ""
                    + "\n");
            writer.write(this.answersList.get(i) + "\n");
        }
    }
    
    @Override
    public String answerForQuestion(String question) 
    {
        for(int i=0; i<this.questionList.size(); i++)
        {
            String myQ = this.questionList.get(i);
            int distance = this.LevenshteinDistance(myQ.toLowerCase(), question.toLowerCase());
            if (distance <= 2) 
            {
                return this.answersList.get(i);
            }
        }
        
        return null;
    }

    @Override
    public void recordAnswerForQuestion(String answer, String question) 
    {
        this.answersList.add(answer);
        this.questionList.add(question);
    }
    
    
    private int LevenshteinDistance (CharSequence lhs, CharSequence rhs) {                          
    int len0 = lhs.length() + 1;                                                     
    int len1 = rhs.length() + 1;                                                     
                                                                                    
    // the array of distances                                                       
    int[] cost = new int[len0];                                                     
    int[] newcost = new int[len0];                                                  
                                                                                    
    // initial cost of skipping prefix in String s0                                 
    for (int i = 0; i < len0; i++) cost[i] = i;                                     
                                                                                    
    // dynamically computing the array of distances                                  
                                                                                    
    // transformation cost for each letter in s1                                    
    for (int j = 1; j < len1; j++) {                                                
        // initial cost of skipping prefix in String s1                             
        newcost[0] = j;                                                             
                                                                                    
        // transformation cost for each letter in s0                                
        for(int i = 1; i < len0; i++) {                                             
            // matching current letters in both strings                             
            int match = (lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1;             
                                                                                    
            // computing cost for each transformation                               
            int cost_replace = cost[i - 1] + match;                                 
            int cost_insert  = cost[i] + 1;                                         
            int cost_delete  = newcost[i - 1] + 1;                                  
                                                                                    
            // keep minimum cost                                                    
            newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
        }                                                                           
                                                                                    
        // swap cost/newcost arrays                                                 
        int[] swap = cost; cost = newcost; newcost = swap;                          
    }                                                                               
                                                                                    
    // the distance is the cost for transforming all letters in both strings        
    return cost[len0 - 1];                                                          
}
}
