/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import models.Question;

/**
 *
 * @author Costi
 */
public interface QuestionProvider {

    int getRandomCategoryWithIgnoreList(List<Integer> ignoredCategories);

    Question randomQuestionFromCategory(int category);
    
}
