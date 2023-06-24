package view;

import digitalEnvelope.Verify;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VerifyEnvelopeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	        String envelopeFName = "/" + request.getParameter("envelopeFName");
	        String privateFName = "/" + request.getParameter("privateFName");
	
	        ServletContext context = getServletContext();
	
	        
	        String envelopePath = context.getRealPath(envelopeFName);
	        String privatePath = context.getRealPath(privateFName);
	        
	        boolean verificationResult = Verify.verifyEnvelope(envelopePath, privatePath);
	        
	        String message = "";
	        if (verificationResult) {
	        	message = "검증 결과, 신뢰할 수 있는 파일입니다 :)";
	        }else {
	        	message = "검증 결과, 신뢰할 수 없는 파일입니다 :(";
	        }
	        request.setAttribute("message", message);
		} catch (Exception e) {
	        request.setAttribute("message", "에러가 발생했습니다. 다시 확인해주세요");
		}
		request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}
