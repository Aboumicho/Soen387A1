import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;



public class ServerInstance {

	public static void main(String [] args) throws IOException {
		  HttpServer server = HttpServer.create(new InetSocketAddress(8011), 0);
	      HttpContext context = server.createContext("/");
		  HttpContext context1 = server.createContext("/index");
	      HttpContext context2 = server.createContext("/index2");
	      HttpContext context3 = server.createContext("/css1");
	      HttpContext context4 = server.createContext("/css2");
	      context2.setHandler(ServerInstance::handleRequest2);
	      context.setHandler(ServerInstance::handleRequest);
	      context1.setHandler(ServerInstance::handleRequest);
	      server.start();
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
	

}
