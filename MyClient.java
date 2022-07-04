import java.io.*;  
import java.net.*;  
import java.util.Scanner;
import java.util.ArrayList;
public class MyClient {  
public static void main(String[] args)throws Exception{ 
MyClient myChoice = new MyClient();
myChoice.choiceLoop(); //runs the choice loop
}
  

public void choiceLoop()throws Exception{ 
{
    Scanner kybd = new Scanner(System.in);
    Socket s=new Socket("localhost",6666);  //creates the socket and its address

    DataOutputStream dout=new DataOutputStream(s.getOutputStream()); //creates the output stream
    ArrayList<String> data = new ArrayList<String>(); //initializes data so it exists before the loop
    String x = "";
    String oper = "";
    Double firstNum = 0.0;
    Double secNum = 0.0;
    String eq = "";
    while(!x.equalsIgnoreCase("quit"))
    {
        System.out.println("Type PRINT to print server, type QUIT to exit the program, or type BEGIN to begin");
        x = kybd.next();

        if(x.equalsIgnoreCase("print"))
        {
            for(int i =0; i<data.size(); i++) //sends each entry of the arraylist across the socket
            {
                dout.writeUTF(i + ". )" + data.get(i)); //writes to socket
                dout.flush(); //clears socket
            }   
            dout.close(); //closes stream 
            s.close(); //closes socket
            return;
        }else if(x.equalsIgnoreCase("quit"))
        {
            break;
        }else if(x.equalsIgnoreCase("begin"))
        {
            Double outpt = 0.0;
            System.out.println("Input your first number: ");
            firstNum = kybd.nextDouble();
            System.out.println("Input the operator (+,-,*,/,^): ");
            oper = kybd.next();
            System.out.println("Input your second number: ");
            secNum = kybd.nextDouble();
            eq = firstNum.toString() + " " + oper + " " + secNum.toString() + " = ";
            System.out.println("The equation is: "+ eq);
            switch(oper)
            {
                case "+" :
                {
                    outpt = firstNum+secNum;
                    System.out.println("The answer is: "+outpt);
                    String ans = (eq + outpt); 
                    data.add(ans.toString());
                    break;
                }
                case "-" :
                {
                    outpt = firstNum-secNum;
                    System.out.println("The answer is: "+outpt); 
                    String ans = (eq + outpt); 
                    data.add(ans.toString());
                    break;
                }
                case "*" :
                {
                    outpt = firstNum*secNum;
                    System.out.println("The answer is: "+outpt);
                    String ans = (eq + outpt); 
                    data.add(ans.toString());
                    break;
                }
                case "/" :
                {
                    outpt = (Double)firstNum/secNum;
                    System.out.println("The answer is: "+outpt); 
                    String ans = (eq + outpt); 
                    data.add(ans.toString());
                    break;
                }
                case "^" :
                {
                    outpt = (Double)Math.pow(firstNum,secNum);
                    System.out.println("The answer is: "+outpt); 
                    String ans = (eq + outpt); 
                    data.add(ans.toString());
                    break;
                }
                default :
                {
                    System.out.println("Invalid expression invalid operator");
                    choiceLoop();
                    return;
                }
            }   
        }   
    }
}
}  
}