import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;



public class ServerInstance {

	private static final String USER_AGENT = null;

	public static void main(String [] args) throws IOException {
		  HttpServer server = HttpServer.create(new InetSocketAddress(8011), 0);
	      //Declare contexts
		  HttpContext context = server.createContext("/");
		  HttpContext context1 = server.createContext("/index");
	      HttpContext context2 = server.createContext("/index2");
	      HttpContext context3 = server.createContext("/css1");
	      HttpContext context4 = server.createContext("/css2");
	     
	      
	      context.setHandler(ServerInstance::handleRequest);
	      context1.setHandler(ServerInstance::handleRequest);
	      context2.setHandler(ServerInstance::handleRequest2);
	      context3.setHandler(ServerInstance::handleRequest3);
	      context4.setHandler(ServerInstance::handleRequest4);
	      server.start();
	}
	
	public static void handleRequest3(HttpExchange exchange) throws IOException{
		String currentDirPATH = System.getProperty("user.dir");
		File file_index = new File(currentDirPATH + "\\WebContent\\css\\css1.css");
		String response = readFile(file_index);
		sendResponse(exchange, response);
	}
	
	public static void handleRequest4(HttpExchange exchange) throws IOException{
		String currentDirPATH = System.getProperty("user.dir");
		File file_index = new File(currentDirPATH + "\\WebContent\\css\\css2.css");
		String response = readFile(file_index);
		sendResponse(exchange, response);
	}
	
	
	public static void handleRequest2(HttpExchange exchange) throws IOException{
		String currentDirPATH = System.getProperty("user.dir");
		File file_index = new File(currentDirPATH + "\\WebContent\\html\\index2.html");
		String response = readFile(file_index);
		sendResponse(exchange, response);
	}
	
	public static void handleRequest(HttpExchange exchange) throws IOException{
		
		String currentDirPATH = System.getProperty("user.dir");
		File file_index = new File(currentDirPATH + "\\WebContent\\html\\index.html");
		String response = readFile(file_index);
		String url = exchange.getRequestURI().toString();
		System.out.println(url);
		if(url.equals("/")|| url.equals("/index")) {
			sendResponse(exchange, response);	
		}
		else {
			sendBadResponse(exchange);
		}
		
	}
	
	private static String readFile(File file_index) throws IOException {
		FileReader filereader = new FileReader(file_index);
		BufferedReader br = new BufferedReader(filereader);
		String line;
		String text="";
		//Read file
		while((line=br.readLine()) != null) {
			text += line;
		}
		return text;
	}
	
	private static void sendResponse(HttpExchange exchange, String response) throws IOException {
		String encoding = "UTF-8";
		exchange.getResponseHeaders().set("Context-Type", "text/html; charset=" + encoding);
		exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
		Writer out = new OutputStreamWriter(exchange.getResponseBody(), encoding);
	    out.write(response);
	    out.close();
	}
	
	private static void sendBadResponse(HttpExchange exchange) throws IOException {
		String response = "<h1>404 NOT FOUND</h1>";
		String encoding = "UTF-8";
		exchange.getResponseHeaders().set("Context-Type", "text/html; charset=" + encoding);
		exchange.sendResponseHeaders(404, response.getBytes().length);//response code and length
		Writer out = new OutputStreamWriter(exchange.getResponseBody(), encoding);
	    out.write(response);
	    out.close();
	
	}
	
	private static void sendPost() throws Exception{
		String url = "http://localhost:8011/Soen387A1/Servlet1";
	    URL obj = new URL(url);
	    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

	    //add reuqest header
	    con.setRequestMethod("POST");
	    con.setRequestProperty("User-Agent", USER_AGENT);
	    con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

	    String urlParameters = "name=C02G8416DRJM&email=micho&format=text";

	    // Send post request
	    con.setDoOutput(true);
	    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	    wr.writeBytes(urlParameters);
	    wr.flush();
	    wr.close();

	    int responseCode = con.getResponseCode();
	    System.out.println("\nSending 'POST' request to URL : " + url);
	    System.out.println("Post parameters : " + urlParameters);
	    System.out.println("Response Code : " + responseCode);

	    BufferedReader in = new BufferedReader(
	            new InputStreamReader(con.getInputStream()));
	    String inputLine;
	    StringBuffer response = new StringBuffer();

	    while ((inputLine = in.readLine()) != null) {
	        response.append(inputLine);
	    }
	    in.close();

	    //print result
	    System.out.println(response.toString());
	}
	

}
