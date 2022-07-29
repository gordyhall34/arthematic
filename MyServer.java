import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class MyServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(6666);
        ArrayList<String> data = new ArrayList<String>(); // initializes data so it exists before the loop
        Socket s = ss.accept();// establishes connection
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream()); // creates the output stream
        MyServer myMethod = new MyServer();
        myMethod.loop(data, dis, dout);
    }

    public void loop(ArrayList<String> data, DataInputStream dis, DataOutputStream dout)
    {
        while (true) {
            try {
                String eq = "";
                String oper = "";
                String num2 = "";
                String x = "";
                Double firstNum = 0.0;
                Double secNum = 0.0;
                Double outpt = 0.0;
                String ans = "";
                boolean isNum1 = false;
                boolean isNum2 = false;
                String outptCase2 = "";
                String outptCase3 = "";
                String outptCase4 = "";
                x = (String) dis.readUTF();
                if(x.equalsIgnoreCase("print"))
                {
                    try{
                    for(int i = 0; i < data.size(); i++)
                    {
                        dout.writeUTF(data.get(i));
                    }
                    data.add("");
                    dout.writeUTF(data.get(data.size()-1));
                    for(int u = data.size(); u > 0 ; u--)
                    {
                        data.remove(0); //removes all entries from arraylist
                    }
                    loop(data, dis, dout);
                }catch(IOException e){}
                }
                num2 = (String) dis.readUTF();
                oper = (String) dis.readUTF();
                eq = (String) dis.readUTF();
                isNum1 = x.matches(".*[0-9].*");
                isNum2 = num2.matches(".*[0-9].*");
                if (isNum1 == true) {
                    firstNum = Double.parseDouble(x);
                }
                if (isNum2 == true) {
                    secNum = Double.parseDouble(num2);
                }
                if (isNum1 == true && isNum2 == true) {
                    switch (oper) {
                        case "+": {
                            outpt = firstNum + secNum;
                            System.out.println("The answer is: " + outpt);
                            ans = (eq + outpt);
                            data.add(ans);
                            // dout.flush(); // clears socket
                            break;
                        }
                        case "-": {

                            outpt = firstNum - secNum;
                            System.out.println("The answer is: " + outpt);
                            ans = (eq + outpt);
                            data.add(ans);
                            break;
                        }
                        case "*": {
                            outpt = firstNum * secNum;
                            System.out.println("The answer is: " + outpt);
                            ans = (eq + outpt);
                            data.add(ans);
                            break;
                        }
                        case "/": {
                            if (secNum == 0) {
                                System.out.println("Invalid expression cannot divide by zero");
                                // choiceLoop(kybd, s, data, myChoice);
                            }
                            outpt = (Double) firstNum / secNum;
                            System.out.println("The answer is: " + outpt);
                            ans = (eq + outpt);
                            data.add(ans);
                            break;
                        }
                        case "^": {
                            if (firstNum < 0 && secNum == 0.5) {
                                System.out
                                        .println(
                                                "Invalid expression cannot take the square root of a negative number");
                                // choiceLoop(kybd, s, data, myChoice);
                            }
                            outpt = (Double) Math.pow(firstNum, secNum);
                            System.out.println("The answer is: " + outpt);
                            ans = (eq + outpt);
                            data.add(ans);
                            break;
                        }
                        default: {
                            System.out.println("Invalid expression invalid operator");
                            loop(data, dis, dout);
                            // choiceLoop(kybd, s, data, myChoice);
                            return;
                        }
                    }
                } else if (isNum1 == true && isNum2 == false) {
                    switch (oper) {
                        case "+": {
                            outptCase2 = firstNum.toString() + " + " + num2 + " = ?";
                            data.add(outptCase2);
                            break;
                        }
                        case "-": {
                            outptCase2 = firstNum.toString() + " - " + num2 + " = ?";
                            data.add(outptCase2);
                            break;
                        }
                        case "*": {
                            outptCase2 = firstNum.toString() + " * " + num2 + " = ?";
                            data.add(outptCase2);
                            break;
                        }
                        case "/": {
                            outptCase2 = firstNum.toString() + " / " + num2 + " = ?";
                            data.add(outptCase2);
                            break;
                        }
                        case "^": {
                            outptCase2 = firstNum.toString() + " ^ " + num2 + " = ?";
                            data.add(outptCase2);
                            break;
                        }
                        default: {
                            System.out.println("Invalid expression invalid operator");
                            loop(data, dis, dout);
                            // choiceLoop(kybd, s, data, myChoice);
                            return;
                        }
                    }
                } else if (isNum1 == false && isNum2 == true) {
                    switch (oper) {
                        case "+": {
                            outptCase3 = x + " + " + secNum.toString() + " = ?";
                            data.add(outptCase3);
                            break;
                        }
                        case "-": {
                            outptCase3 = x + " - " + secNum.toString() + " = ?";
                            data.add(outptCase3);
                            break;
                        }
                        case "*": {
                            outptCase3 = x + " * " + secNum.toString() + " = ?";
                            data.add(outptCase3);
                            break;
                        }
                        case "/": {
                            outptCase3 = x + " / " + secNum.toString() + " = ?";
                            data.add(outptCase3);
                            break;
                        }
                        case "^": {
                            outptCase3 = x + " ^ " + secNum.toString() + " = ?";
                            data.add(outptCase3);
                            break;
                        }
                        default: {
                            System.out.println("Invalid expression invalid operator");
                            loop(data, dis, dout);
                            // choiceLoop(kybd, s, data, myChoice);
                            return;
                        }
                    }
                } else if (isNum1 == false && isNum2 == false) {
                    switch (oper) {
                        case "+": {
                            outptCase4 = x + " + " + num2 + " = ?";
                            data.add(outptCase4);
                            break;
                        }
                        case "-": {
                            outptCase4 = x + " - " + num2 + " = ?";
                            data.add(outptCase4);
                            break;
                        }
                        case "*": {
                            outptCase4 = x + " * " + num2 + " = ?";
                            data.add(outptCase4);
                            break;
                        }
                        case "/": {
                            outptCase4 = x + " / " + num2 + " = ?";
                            data.add(outptCase4);
                            break;
                        }
                        case "^": {
                            outptCase4 = x + " ^ " + num2 + " = ?";
                            data.add(outptCase4);
                            break;
                        }
                        default: {
                            System.out.println("Invalid expression invalid operator");
                            loop(data, dis, dout);
                            // choiceLoop(kybd, s, data, myChoice);
                            return;
                        }
                    }
                }
            } catch (IOException e) {
            }
        }
    }
}
