package client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import util.SendEmail;
@WebServlet("/email")
public class Client extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email").toString();
		try {
			//To 审批人 Content
			StringBuffer emailMsg = new StringBuffer();
			emailMsg.append("<h3>审批通知:</h3>");
			emailMsg.append("<p>借款人：" + "模拟信息" + "</p>");
			emailMsg.append("<p>借款人证件号：" + "模拟信息" + "</p>");
			emailMsg.append("<p>所属公司：" + "模拟信息"+ "</p>");
			emailMsg.append("<p>借款金额：" + "模拟信息" + "</p>");
			emailMsg.append("<p>借款期数：" + "模拟信息" + "</p>");
			emailMsg.append("<p>借款人开户行：" + "模拟信息" +"</p>");
			emailMsg.append("<p>工资卡号：" + "模拟信息" + "</p>");
			emailMsg.append("<p>请尽快审批，谢谢。</p>");
			emailMsg.append("<strong>钦钦贷系统</strong><br>");
			emailMsg.append("<strong>"+ "模拟信息" + "</strong><br>");
			String[] copyPerson = null;
			//To HR Content
			StringBuffer emailMsgHR = new StringBuffer();
			emailMsgHR.append("<p>Dear HR,</p>");
			emailMsgHR.append("<p></p>");
			emailMsgHR.append("<p>"+ "模拟信息" + "的员工 " + "模拟信息" + " 于");
			emailMsgHR.append("模拟信息" + "申请贷款"+ "模拟信息" + "元。</p>");
			emailMsgHR.append("<p>为准确评估该员工的借款资质，烦请帮忙提供以下信息:</p>");
			emailMsgHR.append("<br/>");
			emailMsgHR.append("<p>"+"证件类型:"+"模拟信息"+"</p>");
			emailMsgHR.append("<p>"+"证件号码:"+"模拟信息"+"</p>");
			emailMsgHR.append("<p>"+"姓名:"+"模拟信息"+"</p>");
			emailMsgHR.append("<p>"+"部门:"+"模拟信息"+"</p>");
			emailMsgHR.append("<p>工作评价:<strong>回复时请填写</strong></p>");
			emailMsgHR.append("<p>近期是否有休假计划:<strong>回复时请填写</strong></p>");
			emailMsgHR.append("<p><strong>（如有休假计划请说明休假的类型及时间）</strong></p>");
			emailMsgHR.append("<p>近期是否有离职意向:<strong>回复时请填写</strong></p>");
			emailMsgHR.append("<p>评价人签名:<strong>回复时请填写</strong></p>");
			emailMsgHR.append("<strong>钦钦贷系统</strong><br>");
			emailMsgHR.append("<strong>"+ "模拟信息" + "</strong><br>");
			String[] copyPersonHR = null;
			SendEmail.sendEmailHR("cl.ygd@clpsglobal.com",
					email,"Clps2017","clpsglobal.com",copyPersonHR,emailMsgHR.toString(),"QQD.A201802070429000833");

			//发邮件给审批人
			SendEmail.sendEmail("cl.ygd@clpsglobal.com",
					email,"Clps2017","clpsglobal.com",copyPerson,emailMsg.toString(),"","");
			 PrintWriter out = response.getWriter();
			 out.println(
			            "<html>\n" +
			            "<head><title>" + "</title></head>\n" +
			            "<body bgcolor=\"#f0f0f0\">\n" +
			            "<h1 align=\"center\">" + "Success" + "</h1>\n" +
			            "</body></html>");
			//success(response,null,"用户Cino信息");//返回借据号
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			PrintWriter out = response.getWriter();
			out.println(
		            "<html>\n" +
		            "<head><title>" + "</title></head>\n" +
		            "<body bgcolor=\"#f0f0f0\">\n" +
		            "<h1 align=\"center\">" + "Abnormal email" + "</h1>\n" +
		            "</body></html>");
			e.printStackTrace();
		}
	}

	/*protected void success(HttpServletResponse response,String message, Object data) {
		ResultVO result = new ResultVO(ResultVO.SUCCESS);
		result.setMessage(message);
		result.setData(data);
		renderJson(response, JSON.toJSONString(result));
	}*/
}
