package memorygame;
import constanvalue.*;
import javax.swing.*; 
import java.awt.event.*;
import java.awt.*;
import java.util.LinkedList;
import java.io.*;

public class MemoryPane extends JPanel implements ActionListener
{
  RandomSetIcon  icImage=null;
  Block block[];                         //带图标的square数组
  ImageIcon icon[];                      //图标数组
  LinkedList  listIocn=null,             //链表对象，用来添加图标square
              listBlock=null;            //链表对象，用来添加square对象
  int row=0,column=0;                     
  int success=0;                         //用来记录是否成功的变量，
                                         //当该变量值等于column时就成功了。
  int time=0;                            
  javax.swing.Timer clock=null;         
  JTextField timeShow=null;
  File gradeFile=null;
  boolean clockStarted=false;
  public  MemoryPane(Block[] block,ImageIcon[] icon,int m,int n,File f)
   {
	 
     icImage=new RandomSetIcon();
     this.block=block;
     this.icon=icon;
     row=m;
     column=n;
     gradeFile=f;
     
     listIocn=new LinkedList();
     listBlock=new LinkedList();
     setLayout(new BorderLayout());
     JPanel center=new JPanel();
     center.setLayout(new GridLayout(row,column));
     for(int i=0;i<block.length;i++)
        {
          center.add(block[i]);
          block[i].addActionListener(this);
        }
     JPanel south=new JPanel();
     timeShow=new JTextField(12);
     timeShow.setEditable(false);
     timeShow.setForeground(Color.red);
     south.add(timeShow);
     add(center,BorderLayout.CENTER);
     add(south,BorderLayout.SOUTH); 
     icImage.randomButton(block,icon);
     clock=new Timer(1000,this);                   //每隔一秒计时一次           
     clockStarted=false;
   }
  public void actionPerformed(ActionEvent e)
  {	
	  
	  
    if(e.getSource() instanceof Block)
    { 
        Block square=(Block)e.getSource();
        ImageIcon p1=square.getImage();
        square.setButton(p1);
        if(listIocn.size()==0)                        //翻开第一个square          
         {
          listIocn.add(p1);
          listBlock.add(square);
          success=1;                                 //成功一次           
    }
     else
     {
           ImageIcon temp=(ImageIcon)listIocn.getLast();      //取出上一次翻开的
                                                              //square的图标 
                                                              
           if(temp==p1&&!(listBlock.contains(square))) //如果和上一次翻开
              {                                                //的图标相同。
                success=success+1;                             //得分
                listIocn.add(p1);             //添加图标及square到链表        
                listBlock.add(square);
                if(success==column)                       //本次测试成功
                  {
                    for(int i=0;i<block.length;i++)           
                     {
                       block[i].setEnabled(false);
                     }
                    for(int j=0;j<listBlock.size();j++)
                     {
                       Block b=(Block)listBlock.get(j);
                       b.setDisabledIcon(b.getImage());
                     } 
                    clock.stop();
                    Record record=new Record(gradeFile);
                    record.setTime(time);
                    record.setVisible(true);
                  }    
           }
           else if((temp!=p1)&&(!(listBlock.contains(square))))//如果和上一次翻开的图标不同 
            {
                listIocn.clear();              //将图标和square链表清空            
                listBlock.clear();
                listIocn.add(p1);    //将新的图标、square重新添加到链表           
                listBlock.add(square);
                success=1;                         //将成功次数恢复到一次        
                for(int i=0;i<block.length;i++)    //将其他square设置成显示关闭图标        
                  {
                    if(square!=block[i])
                       {
                         block[i].setButton(block[i].getCloseImage());
                         
                         
                       }
                 }                
               }  
         }
       
       if(clockStarted==false)
         {
    	   clock.start(); //clock开始                        
           clockStarted=true;//重新计时，触发ActionEvent事件
           time=1;
           timeShow.setText("您的用时:"+time+"秒");
                                        
         }
    }
   if(e.getSource()==clock)
    {
      time = time+1;
      timeShow.setText("您的用时:"+time+"秒");
    }
     
 }
  public void open() {
	  for(int i=0;i<block.length;i++)   
      {
       
             block[i].setButton(block[i].getImage());
           
     } 
	
}
}