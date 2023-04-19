import java.io.*;
import java.util.*;
import java.net.*;

class example extends Thread
{
    Socket s;
    DataInputStream din;
    DataOutputStream ps;
    public example(Socket st)
    {
        s=st;
    }
    public void run()
    {
        try
        {
                //System.out.println("entered");
            din=new DataInputStream(s.getInputStream());
            ps=new DataOutputStream(s.getOutputStream());
            while(s.isConnected())
            {
                if(din.readUTF().equals("1"))
                        {
                            String fileName = din.readUTF();
                            //System.out.println(""+fileName);
                            File file= new File("C:\\Users\\q\\Downloads\\CN_Project2_files\\"+fileName);
                            if(file.exists())
                            {
                                //System.out.println("exists");
                                ps.writeUTF("1");

                                FileInputStream fin= new FileInputStream(file);

                                int ch=-1;
                                byte arr[]=new byte[1000];
                                while((ch=fin.read(arr))>-1)
                                {
                                    ps.write(arr,0,ch);
                                }
                                //System.out.println("wrote file");
                                fin.close();
                            }
                            else
                            {
                                ps.writeUTF("0");
                            }
                            //ps.flush();
                        }
                        else
                        {
                            String fileName = din.readUTF();
                            //System.out.println("got filename to upload data in server"+" "+fileName);
                            byte buffer[] = new byte[1000];
                            String path= "C:\\Users\\q\\Downloads\\CN_Project2_files\\";
                            //System.out.println("creating new File");
                            File myFile = new File(path+"new"+fileName);

                            FileOutputStream fout = new FileOutputStream(myFile);
                            int valid;
                            while((valid = din.read(buffer))>-1)
                            {
                                fout.write(buffer,0,valid);
                                if(valid<1000)
                                    break;
                            }
                            fout.close();
                        }
                //ps.flush();
            }
            ps.close();
            din.close();
        }
        catch(Exception e)
        {
            System.out.println("client disconnected");
        }
    }
}
class Server {

	public static void main(String args[])
		throws Exception
	{
            ServerSocket ss = new ServerSocket(5106);
            while(true)
            {
                Socket s = ss.accept();
                //System.out.println("connected");
                example Thread1 = new example(s);
                //System.out.println("thread started");
                Thread1.start();
            }
	}
}
