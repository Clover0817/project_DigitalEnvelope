package view;

import key.MyKeyPair;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class KeyPairServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // JSP에서 전달된 데이터 받기
        String publicFileName = "/" + request.getParameter("publicFileName");
        String privateFileName = "/" + request.getParameter("privateFileName");

        
        ServletContext context = getServletContext();
        String publicPath = context.getRealPath(publicFileName);
        String privatePath = context.getRealPath(privateFileName);

        // 입력된 데이터를 이용하여 MyKeyPair 클래스의 로직 수행
        try {		
			MyKeyPair.createKeyPair(publicPath, privatePath);
            String message = "공개키와 개인키가 성공적으로 생성되었습니다." + "<br/>각각 " + publicFileName + ", " + privateFileName + "에 저장합니다.";
            request.setAttribute("message", message);
        } catch (NoSuchAlgorithmException e) {
            request.setAttribute("message", "키 생성에 실패하였습니다. " + e.getMessage());
        }

        // 결과를 JSP로 전달
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}
