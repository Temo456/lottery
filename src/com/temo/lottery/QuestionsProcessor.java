package com.temo.lottery;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class QuestionsProcessor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1669466062683797349L;
	private List<String> questionIds = new ArrayList<String>(50);
	private Map<String, String> mapQuestionTitle = new HashMap<String, String>();
	private Map<String, List<String>> mapQuestionAnswers = new LinkedHashMap<String, List<String>>();
	private static String answer = "12mLdDbDiUA1u/gtuqd7gUomD1mTeBeMYYHYM7sqHchdbntvtbbu65SPKdkKqZnrfW5I9QUvgvA=";

	public Map<String, Player> mapPlayers = new HashMap<String, Player>();
	public static Map<String, String> mapGifts = new HashMap<String, String>();
	public int BASE_COUNT = 3200;
	public int number2 = 5;
	public int number3 = 50;
	public int number4 = 300;

	private static QuestionsProcessor _instance = null;

	private QuestionsProcessor() {
		mapGifts.put("2", "中国移动充值卡100元");
		mapGifts.put("3", "中国移动充值卡30元");
		mapGifts.put("4", "中国移动充值卡10元");
	}

	private static SimpleDateFormat formatter = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	public static synchronized void saveStatus() {
		try {
			File f = new File(getClassPath("lottery.data"));
			if (f.exists()) {
				File newfile = new File(getClassPath("lottery.data."
						+ formatter.format(new Date())));
				FileUtils.copyFile(f, newfile);
			}
			FileOutputStream fos = new FileOutputStream(
					getClassPath("lottery.data"));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(QuestionsProcessor.getInstance());
			oos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static String getClassPath(String filename) {
		try {
			return URLDecoder.decode(new QuestionResource().getClass()
					.getClassLoader().getResource("").getPath(), "UTF-8")
					+ filename;
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public static synchronized void loadStatus() throws IOException {
		try {
			FileInputStream fis = new FileInputStream(
					getClassPath("lottery.data"));
			ObjectInputStream ois = new ObjectInputStream(fis);
			_instance = (QuestionsProcessor) ois.readObject();
			ois.close();
		} catch (Exception ex) {
			_instance = new QuestionsProcessor();
		}
		_instance.readQuestionsFromFile(getClassPath("p.txt"));
	}

	public static QuestionsProcessor getInstance() {
		if (null == _instance)
			try {
				loadStatus();
			} catch (IOException e) {
				Logger logger = Logger
						.getLogger(_instance.getClass().getName());
				logger.log(Level.SEVERE, "无法加载问题列表！！");
				e.printStackTrace();
			}
		return _instance;
	}

	public List<Q> randomThreeQuestions() {
		List<Q> lstRet = new ArrayList<Q>();
		Random r = new Random();
		while (lstRet.size() < 3) {
			int i = r.nextInt(questionIds.size()) + 1;
			if (!lstRet.contains(createQ(i + "")))
				lstRet.add(createQ(i + ""));
		}
		return lstRet;
	}

	private Q createQ(String string) {
		Q q = new Q();
		q.setId(string);
		q.setTitle(mapQuestionTitle.get(string));
		q.setAnswers(mapQuestionAnswers.get(string));
		return q;
	}

	public static boolean judgeAnswer(String questionId, String questionAnswer) {
		try {
			int i = Integer.parseInt(questionId);
			return questionAnswer.equals(DesUtil.decrypt(answer).charAt(i - 1)
					+ "");
		} catch (Exception e) {
			return false;
		}
	}

	public String getQuestionTitle(String questionId) {
		return mapQuestionTitle.get(questionId);
	}

	public List<String> getQuestionAnswers(String questionId) {
		return mapQuestionAnswers.get(questionId);
	}

	private void printQuestionsAndAnswers() {
		for (Iterator<String> iterator = questionIds.iterator(); iterator
				.hasNext();) {
			String key = (String) iterator.next();
			List<String> answers = mapQuestionAnswers.get(key);
			System.out.println("问题:" + mapQuestionTitle.get(key) + "答案："
					+ answer.charAt(Integer.parseInt(key) - 1));
			for (Iterator<String> iterAnswers = answers.iterator(); iterAnswers
					.hasNext();) {
				String string = (String) iterAnswers.next();
				System.out.println("\t" + string);
			}
		}
	}

	private void readQuestionsFromFile(String filename) throws IOException {
		File f = new File(filename);
		InputStream is = null;
		is = new FileInputStream(f);
		List<String> list = IOUtils.readLines(is);
		String lastTitle = "";
		mapQuestionAnswers.clear();
		questionIds.clear();
		mapQuestionTitle.clear();
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			string = string.trim();
			if (string.startsWith("A")) {
				// answers
				String answerA = string.substring(string.indexOf("A.") + 2,
						string.indexOf("B.")).trim();
				String answerB = string.substring(string.indexOf("B.") + 2,
						string.indexOf("C.")).trim();
				String answerC = string.substring(string.indexOf("C.") + 2,
						string.indexOf("D.")).trim();
				String answerD = string.substring(string.indexOf("D.") + 2)
						.trim();

				List<String> lstAnswers = new ArrayList<String>();
				lstAnswers.add(answerA);
				lstAnswers.add(answerB);
				lstAnswers.add(answerC);
				lstAnswers.add(answerD);

				mapQuestionAnswers.put(lastTitle, lstAnswers);
			} else {
				// question
				string = string.trim();
				if (!"".equals(string)) {
					String titleKey = string.substring(0, string.indexOf('.'));
					questionIds.add(titleKey);
					mapQuestionTitle.put(titleKey, string);
					lastTitle = titleKey;
				}
			}
		}
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		QuestionsProcessor.getInstance().readQuestionsFromFile(
				"G:/workspace/lottery/p.txt");

		QuestionsProcessor.getInstance().printQuestionsAndAnswers();

		System.out.println(judgeAnswer("1", "B"));
		System.out.println(judgeAnswer("A", "B"));
		System.out.println(judgeAnswer("1", "C"));
		System.out.println(judgeAnswer("1", "D"));
	}

}
