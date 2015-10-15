/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package askingChatbot.managers.simpleManagers;
import askingChatbot.interfaces.*;
import models.Person;
/**
 *
 * @author Costi
 */
public class SimpleQuestionProposer implements QuestionProposer
{
    private QuestionProvider _qp;
    public SimpleQuestionProposer(QuestionProvider qp)
    {
        _qp = qp;
    }
    @Override
    public int proposeQuestionGroupIDForPerson(Person p) 
    {
        if (_qp != null) 
        {
            return _qp.getRandomCategoryWithIgnoreList(null);
        }
        
        return -1;
    }
 
    
}
