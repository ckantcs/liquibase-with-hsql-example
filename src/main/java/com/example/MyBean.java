package com.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyBean {

	private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MyBean(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @GetMapping("/allpersons")
    public Object findAll(){
    	return  jdbcTemplate.query("select * from person", new PersonMapper());
    }
    
    public class PersonMapper implements RowMapper<Person> {
    	   public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
    		  Person person = new Person();
    	      person.setId(rs.getInt("id"));
    	      person.setFirstName(rs.getString("first_name"));
    	      person.setLastName(rs.getString("last_name"));
    	      return person;
    	   }
    }
    public class Person {
    	
    	Integer id;
    	String firstName;
    	String  lastName;
    	public Person(Integer id, String firstName, String lastName) {
    		super();
    		this.id = id;
    		this.firstName = firstName;
    		this.lastName = lastName;
    	}
    	public Integer getId() {
    		return id;
    	}
    	public void setId(Integer id) {
    		this.id = id;
    	}
    	public String getFirstName() {
    		return firstName;
    	}
    	public void setFirstName(String firstName) {
    		this.firstName = firstName;
    	}
    	public String getLastName() {
    		return lastName;
    	}
    	public void setLastName(String lastName) {
    		this.lastName = lastName;
    	}
    	public Person() {
    		
    	}
    	
    	
    }
}
