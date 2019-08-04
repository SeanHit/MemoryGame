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
  Block block[];                         //��ͼ���square����
  ImageIcon icon[];                      //ͼ������
  LinkedList  listIocn=null,             //��������������ͼ��square
              listBlock=null;            //��������������square����
  int row=0,column=0;                     
  int success=0;                         //������¼�Ƿ�ɹ��ı�����
                                         //���ñ���ֵ����columnʱ�ͳɹ��ˡ�
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
     clock=new Timer(1000,this);                   //ÿ��һ���ʱһ��           
     clockStarted=false;
   }
  public void actionPerformed(ActionEvent e)
  {	
	  
	  
    if(e.getSource() instanceof Block)
    { 
        Block square=(Block)e.getSource();
        ImageIcon p1=square.getImage();
        square.setButton(p1);
        if(listIocn.size()==0)                        //������һ��square          
         {
          listIocn.add(p1);
          listBlock.add(square);
          success=1;                                 //�ɹ�һ��           
    }
     else
     {
           ImageIcon temp=(ImageIcon)listIocn.getLast();      //ȡ����һ�η�����
                                                              //square��ͼ�� 
                                                              
           if(temp==p1&&!(listBlock.contains(square))) //�������һ�η���
              {                                                //��ͼ����ͬ��
                success=success+1;                             //�÷�
                listIocn.add(p1);             //���ͼ�꼰square������        
                listBlock.add(square);
                if(success==column)                       //���β��Գɹ�
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
           else if((temp!=p1)&&(!(listBlock.contains(square))))//�������һ�η�����ͼ�겻ͬ 
            {
                listIocn.clear();              //��ͼ���square�������            
                listBlock.clear();
                listIocn.add(p1);    //���µ�ͼ�ꡢsquare������ӵ�����           
                listBlock.add(square);
                success=1;                         //���ɹ������ָ���һ��        
                for(int i=0;i<block.length;i++)    //������square���ó���ʾ�ر�ͼ��        
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
    	   clock.start(); //clock��ʼ                        
           clockStarted=true;//���¼�ʱ������ActionEvent�¼�
           time=1;
           timeShow.setText("������ʱ:"+time+"��");
                                        
         }
    }
   if(e.getSource()==clock)
    {
      time = time+1;
      timeShow.setText("������ʱ:"+time+"��");
    }
     
 }
  public void open() {
	  for(int i=0;i<block.length;i++)   
      {
       
             block[i].setButton(block[i].getImage());
           
     } 
	
}
}