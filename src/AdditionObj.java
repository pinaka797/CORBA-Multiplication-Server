/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import AdditionApp.*;
//import UpdateApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ajit
 */
public class AdditionObj extends AdditionPOA{
    private ORB orb;int r;boolean status=false;
    
    public void setORB(ORB orbval){
    orb=orbval;
    }
    
    public int add(int a,int b){
    r=a*b;status=true;
    
    return r;
    
    }
    public String otherClientStatus(){
        String s="";
        if(status==true){
        s="The other client has initiated a multiplication operation and the result is:"+r;
        }
        status=false;
    return s;
    }
    public void shutdown(){
    orb.shutdown(false);
    }
    
}
