package view;

import digitalEnvelope.Create;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateEnvelopeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		        String letterFName = "/" + request.getParameter("letterFName");
		    	String content = request.getParameter("content");
		        String myPrivateFName = "/" + request.getParameter("myPrivateFName");
		        String myPublicFName = "/" + request.getParameter("myPublicFName");
		        String mySecretFName = "/" + request.getParameter("mySecretFName");
		        String yourPublicFName = "/" + request.getParameter("yourPublicFName");
		        String sigFName = "/" + request.getParameter("sigFName");
		        String encryptedFName = "/" + request.getParameter("encryptedFName");
			
		        ServletContext context = getServletContext();
		        String letterPath = context.getRealPath(letterFName);
		        String myPrivatePath = context.getRealPath(myPrivateFName);
		        String myPublicPath = context.getRealPath(myPublicFName);
		        String mySecretPath = context.getRealPath(mySecretFName);
		        String yourPublicPath = context.getRealPath(yourPublicFName);
		        String sigPath = context.getRealPath(sigFName);
		        String encryptedPath = context.getRealPath(encryptedFName);
		        
		        Create.createEnvelope(letterPath, content, myPrivatePath, myPublicPath, mySecretPath, yourPublicPath, sigPath, encryptedPath);
		        String message = "전자봉투가 성공적으로 생성되었습니다.<br/> 이 편지는 당신의 친구만 열어볼 수 있습니다!";
		        request.setAttribute("message", message);
       
		}catch (Exception e){
            	request.setAttribute("message", "전자봉투 생성에 실패하였습니다. " + e.getMessage());
		}
		
		request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}
