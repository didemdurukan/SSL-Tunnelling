import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServerHelper extends Thread {

	private Socket s;

	public ServerHelper(Socket s) {
		this.s = s;
	}

	public void run() {
		
		try {
			
			ConfigFileServer serverconfig = new ConfigFileServer();
    		String destIp = serverconfig.getDestIp();
    		int listenport = serverconfig.getListenPort();
    		int destport = serverconfig.getDestPort();
			
			DataInputStream in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
          
            Socket serverSocket = new Socket(destIp,listenport);
            DataOutputStream out = new DataOutputStream(serverSocket.getOutputStream());
            
            ThreadServer threadserver = new ThreadServer(s,serverSocket);
			Thread thread = new Thread(threadserver);
			thread.start();
			
			 int checker;
	         while ((checker = in.read()) != -1){	//Read until return -1
	            out.write(checker);
	            out.flush();
	          }
	    
			 
		
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}

class ThreadServer implements Runnable{  
	
	private Socket s;
	private Socket serverSocket;
	public ThreadServer(Socket s,Socket serverSocket) {
		this.s = s;
		this.serverSocket = serverSocket;
	}
	
	
	public void run(){  
		
		try {
			
			 DataOutputStream out = new DataOutputStream(s.getOutputStream());
             DataInputStream in = new DataInputStream(new BufferedInputStream(serverSocket.getInputStream()));
           
            
			 int checker;
	         while ((checker = in.read()) != -1){	//Read until return -1
	            out.write(checker);
	            out.flush();
	          }
	  
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}  
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	