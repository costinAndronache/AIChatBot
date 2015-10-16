/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package askingChatbot.managers.simpleManagers;
import askingChatbot.interfaces.*;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import models.*;
import org.json.*;
import java.util.*;

/**
 *
 * @author Costi
 */
public class SimpleQuestionProposer implements QuestionProposer
{
    private QuestionProvider _qp;
    private List<QuestionProposion> questionProposions;
    
    public SimpleQuestionProposer(QuestionProvider qp)
    {
        this.questionProposions = new ArrayList<>();
    }
    @Override
    public int proposeQuestionGroupIDForPerson(Person p) 
    {
        List<Integer> idList = new ArrayList<>();
        
        for(QuestionProposion prop : this.questionProposions)
        {
            List<Integer> proposedIds = prop.proposeQuestionIdsForPerson(p);
            idList.addAll(proposedIds);
            //System.out.println("proposed questions " + proposedIds + "\n");
        }
        
        if (idList.size() == 0) 
        {
            return -1;
        }
        
        if (idList.size() == 1) 
        {
            return idList.get(0);
        }
        
        Random rand = new Random();
        int aRand = rand.nextInt();
        if (aRand < 0) 
        {
            aRand = -aRand;
        }
        
        return aRand % idList.size();
    }
 
    public void setupFromJSONPath(String jsonPath) throws Exception
    {
        this.questionProposions = new ArrayList<>();
        byte[] bytes = Files.readAllBytes(Paths.get(jsonPath));
        String jsonString = new String(bytes);
        
        JSONArray array = new JSONArray(jsonString);
        
        for(int i=0; i<array.length(); i++)
        {
            JSONObject obj = array.getJSONObject(i);
            QuestionProposion prop = this.createProposionFromJsonObject(obj);
            this.questionProposions.add(prop);
        }
    }
    
    public void writeToJSON(String jsonPath) throws Exception
    {
        JSONArray array = new JSONArray();
        for(QuestionProposion prop : this.questionProposions)
        {
            JSONObject obj = this.proposionToObject(prop);
            array.put(obj);
        }
        
        StringWriter writer = new StringWriter();
        array.write(writer);
        FileWriter fw = new FileWriter(new File(jsonPath));
        fw.write(writer.toString());
        fw.flush();
    }
    
    private QuestionProposion createProposionFromJsonObject(JSONObject object)
    {
        int maxAge = 0;
        List<String> jobList = new ArrayList<>();
        List<Integer> idList = new ArrayList<>();
        
        maxAge = object.getInt("maxAge");
        JSONArray jobarray = object.getJSONArray("jobs");
        JSONArray idarray  = object.getJSONArray("idList");
        
        for(int i=0; i<jobarray.length(); i++)
        {
            String s = jobarray.getString(i);
            jobList.add(s);
        }
        
        for(int i=0; i<idarray.length(); i++)
        {
            int id = idarray.getInt(i);
            idList.add(id);
        }
        
        return new QuestionProposion(maxAge, jobList, idList);
    }
    
    private JSONObject proposionToObject(QuestionProposion proposion)
    {
        JSONArray jobArray = new JSONArray();
        JSONArray idArray = new JSONArray();
        
        List<String> jobList = proposion.getJobList();
        List<Integer> idList = proposion.getIdList();
        
        for(String s : jobList)
        {
            jobArray.put(s);
        }
        
        for(int id : idList)
        {
            idArray.put(id);
        }
        
        JSONObject object = new JSONObject();
        object.put("maxAge", proposion.getMaxAge());
        object.put("jobs", jobArray);
        object.put("idList", idList);
        return object;
    }
}
