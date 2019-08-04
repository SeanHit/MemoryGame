package memorygame;
import constanvalue.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class ShowRecord extends JDialog implements ActionListener
{ 
  File gradeFile=null;
  JButton yes,cancel;
  JTextArea show=null;
 public ShowRecord(JFrame frame,File f)
  {
    super(frame,"记忆测试排行榜:"+f.toString());
    gradeFile=f;
    show=new JTextArea(6,4);
    yes=new JButton("显示排行");
    yes.addActionListener(this);
    cancel=new JButton("清空排行");
    cancel.addActionListener(this);
    Container con=getContentPane(); 
    con.add(new JScrollPane(show),BorderLayout.CENTER);
    JPanel p=new JPanel();
    p.add(yes);
    p.add(cancel);
    con.add(p,BorderLayout.SOUTH);
    setBounds(100,100,320,185);
    setVisible(false);
    setModal(true); 
   
    addWindowListener(new WindowAdapter()
                        {
                          public void windwoClosing(WindowEvent e)
                            {
                              setVisible(false);
                              dispose();
                            }
                        }
                     ); 
   }

 public void actionPerformed(ActionEvent e)
  { 
    
    if(e.getSource()==yes)
     { 
       try
         {
           show.setText(null);
           FileInputStream in=new FileInputStream(gradeFile);
           ObjectInputStream object_in=new ObjectInputStream(in);
           LinkedList grade1=(LinkedList)object_in.readObject();
           object_in.close();
           sort(grade1);                                        
           for(int i=0;i<grade1.size();i++)
            {
              People people=(People)grade1.get(i);
              show.append("\n"+people.getName()+"grade1:"+people.getTime());
            }
         }
        catch(Exception ee)
         {
         }
     }
   if(e.getSource()==cancel)
     { 
       try
         {
           FileInputStream in=new FileInputStream(gradeFile);
           ObjectInputStream object_in=new ObjectInputStream(in);
           LinkedList grade1=(LinkedList)object_in.readObject();
           object_in.close();
           grade1.clear();
           FileOutputStream out=new FileOutputStream(gradeFile);
           ObjectOutputStream object_out=new ObjectOutputStream(out);
           object_out.writeObject(grade1);
           out.close();
           object_out.close(); 
           show.setText("排行被清空");
         }
        catch(Exception ee)
         {
         }
     }    
  }
 public void sort(LinkedList list)
  {
    for(int i=0;i<list.size()-1;i++)
      {
        
         for(int j=i+1;j<list.size();j++)
            {
             if(((People)list.get(i)).getTime()>((People)list.get(j)).getTime())
                {
                   People temp=(People)list.get(j);
                   list.set(j,(People)list.get(i));
                   list.set(i,temp);
                }
            } 
      }
  }
}
