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
      Vector vector=new Vector();            //向量设置       
      int n=icon.length;
     for(int i=0;i<block.length;i++)         //将1至n构成的Point对象数均匀     
       {                                     //添加到向量中
         int x=i%n;
         Point p=new Point(x);
         vector.addElement(p);
       } 
     for(int i=0;i<block.length;i++)
       {
         int m=(int)(Math.random()*vector.size()); //随机获取一个0到vector.size()
                                                   //之间的数。
         Point p=(Point)vector.elementAt(m);       //取出随机数对应位置处的Point
                                                   //对象。
         int x=p.getInt();                         //获取Point对象中的整数
         block[i].setImage(icon[x]); 
         vector.remove(m);           
       }
    } 
 } 
