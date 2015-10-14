/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package askingChatbot.main;
import askingChatbot.managers.PersonManager;
import askingChatbot.managers.ChatManager;
import askingChatbot.managers.QuestionManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLException;

import dataBase.DataBase;

/**
 *
 * @author Costi
 */
public class Main 
{
    public static void main(String[] args) throws Exception
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
        
        PersonManager pm = new PersonManager(persBR);
        
        FileReader qsReader = new FileReader(new File("persoane.txt"));
        BufferedReader qsBR = new BufferedReader(persReader);
        
        QuestionManager qm = new QuestionManager(qsBR);
        
        */
        
        ChatManager cm = new ChatManager(qm, pm);
        cm.startChat();
    }
}
