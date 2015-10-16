/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.*;

/**
 *
 * @author Costi
 */
public class QuestionProposion 
{
    private int maxAge;
    private List<String> jobList;
    private List<Integer> idList;
    
    public QuestionProposion(int maxAge, List<String> jobList, List<Integer> idList)
    {
        this.maxAge = maxAge;
        this.jobList = jobList;
        this.idList = idList;
       
        //System.out.println("created with jobList " + jobList + ", idList " + idList);
    }
    
    public int getMaxAge()
    {
        return  this.maxAge;
    }
    public List<String> getJobList()
    {
        return this.jobList;
    }
    
    public List<Integer> getIdList()
    {
        return this.idList;
    }
    
    public List<Integer> proposeQuestionIdsForPerson(Person p)
    {
        List<Integer> list = new ArrayList<>();
        
        if (p.getAge() > this.maxAge) 
        {
            return list;
        }
        
        if (!this.doesPersonHaveAJobInMyList(p)) 
        {
            return list;
        }
        
        for(int groupId : this.idList)
        {
            if(!p.hasAnsweredQuestionGroup(groupId))
            {
                list.add(groupId);
                //System.out.println("added " + groupId + "\n");
            }
        }
        
        return list;
    }
    
    
    private boolean doesPersonHaveAJobInMyList(Person p)
    {
        return this.jobList.contains(p.getJob());
    }
}
