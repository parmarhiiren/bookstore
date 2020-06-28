package com.example.bookstore.input;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "authors")
public class AuthorsCriteria {

    private List<String> author;

    public List<String> getAuthor() {
        return author;
    }

    @XmlElement(name = "author")
    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public void add(String _author) {
        if (this.author == null) {
            this.author = new ArrayList<>();
        }
        author.add(_author);
    }

	@Override
	public String toString() {
		return "AuthorsCriteria [author=" + author + "]";
	}

}
