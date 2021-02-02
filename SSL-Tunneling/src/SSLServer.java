import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.util.HashMap;

import javax.net.*;
import javax.net.ssl.*;
import javax.security.cert.X509Certificate;

public class SSLServer {

	private static HashMap<Socket, String> hashMap = new HashMap<Socket, String>();
	
	public static void main(String[] args) throws Exception {

		int i = 0;
		try {
			SystemTrayClass systemtray = new SystemTrayClass();
			systemtray.systemTray();
			ConfigFileServer serverconfig = new ConfigFileServer();
			int destport = serverconfig.getDestPort();
			
			ServerSocketFactory ssf = SSLServer.getServerSocketFactory("TLS");
			ServerSocket ss = ssf.createServerSocket(destport);
			while(true) {
				
				Socket s = ss.accept();
				i++;
				getHashMap().put(s, "Client " + i);
				new ServerHelper(s).start();
				System.out.println("A client is connected");
				
		  }
			
		}catch (Exception exception) {
			exception.printStackTrace();
		}
		
	}
	
	private static ServerSocketFactory getServerSocketFactory(String type) {
		if (type.equals("TLS")) {
		    SSLServerSocketFactory ssf = null;
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

		        ssf = ctx.getServerSocketFactory();
		        return ssf;
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		} else {
		    return ServerSocketFactory.getDefault();
		}
		return null;
        }
	

	public static synchronized HashMap<Socket, String> getHashMap() {
		return hashMap;
	}

	public static synchronized void setHashMap(HashMap<Socket, String> hashMap) {
		SSLServer.hashMap = hashMap;
	}

}
