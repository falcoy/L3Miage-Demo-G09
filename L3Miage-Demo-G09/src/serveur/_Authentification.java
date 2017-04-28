package serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface _Authentification extends Remote {
	
	public boolean reco(String login, String mdp)throws RemoteException;

}
