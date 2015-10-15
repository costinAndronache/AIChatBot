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

/**
 *
 * @author Costi
 */
public class Main 
{
    
    
    public static void startTheSimpleChat() throws Exception
    {

      SimpleQuestionManager sqm = new SimpleQuestionManager();
      SimplePersonManager spm = new SimplePersonManager();
      SimpleQuestionProposer sqp = new SimpleQuestionProposer(sqm);
      SimpleAnswerKB sakb = getAKB();
 
      ChatManager cm = new ChatManager(sqm, spm, sqp, sakb);
      cm.startChat();
    }
    
    public static SimpleAnswerKB getAKB() throws Exception
    {
        SimpleAnswerKB sakb = new SimpleAnswerKB();
        File file = new File("QA.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        sakb.readFromBuffer(br);
        return sakb;
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