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
import java.io.IOException;
import java.sql.SQLException;

import dataBase.DataBase;

/**
 *
 * @author Costi
 */
public class Main 
{
    public static void main(String[] args) 
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
        
        
        
        ChatManager cm;
		try {
			cm = new ChatManager(qm, pm);
			cm.startChat();
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
}
