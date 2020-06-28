package com.example.bookstore.input;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "author")
public class AuthorCriteria {

    private String name;

    public AuthorCriteria() {
    }

    public AuthorCriteria(String name) {
        this.name = name;
    }

    @XmlElement(name = "author")
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

	@Override
	public String toString() {
		return "AuthorCriteria [name=" + name + "]";
	}



}
