import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;



public class ServerInstance {
	
	public static void main(String [] args) throws IOException {
		/*System.out.println("Works" );
		final ServerSocket serverSocket = new ServerSocket(8015);
		
		while(true) {
			final Socket socket = serverSocket.accept();
			final OutputStream outputStream = socket.getOutputStream();
			final PrintWriter printWriter = new PrintWriter(outputStream);
			System.out.println(socket.getRemoteSocketAddress());
			printWriter.println("HTTP/1.0 200 OK" + socket);
			System.out.println("Connected");
			String currentDirPATH = System.getProperty("user.dir");
			File file = new File(currentDirPATH + "\\WebContent\\html\\index.html");
			FileReader filereader = new FileReader(file);
			BufferedReader br = new BufferedReader(filereader);
			String line;
			
			//Read file
			while((line=br.readLine()) != null) {
				System.out.println(line);
			}
		}*/
		
		  HttpServer server = HttpServer.create(new InetSocketAddress(8011), 0);
	      HttpContext context = server.createContext("/");
	      context.setHandler(ServerInstance::handleRequest);
	      server.start();
		
		
	}
	
	public static void handleRequest(HttpExchange exchange) throws IOException{
		
		String currentDirPATH = System.getProperty("user.dir");
		File file = new File(currentDirPATH + "\\WebContent\\html\\index.html");
		FileReader filereader = new FileReader(file);
		BufferedReader br = new BufferedReader(filereader);
		String line;
		String text="";
		//Read file
		while((line=br.readLine()) != null) {
			text += line;
		}
		String response = text;
		System.out.println(response);
		exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
	    OutputStream os = exchange.getResponseBody();
	    os.write(response.getBytes());
	    os.close();
	}
	

}
