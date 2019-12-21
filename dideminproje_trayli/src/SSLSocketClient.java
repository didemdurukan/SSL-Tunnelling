import java.io.*;
import java.net.*;
import java.security.KeyStore;
import javax.net.*;
import javax.net.ssl.*;
import java.security.cert.X509Certificate;
import java.security.cert.CertificateException;
import java.util.Properties;
import java.util.Scanner;
/*
 * This example demostrates how to use a SSLSocket as client to
 * send a HTTP request and get response from an HTTPS server.
 * It assumes that the client is not behind a firewall
 */

public class SSLSocketClient {

    public static void main(String[] args) throws Exception {

    Scanner scanner = new Scanner(System.in);
    Properties systemProps = System.getProperties();
    systemProps.put("javax.net.ssl.trustStore", "keystore.ImportKey");
        
    try {
    		ConfigFileClient clientconfig = new ConfigFileClient();
    		String destIp = clientconfig.getDestIp();
    		int listenport = clientconfig.getListenPort();
    		int destport = clientconfig.getDestPort();
    	
    		Socket clientSocket = null;
    		ServerSocket serverSocket = new ServerSocket(listenport);
    		 clientSocket = serverSocket.accept();
    		
    		System.out.println("Listening from port 1234...");
    	
            SSLSocketFactory factory = getSSLSocketFactory("TLS");
            SSLSocket socket =
                (SSLSocket)factory.createSocket(destIp, destport);

            socket.startHandshake();
           
            ThreadSSLSocketClient threadsslsocketclient = new ThreadSSLSocketClient(socket,clientSocket);
    		Thread thread = new Thread(threadsslsocketclient); //burda mý calýscak emin deðilim
    		thread.start();
            
    		sendToServer(clientSocket,socket); 
       
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    
    
    @SuppressWarnings({ "resource", "unused" })
	public static void sendToServer(Socket clientSocket,Socket securesocket) {
    	
    	
		try {

			DataInputStream in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            DataOutputStream out = new DataOutputStream(securesocket.getOutputStream());
            
            int checker;
            while ((checker = in.read()) != -1){	//Read until return -1
            	out.write(checker);
            	 out.flush();
            }
    
		} catch (IOException e) {
			e.printStackTrace();
		}
	
    }
   private static SSLSocketFactory getSSLSocketFactory(String type) {
		if (type.equals("TLS")) {
		    SocketFactory ssf = null;
		    try {
			SSLContext ctx;
		        KeyManagerFactory kmf;
		        KeyStore ks;
		        char[] passphrase = "importkey".toCharArray();

		        ctx = SSLContext.getInstance("TLS");
		        kmf = KeyManagerFactory.getInstance("SunX509");
		        ks = KeyStore.getInstance("JKS");

		        ks.load(new FileInputStream("keystore.ImportKey"), passphrase);
		        kmf.init(ks, passphrase);

			ctx.init(kmf.getKeyManagers(), null, null);

		        ssf = ctx.getSocketFactory();
		        return (SSLSocketFactory) ssf;
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		} else {
		    return (SSLSocketFactory) SSLSocketFactory.getDefault();
		}
		return null;
     }
 }
	
 class ThreadSSLSocketClient extends SSLSocketClient implements Runnable {  
	 
	 private SSLSocket socket;
	 private Socket clientSocket;
	 
	 public ThreadSSLSocketClient(SSLSocket socket,Socket clientSocket){
		 this.socket = socket;
		 this.clientSocket = clientSocket;
	 }
	 
	 public void run(){  
		 
		 Scanner scanner = new Scanner(System.in);
		    Properties systemProps = System.getProperties();
		    systemProps.put("javax.net.ssl.trustStore", "keystore.ImportKey");
		        
		    try {
		    		
		    		 socket.startHandshake();
		    		 
		            getFromServer(clientSocket,socket); 
		       
		        }
		        catch (Exception e) {
		            e.printStackTrace();
		        }

	 }
	 @SuppressWarnings({ "resource", "unused" })
		public static void getFromServer(Socket clientSocket,Socket securesocket) {
	    	
	    	
			try {

				DataInputStream in = new DataInputStream(new BufferedInputStream(securesocket.getInputStream()));
	            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
	            
	            int checker;
	            while ((checker = in.read()) != -1){	//Read until return -1
	            	out.write(checker);
	            	 out.flush();
	            }
	    
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	    }
	 
}
