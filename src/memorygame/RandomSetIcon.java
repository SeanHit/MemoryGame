package memorygame;
import constanvalue.*;
import javax.swing.*;
import java.util.Vector;
class Point
{ 
  int x;
  Point(int i)
  {
    x=i;
  }
  public int getInt()
  {
    return x;
  }
}
public class  RandomSetIcon 
 {
  public RandomSetIcon()
    {
    }
  public void randomButton(Block[] block,ImageIcon icon[])
    {
      Vector vector=new Vector();            //��������       
      int n=icon.length;
     for(int i=0;i<block.length;i++)         //��1��n���ɵ�Point����������     
       {                                     //��ӵ�������
         int x=i%n;
         Point p=new Point(x);
         vector.addElement(p);
       } 
     for(int i=0;i<block.length;i++)
       {
         int m=(int)(Math.random()*vector.size()); //�����ȡһ��0��vector.size()
                                                   //֮�������
         Point p=(Point)vector.elementAt(m);       //ȡ���������Ӧλ�ô���Point
                                                   //����
         int x=p.getInt();                         //��ȡPoint�����е�����
         block[i].setImage(icon[x]); 
         vector.remove(m);           
       }
    } 
 } 
