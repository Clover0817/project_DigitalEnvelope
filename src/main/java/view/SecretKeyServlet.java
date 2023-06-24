package view;

import key.MySecretKey;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class SecretKeyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = "/" + request.getParameter("secretFName");
        ServletContext context = getServletContext();

        String filePath = context.getRealPath(fileName);

        try {
            MySecretKey.createKey(filePath);
            String message = "비밀키가 성공적으로 생성되었습니다.<br/>" + fileName + "에 저장합니다.";
            request.setAttribute("message", message);
        } catch (NoSuchAlgorithmException e) {
            request.setAttribute("message", "비밀키 생성에 실패하였습니다. " + e.getMessage());
        }

        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}
