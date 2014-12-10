package com.temo.lottery;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.temo.lottery.util.MessageDigestUtil;

@Path("/")
public class QuestionResource {
	private static final String ERROR = "error";
	@Context
	UriInfo uriInfo;
	@Context
	HttpServletRequest request;
	@Context
	HttpServletResponse response;

	private static String encrypt(String jsid, String uuid, String ip) {
		return MessageDigestUtil.MessageDigest(
				DesUtil.encrypt(uuid + ip + jsid), "MD5");
	}

	@Path("/u")
	@GET
	@Produces({ MediaType.TEXT_PLAIN })
	public String getUserId(@CookieParam("JSESSIONID") String jsessionid) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getRemoteAddr();
		}
		String userid = encrypt(jsessionid, UUID.randomUUID().toString(), ip);
		Player p = new Player();
		p.setUid(userid);
		p.setIp(ip);
		p.setQuestions(QuestionsProcessor.getInstance().randomThreeQuestions());
		QuestionsProcessor.getInstance().mapPlayers.put(userid, p);
		return userid;
	}

	@Path("/q")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)
	public Q getQuestion(@FormParam("uid") String uid) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (null == uid || "null".equals(uid))
			return new Q();
		Player p = QuestionsProcessor.getInstance().mapPlayers.get(uid);
		if (null == p)
			return new Q();
		return p.getCurQuestion();
	}

	@Path("/a/{uid}/{answer}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAnswer(@PathParam("uid") String uid,
			@PathParam("answer") String answer) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Player p = QuestionsProcessor.getInstance().mapPlayers.get(uid);
		if (null == p)
			return ERROR;

		Q q = p.getCurQuestion();
		if (null == q)
			return ERROR;
		if (QuestionsProcessor.judgeAnswer(q.getId(), answer)) {
			p.addQIndex();
			if (p.success())
				return "success";
			return "next";
		}
		QuestionsProcessor.getInstance().mapPlayers.remove(uid);
		return ERROR;
	}

	@Path("/i/{uid}")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String submitInfo(@PathParam("uid") String uid,
			@CookieParam("JSESSIONID") String jsessionid,
			@FormParam("uid") String uid_form,
			@FormParam("username") String username,
			@FormParam("email") String email, @FormParam("tele") String tele,
			@FormParam("corp") String corp, @FormParam("suggest") String suggest) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (null == uid || "null".equals(uid) || !uid.equals(uid_form))
			return ERROR;

		Player p = QuestionsProcessor.getInstance().mapPlayers.get(uid);
		if (null == p || !p.success())
			return ERROR;

		Set<String> keys = QuestionsProcessor.getInstance().mapPlayers.keySet();
		boolean flag = false;
		for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			Player pTemp = QuestionsProcessor.getInstance().mapPlayers
					.get(string);
			if (null != pTemp.getTele() && pTemp.getTele().equals(tele) && (null != pTemp.getLotteryid()) &&  !"".equals(pTemp)) {
				flag = true;
				break;
			}
		}
		if (flag)
			return "checked";

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getRemoteAddr();
		}
		p.setCorp(corp);
		p.setEmail(email);
		p.setTele(tele);
		p.setSuggestions(suggest);
		p.setName(username);
		p.setIp(ip);
		QuestionsProcessor.getInstance().mapPlayers.put(uid, p);
		QuestionsProcessor.saveStatus();
		return "success";
	}

	@Path("/d/{uid}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String drawLottery(@PathParam("uid") String uid,
			@CookieParam("JSESSIONID") String jsessionid) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (null == uid || "null".equals(uid))
			return ERROR;

		Player p = QuestionsProcessor.getInstance().mapPlayers.get(uid);
		if (null == p || !p.success())
			return ERROR;

		int NUMBER2_COUNT = QuestionsProcessor.getInstance().number2;
		int NUMBER3_COUNT = QuestionsProcessor.getInstance().number3;
		int NUMBER4_COUNT = QuestionsProcessor.getInstance().number4;

		String lotteryid = "0";
		Random r = new Random();
		int i = r.nextInt(QuestionsProcessor.getInstance().BASE_COUNT);
		if (i <= NUMBER2_COUNT) {
			lotteryid = "2";
			QuestionsProcessor.getInstance().number2--;
		} else if (i > NUMBER2_COUNT && i <= NUMBER2_COUNT + NUMBER3_COUNT) {
			lotteryid = "3";
			QuestionsProcessor.getInstance().number3--;
		} else if (i < NUMBER2_COUNT + NUMBER3_COUNT + NUMBER4_COUNT) {
			lotteryid = "4";
			QuestionsProcessor.getInstance().number4--;
		} else
			return "ERROR";
		p.setLotteryid(lotteryid);
		QuestionsProcessor.getInstance().BASE_COUNT--;
		QuestionsProcessor.getInstance().mapPlayers.put(uid, p);
		QuestionsProcessor.saveStatus();
		return "goal";
	}

	@Path("/l/{uid}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getLottery(@PathParam("uid") String uid,
			@CookieParam("JSESSIONID") String jsessionid) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (null == uid || "null".equals(uid))
			return ERROR;
		Player p = QuestionsProcessor.getInstance().mapPlayers.get(uid);
		if (null == p)// || !p.success())
			return ERROR;

		return QuestionsProcessor.mapGifts
				.get(String.valueOf(p.getLotteryid()));
	}

	@Path("/admin/list")
	@GET
	@Produces("application/json;charset=UTF-8")
	public String listLotterys() {
		response.setHeader("Access-Control-Allow-Origin", "*");
		StringBuffer ret = new StringBuffer();
		Set<String> keys = QuestionsProcessor.getInstance().mapPlayers.keySet();
		ret.append("ID").append(",").append("奖项").append(",").append("姓名")
				.append(",").append("E-mail").append(",").append("电话")
				.append(",").append("工作单位").append(",").append("意见建议")
				.append(",").append("IP").append(",").append("是否答题完毕")
				.append("\n");
		for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			Player p = QuestionsProcessor.getInstance().mapPlayers.get(string);
			ret.append(p.getString());
		}
		return ret.toString();
	}
}
