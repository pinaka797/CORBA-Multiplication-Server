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
/**
 *
 * @author ajit
 *
 */
public class StartServer {
    public static void main(String args[]){
    try{
    ORB orb=ORB.init(args, null);
    
    POA rootpoa=POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
    rootpoa.the_POAManager().activate();
    
    //UpdateObj upobj=new UpdateObj();
    //upobj.setORB(orb);
    //org.omg.CORBA.Object obj=rootpoa.servant_to_reference(upobj);
    //Update href1=UpdateHelper.narrow(obj);
    
    AdditionObj addobj=new AdditionObj();
    addobj.setORB(orb);
    org.omg.CORBA.Object ref=rootpoa.servant_to_reference(addobj);
    Addition href=AdditionHelper.narrow(ref);
    
    
    
    org.omg.CORBA.Object objref=orb.resolve_initial_references("NameService");
    NamingContextExt ncref=NamingContextExtHelper.narrow(objref);
    NameComponent path[]=ncref.to_name("ABC");//NameComponent path1[]=ncref.to_name("DEF");
    ncref.rebind(path, href);//ncref.rebind(path1, href1);
    System.out.println("Multiplication Server ready and waiting.....");
    for(;;)
        orb.run();
    }
    catch(Exception e){
    System.err.println("Error:"+e);
    e.printStackTrace(System.out);
    }
    System.out.println("Server exiting now....");
    }
    
}
