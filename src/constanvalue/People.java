package constanvalue;
public class People implements java.io.Serializable
{
  String name=null;
  int time=0;                          
 public People(String name,int t)
  {
    this.name=name;
    time=t;
  }
 public int getTime()
  {
    return time;
  }
 public String getName()
  {
   return name;
  }
}