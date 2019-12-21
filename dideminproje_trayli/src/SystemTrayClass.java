import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class SystemTrayClass {
	
  public void systemTray() {
	  
    
    SystemTray tray = SystemTray.getSystemTray();
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Image image = toolkit.getImage("connectedtray.jpg");

    PopupMenu menu = new PopupMenu();

    MenuItem messageItem = new MenuItem("About");
    messageItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Emine Didem Durukan,20160702050");
      }
    });
    menu.add(messageItem);

    MenuItem closeItem = new MenuItem("Close");
    closeItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    menu.add(closeItem);
    TrayIcon icon = new TrayIcon(image, "471 project", menu);
    icon.setImageAutoSize(true);

    try {
		tray.add(icon);
	} catch (AWTException e1) {
		e1.printStackTrace();
	}
  
  }

}
