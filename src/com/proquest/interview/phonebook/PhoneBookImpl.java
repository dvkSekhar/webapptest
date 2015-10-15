package com.proquest.interview.phonebook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proquest.interview.util.DatabaseUtil;

public class PhoneBookImpl implements PhoneBook {
	private List<Person> people = new ArrayList<Person>();
	
	@Override
	public void addPerson(Person newPerson) {
		//TODO: write this method
		
		people.add(newPerson);
	}
	
	@Override
	public Person findPerson(String firstName, String lastName){
		//TODO: write this method
		
		Person person = null;
		try {
			Connection con = DatabaseUtil.getConnection();
			person = new Person();
			
			StringBuilder sql = new StringBuilder("SELECT * FROM PHONEBOOK WHERE NAME = '" + firstName+" "+lastName+"'");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while(rs.next()){
				person.setName(rs.getString(1));
				person.setAddress(rs.getString(2));
				person.setPhoneNumber(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return person;
		
	}
	
	
	
	public static void main(String[] args) {
		DatabaseUtil.initDB();  //You should not remove this line, it creates the in-memory database
		Person personJhn = new Person();
		personJhn.setName("John Smith");
		personJhn.setAddress("1234 Sand Hill Dr, Royal Oak, MI");
		personJhn.setPhoneNumber("(248) 123-4567");
		
		PhoneBookImpl phBk = new PhoneBookImpl();
		phBk.addPerson(personJhn);
		
		Person personCyn = new Person();
		personCyn.setName("Cynthia Smith");
		personCyn.setAddress("875 Main St, Ann Arbor, MI");
		personCyn.setPhoneNumber("(824) 128-8758");
		phBk.addPerson(personCyn);
		
	    try {
			phBk.insertPhoneBook(phBk.getPeople());
			
			Person person = phBk.findPerson("Cynthia", "Smith");
			System.out.println("----- Print Person Details From DB -----");
			System.out.println("Name :"+person.getName());
			System.out.println("Phone Number :"+person.getPhoneNumber());
			System.out.println("Address :"+person.getAddress());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		/* TODO: create person objects and put them in the PhoneBook and database
		 * John Smith, (248) 123-4567, 1234 Sand Hill Dr, Royal Oak, MI
		 * Cynthia Smith, (824) 128-8758, 875 Main St, Ann Arbor, MI
		*/ 
		// TODO: print the phone book out to System.out
		// TODO: find Cynthia Smith and print out just her entry
		// TODO: insert the new person objects into the database
	}

	/*
	 * This method prints the Phone Book to the console and also writes to the Database.
	 */
	public void insertPhoneBook(List<Person> prsnPhnList) throws SQLException, ClassNotFoundException {
			Connection con = DatabaseUtil.getConnection();
			Statement stmt = con.createStatement();
			StringBuilder sql = null;
			
			System.out.println("---Phone Book Contents---- ");
			
			for(Person per : prsnPhnList){
				System.out.println(per);
				sql = new StringBuilder("INSERT INTO PHONEBOOK (NAME, PHONENUMBER, ADDRESS) VALUES ( ");
				sql = sql.append("'"+per.getName()+"',").append("'"+per.getPhoneNumber()+"',").append("'"+per.getAddress()+"' )");
				DatabaseUtil.insertDB(stmt, sql.toString());
				System.out.println("---Person Record Inserted---");
			}
		
	}

	public List<Person> getPeople() {
		return people;
	}

}
