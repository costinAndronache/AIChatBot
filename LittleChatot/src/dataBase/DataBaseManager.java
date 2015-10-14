package dataBase;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Person;

public class DataBaseManager {
	DataBase db = DataBase.getInstance();
	
	public boolean personCheck(String name) throws IOException
    {
		
		try {
			ResultSet rs = db.viewPersoane();
			
            String[] valori = new String[11];
            while (rs.next()) {
            	if (name.equals(rs.getString(2).trim())) return true;
            	
            	//System.out.println("Dificultate: " + valori[n-1]);
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return false;
    }
	
	public Person getPerson(String name) throws IOException
    {
		Person p = null;
		try {
			ResultSet rs = db.viewPersoane();
			ResultSet rsQA = db.viewQuestionsAsked();
			
			List<Integer> answeredQuestions = new ArrayList<>();
			
			while (rs.next()) {
	        	if (name.equals(rsQA.getString(1).trim()))
	        		answeredQuestions.add(rsQA.getInt(2));
	        }
				
	        while (rs.next()) {
	        	if (name.equals(rs.getString(1).trim()))
	        	{
	        		p = new Person(name,answeredQuestions,rs.getInt(3),rs.getString(4));
	        		return p;
	        	}

	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
    }
	
}
