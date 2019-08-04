package memorygame;
import constanvalue.*;
import constanvalue.*;
import constanvalue.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Record extends JFrame implements ActionListener
{
  int time=0;
  JTextField yourName,label; 
  JButton yes,no;
  File gradeFile=null;
  public Record(File f)
  {
    super("记录你的成绩到:"+f.toString());
    gradeFile=f;
    setBounds(100,100,330,160);
    setResizable(false);
    setVisible(false);
    yes=new JButton("确认");
    no=new JButton("取消");
    yourName=new JTextField(8);
    yourName.setText("匿名");
    yes.addActionListener(this);
    no.addActionListener(this);
    Container con=getContentPane();
    con.setLayout(new GridLayout(2,1));
    label=new JTextField("输入你的姓名,将成绩存放到排行榜:"+f.toString());
    label.setEditable(false);
    con.add(label);
    JPanel p=new JPanel();
    p.add(yourName);
    p.add(yes);
    p.add(no);
    con.add(p);
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
  public void setFile(File f)
  { 
    gradeFile=f;
  }
  public void setTime(int time)
  {
    this.time=time;
  }
  public void actionPerformed(ActionEvent e)
  { 
    if(e.getSource()==yes)
     {
      try{
          FileInputStream in=new FileInputStream(gradeFile);
          ObjectInputStream object_in=new ObjectInputStream(in);
          LinkedList list=(LinkedList)object_in.readObject();
          object_in.close();
          in.close(); 
          People people=new People(yourName.getText(),time);
          list.add(people);
          FileOutputStream out=new FileOutputStream(gradeFile);
          ObjectOutputStream object_out=new ObjectOutputStream(out);
          object_out.writeObject(list);
          out.close();
          object_out.close();
           
         }
     catch(Exception event) 
         {
            System.out.println(event);
         }
       
       setVisible(false);
       dispose();
     }
    if(e.getSource()==no)
     {
       setVisible(false);
       dispose();
     }  
  }
}
