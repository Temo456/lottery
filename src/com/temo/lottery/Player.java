package com.temo.lottery;

import java.io.Serializable;
import java.util.List;

public class Player implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5598596577091628183L;
	private String uid;
	private int currentQIndex = 0;
	private List<Q> questions;
	private String name;
	private String email;
	private String tele;
	private String corp;
	private String suggestions;
	private String lotteryid;
	private String ip;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getCurrentQIndex() {
		return currentQIndex;
	}

	public void setCurrentQIndex(int currentQIndex) {
		this.currentQIndex = currentQIndex;
	}

	public void addQIndex() {
		this.currentQIndex++;
	}

	public boolean success() {
		if (null == questions)
			return false;
		return this.currentQIndex == questions.size();
	}

	public Q getCurQuestion() {

		if (null == questions)
			return null;

		if (currentQIndex > questions.size() - 1)
			return null;

		Q q = questions.get(currentQIndex);
		q.setIndex("Œ Ã‚" + (currentQIndex + 1));
		//q.setAnswers(QuestionsProcessor.getInstance().getQuestionAnswers(q.getId()));
		//q.setTitle(QuestionsProcessor.getInstance().getQuestionTitle(q.getId()));
		return q;
	}

	public List<Q> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Q> questions) {
		this.questions = questions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public String getCorp() {
		return corp;
	}

	public void setCorp(String corp) {
		this.corp = corp;
	}

	public String getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(String suggestions) {
		this.suggestions = suggestions;
	}

	public String getLotteryid() {
		return lotteryid;
	}

	public void setLotteryid(String lotteryid) {
		this.lotteryid = lotteryid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getString() {
		StringBuffer ret = new StringBuffer();
		ret.append(getUid()).append(",").append(getLotteryid()).append(",")
				.append(getName()).append(",").append(getEmail()).append(",")
				.append(getTele()).append(",").append(getCorp()).append(",")
				.append(getSuggestions()).append(",").append(getIp())
				.append(",").append(success()).append("\n");
		return ret.toString();
	}
}
