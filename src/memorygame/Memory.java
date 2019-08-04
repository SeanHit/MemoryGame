package memorygame;
import javax.swing.*;
import constanvalue.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.LinkedList;
public class Memory extends JFrame implements ActionListener
{ 
	JMenuBar bar;
	  JMenu fileMenu;
	  JMenu fileMenu1= new JMenu("排行榜");
	  JMenuItem fileMenu2 =new JMenuItem("放 弃");
	  JMenuItem Primary,Middle,High,pRank,mRank,hRank;
	  Block block[];
	  ImageIcon icon[];
	  MemoryPane panel=null;
	  File filePrimary=new File("src"+File.separator+"resource"+File.separator+"easyRank.txt");
	  File fileMiddle=new File("src"+File.separator+"resource"+File.separator+"normalRank.txt");
	  File fileHigh=new File("src"+File.separator+"resource"+File.separator+"hardRank.txt");
	  String string =filePrimary.getParent();
	  
	  
	  LinkedList grade=null;
	  ShowRecord showgrade=null;
	  int m=5,n=6;                       //测试区域的row和column                 
	  int imageNum=0;
	  Container con=null;
	  JTextField tips=null;
	  File gradeFile=null;    

          
  public  Memory()
  { 
	  System.out.println(string);
    block=new Block[m*n];
    imageNum=m; 
    icon=new ImageIcon[imageNum];
    String path = "";
    for(int i=0;i<icon.length;i++)
       {
    	path = "/resource/a"+i+".jpg";
        icon[i]=new ImageIcon(Memory.class.getResource(path));
       } 
    for(int i=0;i<block.length;i++)
       {
         block[i]=new Block();
         block[i].setCloseImage(new ImageIcon(""));
       }
    this.setTitle("记忆力游戏");
    bar=new JMenuBar();
    fileMenu=new JMenu("记忆力测试");
    Primary=new JMenuItem("初级");
    Middle=new JMenuItem("中级");
    High=new JMenuItem("高级");   
    hRank=new JMenuItem("高级排行");
    mRank=new JMenuItem("中级排行");
    pRank=new JMenuItem("初级排行");
    fileMenu.add(Primary);
    fileMenu.add(Middle);
    fileMenu.add(High);
    fileMenu1.add(pRank);
    fileMenu1.add(mRank);
    fileMenu1.add(hRank);
    bar.add(fileMenu);
    bar.add(fileMenu1);
    bar.add(fileMenu2);
    setJMenuBar(bar);
    Primary.addActionListener(this);
    Middle.addActionListener(this);
    High.addActionListener(this);
    pRank.addActionListener(this);
    mRank.addActionListener(this);
    hRank.addActionListener(this);
    fileMenu2.addActionListener(this);
    grade=new LinkedList();
    if(!filePrimary.exists())                         
     {
      try{
          FileOutputStream out=new FileOutputStream("src"+File.separator+"resource"+File.separator+"easyRank.txt");
          ObjectOutputStream object_out=new ObjectOutputStream(out);
          object_out.writeObject(grade);
          object_out.close();
          out.close();
         }
      catch(IOException e)
         {
         }
     } 
     if(!fileMiddle.exists())
     {
      try{
          FileOutputStream out=new FileOutputStream("src"+File.separator+"resource"+File.separator+"normaRank.txt");
          ObjectOutputStream object_out=new ObjectOutputStream(out);
          object_out.writeObject(grade);
          object_out.close();
          out.close();
         }
      catch(IOException e)
         {
         }
     } 
     if(!fileHigh.exists())
     {
      try{
          FileOutputStream out=new FileOutputStream("src"+File.separator+"resource"+File.separator+"hardRank.txt");
          ObjectOutputStream object_out=new ObjectOutputStream(out);
          object_out.writeObject(grade);
          object_out.close();
          out.close();
         }
      catch(IOException e)
         {
         }
     } 
    gradeFile=filePrimary;
    setBounds(200,200,400,400);
    setVisible(true);
    addWindowListener(new WindowAdapter()
      {
        public void windowClosing(WindowEvent e)
         {
          System.exit(0);
         }
      });
    con=getContentPane(); 
     panel=new MemoryPane(block,icon,m,n,gradeFile);
    tips=new JTextField("初级：您需要连续找出"+6+"个相同的图标"); 
    tips.setEditable(false);
    tips.setForeground(Color.red);
    con.add(panel,BorderLayout.CENTER);
    con.add(tips,BorderLayout.SOUTH);
    con.validate();
    this.validate();
 }
 public void LevelTest(int width,int height,File f)
 {    
    m=width;
    n=height;
    imageNum=m;
    gradeFile=f;
    block=new Block[m*n];
    String path = "";
    icon=new ImageIcon[imageNum];
       for(int i=0;i<icon.length;i++)
       {
    	 path = "/resource/a"+i+".jpg";
         icon[i]=new ImageIcon(Memory.class.getResource(path));
       } 
    for(int i=0;i<block.length;i++)
       {
         block[i]=new Block();
        
       }
    panel=new MemoryPane(block,icon,m,n,gradeFile);
    con.removeAll();
    con.add(panel,BorderLayout.CENTER);
    con.add(tips,BorderLayout.SOUTH);
    con.validate();
    this.validate();
 }
 public void actionPerformed(ActionEvent event)
 { 
   if(event.getSource()==Primary) 
       {  
         LevelTest(5,6,filePrimary);
         setBounds(200,200,400,400);
//         panel.open();
         this.validate();
         tips.setText("初级：您需要连续找出"+6+"个相同的图标");
       }
   if(event.getSource()==Middle) 
       {
         LevelTest(6,7,fileMiddle);
         setBounds(200,200,400,400);
         this.validate();
         tips.setText("中级：您需要连续找出"+7+"个相同的图标");
       }
   if(event.getSource()==High) 
       {  
         LevelTest(7,8,fileHigh);
         setBounds(200,200,400,400);
         this.validate();
         tips.setText("高级：您需要连续找出"+8+"个相同的图标");
       }
   if(event.getSource()==hRank)
       {
         showgrade=new ShowRecord(this,fileHigh);
         showgrade.setVisible(true);
       }
    if(event.getSource()==mRank)
       {
         showgrade=new ShowRecord(this,fileMiddle);
         showgrade.setVisible(true);
       }
     if(event.getSource()==pRank)
       {
         showgrade=new ShowRecord(this,filePrimary);
         showgrade.setVisible(true);
       }
     if(event.getSource() ==fileMenu2)
     {	
    	 System.out.println("1");
    	 panel.open();
    	 panel.clock.stop();
     }
     
     
 } 

 public static void main(String args[])
  {
    new Memory();
  }
}