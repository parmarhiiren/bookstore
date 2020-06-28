package com.example.bookstore.input;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;


public class Title {

	private String lang;
	private String value;

	public Title() {
		
	}
	public Title(String lang, String value) {
		super();
		this.lang = lang;
		this.value = value;
	}
	
    public String getValue() {
        return value;
    }
    
    @XmlValue
    public void setValue(String value) {
        this.value = value;
    }
    
	public String getLang() {
		return lang;
	}

	@XmlAttribute(name = "lang")
	public void setLang(String lang) {
		this.lang = lang;
	}

	@Override
	public String toString() {
		return "Title [lang=" + lang + ", value=" + value + "]";
	}
	
	
}
