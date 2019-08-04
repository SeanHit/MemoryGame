package constanvalue;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
public class Block extends JButton implements ActionListener
{
 ImageIcon p1=null,p2=null;
 public Block()
  {
    addActionListener(this);
  } 
 public ImageIcon getImage()
  {
    return p1;
  }  
 public ImageIcon getCloseImage()
  {
    return p2;
  }  
 public void setImage(ImageIcon icon)
  {
	 p1=icon;
  }
 public void setCloseImage(ImageIcon icon)
  {
	 p2=icon;
  }
 public void setButton(ImageIcon icon)
  {
    setIcon(icon);    
  }
 public void actionPerformed(ActionEvent e)
  {
    this.setIcon(p1);
  }
}