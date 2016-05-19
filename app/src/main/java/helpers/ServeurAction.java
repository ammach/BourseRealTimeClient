package helpers;

import android.os.Message;

import com.example.ammach.bourserealtime.ActionsActivity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by ammach on 5/4/2016.
 */
public class ServeurAction extends Thread{
    ServerSocket reception;
    static final int port=40000;

    public ServeurAction()
    { try
    { reception=new ServerSocket(port);
        System.out.println("Le serveur est en écoute sur le "+port);
    }
    catch(IOException e) { System.exit(1); }
        this.start();
    }

    public void run()
    { Socket sock;
        String text;

        try
        {
            System.out.println("votre adresse est  "+ InetAddress.getLocalHost().getHostName());
            while(true)
            {
                System.out.println("Le serveur est en attente ");
                sock=reception.accept();
                System.out.println("Le serveur a accepté la connexion avec "+sock.getInetAddress());
                ObjectInputStream objectInputStream =new ObjectInputStream(sock.getInputStream());
                ArrayList<Object> actions= (ArrayList<Object>) objectInputStream.readObject();
                //envoi du msg au handler des actions
                Message msg=Message.obtain();
                msg.obj=actions;
                ActionsActivity.handler.sendMessage(msg);
                sock.close();
            }
        }
        catch(IOException e) { } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
