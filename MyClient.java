import java.io.*;
import java.net.*;
import java.rmi.Remote;
import java.util.Scanner;
import java.util.function.DoubleToIntFunction;
import java.util.ArrayList;

public class MyClient {
    public static void main(String[] args) throws Exception {
        Scanner kybd = new Scanner(System.in);
        Socket s = new Socket("localhost", 6666); // creates the socket and its address
        ArrayList<String> data = new ArrayList<String>(); // initializes data so it exists before the loop
        MyClient myChoice = new MyClient();

        String pick = "";
        while (!pick.equalsIgnoreCase("Begin")) { //begins the loop to allow user inout
            System.out.println("Type BEGIN to begin");
            pick = kybd.next();
        }
        myChoice.choiceLoop(kybd, s, data, myChoice); // runs the choice loop
    }

    public void print(Scanner kybd, Socket s, ArrayList<String> data, DataOutputStream dout, MyClient myChoice) throws Exception { //method to print inputs
        {
            DataInputStream din=new DataInputStream(s.getInputStream()); //starts a new data input stream to recieve data from the server
            boolean mode = true; //boolean switch 
            String str;
            while(mode == true) 
            {
                str = (String) din.readUTF();  //recieve strings from the server
                data.add(str);//add these strings to an arraylist
                if(str.equals("")) //if the recieved string is empty set the switch to false
                {
                    mode = false; //ends the while loop 
                }
            }
            data.remove(data.get(data.size()-1)); //removes the last empty string 
            for(int i = 0; i<data.size(); i++)
            {   
                System.out.println(data.get(i)); //prints the arraylist

            }
        }
    }

    public void quit(Socket s, DataOutputStream dout) throws Exception {
        {
            dout.close(); // closes stream
            s.close(); // closes socket
        }
    }

    public void choiceLoop(Scanner kybd, Socket s, ArrayList<String> data, MyClient myChoice) throws Exception {
        {
            DataOutputStream dout = new DataOutputStream(s.getOutputStream()); // creates the output stream
            dout.flush();//flushes the socket
            String x = "";
            while (!x.equalsIgnoreCase("quit")) {
                try{
                x = "";
                String oper = "";
                String num2 = "";
                String eq = "";
                System.out.println("Input your first number or the command to PRINT or QUIT: ");
                x = kybd.next();
                if (x.equalsIgnoreCase("print")) {
                    dout.writeUTF(x);//sends print to the server
                    myChoice.print(kybd, s, data, dout, myChoice); // runs the print method
                } else if (x.equalsIgnoreCase("quit")) {
                    myChoice.quit(s, dout);//runs quit method
                } else if (!x.equalsIgnoreCase("print") || !x.equalsIgnoreCase("quit")) {
                    System.out.println("Input the operator (+,-,*,/,^): ");   
                    dout.writeUTF(x); //writes the first number to the server if it is not print
                    oper = kybd.next();
                    System.out.println("Input your second number: ");
                    num2 = kybd.next();
                    dout.writeUTF(num2);//writes second number to server
                    switch(oper) //sends operator and full equation to server as strings depending on the operator chosen
                    {
                        case "+": {
                            dout.writeUTF(oper);
                            eq = x + " " + oper + " " + num2 + " = ";
                            dout.writeUTF(eq);
                            System.out.println("The equation is: " + eq);        
                            continue;
                        }
                        case "-": {
                            dout.writeUTF(oper);
                            eq = x + " " + oper + " " + num2 + " = ";
                            dout.writeUTF(eq);
                            System.out.println("The equation is: " + eq);        
                            continue;
                        }
                        case "*": {
                            dout.writeUTF(oper);
                            eq = x + " " + oper + " " + num2 + " = ";
                            dout.writeUTF(eq);
                            System.out.println("The equation is: " + eq);        
                            continue;
                        }
                        case "/": {
                            dout.writeUTF(oper);
                            eq = x + " " + oper + " " + num2 + " = ";
                            dout.writeUTF(eq);
                            System.out.println("The equation is: " + eq);        
                            continue;
                        }
                        case "^": {
                            dout.writeUTF(oper);
                            eq = x + " " + oper + " " + num2 + " = ";
                            dout.writeUTF(eq);
                            System.out.println("The equation is: " + eq);        
                            continue;
                        }
                        default: {
                            System.out.println("Invalid expression invalid operator");
                            dout.writeUTF(oper);
                            eq = x + " " + oper + " " + num2 + " = ";
                            dout.writeUTF(eq);
                            choiceLoop(kybd, s, data, myChoice);
                            return;
                        }
                    }
                }
            }catch(IOException e){}
        }
    }
}
}