package serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import Module_Messagerie.MessageTexte;
import Structure_Contact.Contact;

public interface _Messagerie extends Remote{
	
	public List<MessageTexte> consulterMessagerie(Contact proprio)throws RemoteException, Exception;
	public void envoiMsg(Contact proprio, String dest, String msg);
	public Contact getProprio(String proprio);

}
