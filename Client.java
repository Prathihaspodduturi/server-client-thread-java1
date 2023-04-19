import java.io.*;
import java.net.*;
import java.util.*;

class Client {

	public static void main(String args[])
		throws Exception
	{
		BufferedReader keyboard
			= new BufferedReader(
				new InputStreamReader(System.in));

		String portCommand=keyboard.readLine();
		String str2[]=portCommand.split(" ");
		int port = Integer.parseInt(str2[1]);

		try
		{
			Socket s = new Socket("localhost", port);
            //System.out.println("connected");
			DataOutputStream dos
				= new DataOutputStream(
					s.getOutputStream());

			DataInputStream din=new DataInputStream(s.getInputStream());

			while(true)
			{
                //System.out.println("entered loop");
				String fileCommand=keyboard.readLine();
				str2=fileCommand.split(" ");
                if(str2[0].equals("exit"))
                    break;
				if (str2[0].equals("get")) 
				{
                    //System.out.println("entered get");
					dos.writeUTF("1");
					String fileName = str2[1].substring(0,str2[1].length());
					dos.writeUTF(fileName);
					if(din.readUTF().equals("1"))
					{
						byte buffer[] = new byte[1000];
						String path= "C:\\Users\\q\\Downloads\\CN_Project2_files\\";
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
					else
					{
						System.out.println("file does no exist : enter correct filename");
					}
				}
				else
				{
					if(str2[0].equals("upload"))
					{
                        //System.out.println("entered upload");
                        //System.out.println("after");
						String fileName = str2[1].substring(0,str2[1].length());
						File file= new File("C:\\Users\\q\\Downloads\\CN_Project2_files\\"+fileName);
						if(file.exists())
						{
                            //System.out.println("entering data to file");
							dos.writeUTF("0");
                        	//System.out.println("sent data to server");
                        	//System.out.println("recieved data from server"+fileName);
							dos.writeUTF(fileName);
							FileInputStream fin= new FileInputStream(file);
							int ch;
							byte arr[]=new byte[1000];
							while((ch=fin.read(arr))>-1)
							{
								dos.write(arr,0,ch);
							}
                            //System.out.println("completed entering data into file");
							fin.close();
                            //System.out.println("end of upload");
						}
						else
						{
							System.out.println("file does not exist : enter correct filename");
						}
					}
					else
					{
						System.out.println("Entered wrng command : enter correct command");
					}
                    //System.out.println("end of loop");
				}
				dos.flush();
			}
			dos.close();
			keyboard.close();
			s.close();
		}
		catch(Exception e)
		{
            System.out.println(""+e);
			//System.out.println("entered wrong port number: please restart and enter correct port number");
		}
	}
}
