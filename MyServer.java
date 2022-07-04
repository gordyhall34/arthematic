import java.io.*;  
import java.net.*;  
public class MyServer {  
public static void main(String[] args)throws Exception{   
ServerSocket ss=new ServerSocket(6666);  
Socket s=ss.accept();//establishes connection   
DataInputStream dis=new DataInputStream(s.getInputStream());  
//String  str=(String)dis.readUTF();
while(true)
{  
    try {
        String  str= "null";
        str = (String)dis.readUTF();
        if(str == "")
        {
            break;
        }
        System.out.println(str);  
        ss.close();
    }catch  (EOFException e){
        System.out.println("End of list");
        break;
    }catch (IOException e) {}
}   
}
}  