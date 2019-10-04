import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.net.httpserver.HttpExchange;

public class RequestHandler {
	
	
	public String mapRequest(String format, HashMap parameters_map, HttpExchange exchange) throws ServletException, IOException {
		
		if(format.equals("")) {
			return setHtml(parameters_map, exchange);
		}
		else if(format.equals("xml")) {
			return setXml(parameters_map, exchange);
		}
		else if(format.equals("html")) {
			return setHtml(parameters_map, exchange);
		}
		else if(format.equals("text")) {
			return setPlain(parameters_map, exchange);
		}
		
		return setHtml(parameters_map, exchange);
		
	}

	public String setPlain(HashMap parameters_map, HttpExchange exchange) throws ServletException, IOException {
 
		String Headers = "Request Method:"+ exchange.getRequestMethod()+"\nRequest Headers: \n";
		
		//parameters_map.forEach((key,value) ->{ Headers += "    " + key + ": " + value });
		Iterator<Entry<String, String>> it = parameters_map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> pair = (Map.Entry<String, String>) it.next();
			Headers += "    " + pair.getKey() + ": " + pair.getValue() + "\n";
		}

		return Headers;
		
   }
   
   /**
    *Sets Html text response
    * */
   public String setHtml(HashMap parameters_map, HttpExchange exchange) throws ServletException, IOException{

   	String stylesheet = "<link rel=\'stylesheet\' type=\'text/css\' href=\'./css/css1.css\' /> ";
	String Headers = stylesheet;
       //Request Method
       Headers+=("<table> <tr><td> Request Method </td> <td>" + exchange.getRequestMethod() + " </td></tr></table> ");
       Headers += "<table><tr><td>Request Headers </td></tr>";
       Iterator<Entry<String, String>> it = parameters_map.entrySet().iterator();
       while (it.hasNext()) {
    	   Map.Entry<String, String> pair = (Map.Entry<String, String>) it.next();
           Headers+= "<tr><td>" + pair.getKey() + "</td>" + "<td>" + pair.getValue() + "</td></tr>" ;
       }
       Headers+="</table>";
       return Headers;
   }
   //Set XML
   public String setXml(HashMap parameters_map, HttpExchange exchange) throws ServletException, IOException{
       //Request Method
       String Headers = "<response> <request-method>" + exchange.getRequestMethod() + " </request-method><request-headers>";
       //request-headers
       Iterator<Entry<String, String>> it = parameters_map.entrySet().iterator();
       while (it.hasNext()) {
    	   Map.Entry<String, String> pair = (Map.Entry<String, String>) it.next();
    	   Headers+= "<header name=\"" + pair.getKey() + "\" >" + pair.getValue() + "</header>" ;     
       }    
       Headers += "</request-headers><query-string><format>xml</format>";
       Iterator<Entry<String, String>> it2 = parameters_map.entrySet().iterator();
       while(it.hasNext()) {
    	   Map.Entry<String, String> pair = (Map.Entry<String, String>) it.next();
    	   Headers+= "<" + pair.getKey() + ">" + pair.getValue() + "</" + pair.getKey() + ">" ;   
       }     
       Headers+="</query-string></response>";
       return Headers;
   }
	
}
