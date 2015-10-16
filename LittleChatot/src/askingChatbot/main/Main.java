/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package askingChatbot.main;
import askingChatbot.managers.*;
import askingChatbot.managers.simpleManagers.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import dataBase.DataBase;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Costi
 */
public class Main 
{
    
    
    public static void startTheSimpleChat() throws Exception
    {
      ObjectInputStream pis = inputStreamForFile("PersonsAll");
      SimpleQuestionManager sqm = new SimpleQuestionManager();
      sqm.setupFromJSON("Questions.json");
      
      SimplePersonManager spm = new SimplePersonManager();
      spm.setupFromJSON("SPM.json");
      
      SimpleQuestionProposer sqp = new SimpleQuestionProposer(sqm);
      sqp.setupFromJSONPath("SQP.json");
      
      SimpleAnswerKB sakb = new SimpleAnswerKB();
      sakb.setupFromJSON("QAKB.json");
      
      ChatManager cm = new ChatManager(sqm, spm, sqp, sakb);
      cm.startChat();
      
      spm.writeToJSON("SPM.json");
      sqp.writeToJSON("SQP.json");
      sakb.writeToJSON("QAKB.json");
    }
    
    
    public static SimplePersonManager getPersonManager() throws Exception
    {
        return new SimplePersonManager();
    }
    
    public static SimpleAnswerKB getAKB() throws Exception
    {
        SimpleAnswerKB sakb = new SimpleAnswerKB();
        BufferedReader br = readerForFile("QA.txt");
        sakb.readFromBuffer(br);
        return sakb;
    }
    
    public static ObjectOutputStream outputStreamForFile(String filename) throws Exception
    {
        DataOutputStream dao = new DataOutputStream(new FileOutputStream(new File(filename)));
        ObjectOutputStream oos = new ObjectOutputStream(dao);
        return oos;
    }
    
    public static ObjectInputStream inputStreamForFile(String filename) throws Exception
    {
        FileInputStream fis = new FileInputStream(new File(filename));
        ObjectInputStream ois = new ObjectInputStream(fis);
        return ois;
    }
    
    public static BufferedReader readerForFile(String fileName) throws Exception
    {
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        
        return br;
    }
    
    public static BufferedWriter writerForFile(String filename) throws Exception
    {
        File file = new File(filename);
        FileWriter fr = new FileWriter(file);
        BufferedWriter br = new BufferedWriter(fr);
        return br;
    }
    
    public static void startFullChat() throws Exception
    {
        DataBase db = DataBase.getInstance();
    	try {
    		db.connectDataBase();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	/*
        FileReader persReader = new FileReader(new File("persoane.txt"));
        BufferedReader persBR = new BufferedReader(persReader);
        */
    	
        PersonManager pm = null;
		try {
			pm = new PersonManager();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /*
        FileReader qsReader = new FileReader(new File("persoane.txt"));
        BufferedReader qsBR = new BufferedReader(persReader);
        */
        QuestionManager qm = null;
		try {
			qm = new QuestionManager();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        
		try {

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		try {
    		db.closeDataBaseConnection();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    
    public static void main(String[] args) throws Exception
    {
        startTheSimpleChat();
    }
}