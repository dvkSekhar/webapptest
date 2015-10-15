package com.proquest.interview.phonebook;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

public class PhoneBookImplTest {
	@Test
	public void shouldAddFindPerson() throws SQLException, ClassNotFoundException {
	
		PhoneBook phBk = new PhoneBookImpl();
		Person person = phBk.findPerson("John", "Smith");
		Assert.assertEquals("John Smith", person.getName());
		Assert.assertEquals("1234 Sand Hill Dr, Royal Oak, MI", person.getAddress());
		Assert.assertEquals("(248) 123-4567", person.getPhoneNumber());
	}
	
	@Test
	public void insertPerson() throws SQLException, ClassNotFoundException {
	
		Person personJhn = new Person();
		personJhn.setName("Sekhar DVK");
		personJhn.setAddress("111 Dr, Royal Oak, MI");
		personJhn.setPhoneNumber("(248) 123-4567");
		
		PhoneBookImpl phBk = new PhoneBookImpl();
		phBk.addPerson(personJhn);
		
		phBk.insertPhoneBook(phBk.getPeople());
	}
	
}
