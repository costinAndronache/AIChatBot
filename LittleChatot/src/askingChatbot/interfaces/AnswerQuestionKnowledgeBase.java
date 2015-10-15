/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package askingChatbot.interfaces;

/**
 *
 * @author Costi
 */
public interface AnswerQuestionKnowledgeBase {
   
    public String answerForQuestion(String question);
    public void recordAnswerForQuestion(String answer, String question);
}
