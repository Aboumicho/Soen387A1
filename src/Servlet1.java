

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet1() {
        super();
        // TODO Auto-generated constructor stub
    }
   
    /**
     *Sets Plain text response
     * */
    public void setPlain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 PrintWriter out = response.getWriter();
    	 response.setContentType("text/plain");   	 
         Enumeration<String> parameterNames = request.getParameterNames();
         out.write("Request Method: Get \nRequest Headers: \n");
         while (parameterNames.hasMoreElements()) {
  
             String paramName = parameterNames.nextElement();
             out.write("    " + paramName + ": ");
  
             String[] paramValues = request.getParameterValues(paramName);
             for (int i = 0; i < paramValues.length; i++) {
                 String paramValue = paramValues[i];
                 out.write(paramValue);
                 out.write("\n");
             }
         }
         out.close();	
    }
    
    /**
     *Sets Html text response
     * */
    public void setHtml(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	PrintWriter out = response.getWriter();
    	response.setContentType("text/html"); 
        Enumeration<String> parameterNames = request.getParameterNames();
        //Request Method
        out.write("<table> <tr><td> Request Method </td> <td>Get </td><td></td></tr></table> ");
        String Headers = "<table><tr><td>Request Headers </td></tr>";
        
        while (parameterNames.hasMoreElements()) {
 
            String paramName = parameterNames.nextElement();
            Headers+= "<tr><td>" + paramName + "</td>";
 
            String[] paramValues = request.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
	           String paramValue = paramValues[i];
	           Headers+="<td>" + paramValue + "</td></tr>";
	         }
            
        }
        Headers+="</table>";
        out.write(Headers);
        out.close();	
    }
    //Set XML
    public void setXml(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	PrintWriter out = response.getWriter();
    	response.setContentType("text/xml"); 
        Enumeration<String> parameterNames = request.getParameterNames();
        //Request Method
        String Headers = "<response> <request-method> Get </request-method><request-headers>";
        String Format = "";
        
        //request-headers
        while (parameterNames.hasMoreElements()) {
 
            String paramName = parameterNames.nextElement();
            Headers+= "<header name=\"" + paramName + "\" >" ;
            Format += "<" + paramName + ">";
            String[] paramValues = request.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
	             String paramValue = paramValues[i];
	             Headers+= paramValue + "</header>";
	             Format += paramValue + "</" + paramName + ">";
	        }
        }
        Headers += "</request-headers><query-string><format>xml</format>";
        Headers += Format;
        Headers+="</query-string></response>";
        out.write(Headers);
        out.close();	
    }
    
	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String format = request.getParameter("format");
		PrintWriter out = response.getWriter();
   	 	response.setContentType("text/plain");
		System.out.println(format);
		//Set format		
		if(format == null) {
			 response.setContentType("text/html");
			 out.write("<table> <tr><td> Request Method </td> <td>Get </td><td></td></tr></table> ");
			 String Headers = "<table><tr><td>Request Headers </td></tr><tr><td>format</td><td>html</td></tr>";
			 System.out.println(request.getParameterNames().hasMoreElements());
			 if(request.getParameterNames().hasMoreElements()) {
				 
				 while (request.getParameterNames().hasMoreElements()) {
					 
			            String paramName = request.getParameterNames().nextElement();
			            Headers+= "<tr><td>" + paramName + "</td>";
			 
			            String[] paramValues = request.getParameterValues(paramName);
			            for (int i = 0; i < paramValues.length; i++) {
				           String paramValue = paramValues[i];
				           Headers+="<td>" + paramValue + "</td></tr>";
				         }
			            
			        }
			        Headers+="</table>";
			        out.write(Headers);
			        out.close();	
			 }
			 else {
				 Headers+="</table>";
				 out.write(Headers);
			 }
			 
		}
		else {
			switch(format) {
			case "null": setHtml(request, response); break;
			case "text": setPlain(request, response); break;
			case "html": setHtml(request, response); break;
			case "xml": setXml(request, response); break;
			default: response.setContentType("text/html");setPlain(request, response); 
		}
		}
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}
