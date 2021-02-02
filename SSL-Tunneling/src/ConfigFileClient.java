import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
 
public class ConfigFileClient 
{
	public String destIP;
	public int listenPort;
	public int destPort;
	Properties prop = new Properties();
	FileInputStream fis;
	
	public ConfigFileClient() {
		try {
			
			fis = new FileInputStream("configclient.properties");
			prop.load(fis);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    public String getDestIp()
    {
        this.destIP = prop.getProperty("DestinationIP");
        return destIP;
    }
    
    public int getDestPort()
    {
    	String destport = prop.getProperty("DestinationPort");
    	this.destPort = Integer.parseInt(destport);
    	System.out.println("destport: " + destport);
        return destPort;	
    }
    
    public int getListenPort()
    {
    	String listenport = prop.getProperty("ListenPort");
    	this.listenPort = Integer.parseInt(listenport);
    	//System.out.println("listenport: " + listenport);
        return listenPort;	
    }

}