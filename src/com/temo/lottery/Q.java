package com.temo.lottery;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Q implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7660990721275215109L;
	private String index;
	private String id;
	private String title;
	private List<String> answers;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof Q))
			return false;
		Q a = (Q) obj;
		if (!a.id.equals(this.id))
			return false;
		return true;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}
}
