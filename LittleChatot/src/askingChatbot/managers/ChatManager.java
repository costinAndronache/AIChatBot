/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package askingChatbot.managers;
import askingChatbot.interfaces.*;
import com.sun.jmx.snmp.daemon.SnmpInformRequest;
import com.sun.org.apache.bcel.internal.generic.StackInstruction;
import models.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author Costi
 */
public class ChatManager {
    
    QuestionProposer _questionProposer;
    PersonProvider pp;
    QuestionProvider qp;
    Scanner sc;
    
    private boolean  _chatDone;
    public ChatManager(QuestionProvider qp, PersonProvider pp, QuestionProposer questionProposer) 
            throws Exception
    {
        this.pp = pp;
        this.qp = qp;        
        _questionProposer = questionProposer;
    }
    public ChatManager(QuestionManager qm, PersonManager pm) throws Exception
    {
    	 this.pp = pm;
         this.qp = qm; 
    }
    
    public void startChat()
    {
       sc = new Scanner(System.in);
       String nameQuestionString = this.qp.randomQuestionFromCategory(
               GlobalQuestionGroupIDs.QUESTION_GROUP_ASK_FOR_NAME).getQuestion();
       System.out.println(nameQuestionString);
       String pName = sc.nextLine();
       Person p  = this.pp.getPersonWithName(pName);
           
       if(p == null)
        {
           p = this.askAndCreateAPersonWithName(pName);
        }
           
        
       _chatDone = false;
       while(!_chatDone)
       {
           System.out.println(p.getName() + " would you like to ask me something? (yes/no)");
           String userCommand = this.sc.nextLine();
           if (userCommand.equalsIgnoreCase("no")) 
           {
               this.askAQuestionAndAcceptReply(p);
           }
           else
           {
               this.waitForQuestionAndReply(p);
           }
       }
       
        System.out.println("It was nice talking to you");
    }
    
    public Person askAndCreateAPersonWithName(String name)
    {
        String ageQuestion = this.qp.randomQuestionFromCategory(
                GlobalQuestionGroupIDs.QUESTION_GROUP_ASK_FOR_AGE).getQuestion();
        System.out.println(ageQuestion);
        
        int age = sc.nextInt();
        String jobQuestion = this.qp.randomQuestionFromCategory(
                GlobalQuestionGroupIDs.QUESTION_GROUP_ASK_FOR_JOB).getQuestion();
        System.out.println(jobQuestion);
        String job = sc.nextLine();
        
        List<Integer> newList = new ArrayList<>();
        Person p = new Person(name, newList,age,job);
        pp.addPerson(p);
        
        return p;
    }
    
    
    
    private void askAQuestionAndAcceptReply(Person person)
    {
        int aQuestionGroupId = this._questionProposer.proposeQuestionGroupIDForPerson(person);
        if (aQuestionGroupId < 0) 
        {
            this.presentDoNotKnowMessage();
            return;
        }
        
        Question aQuestion = this.qp.randomQuestionFromCategory(aQuestionGroupId);
        if (aQuestion == null) 
        {
            this.presentDoNotKnowMessage();
            return;
        }
        
        System.out.println(aQuestion.getQuestion());
        String answer = this.sc.nextLine();
    }
    
    private void waitForQuestionAndReply(Person person)
    {
        System.out.println(person.getName() + " what would you like to know?:\n");
        String userQuestion = this.sc.nextLine();
        
        if (userQuestion.equalsIgnoreCase("bye bye")) 
        {
            _chatDone = true;
            return;
        }
        
        System.out.println("I currently cannot answer that n__n\"");
    }
    
    private void presentDoNotKnowMessage()
    {
        System.out.println("I do not know what to ask you anymore n__n\"");
    }
}
