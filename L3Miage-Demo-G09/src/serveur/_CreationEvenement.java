package serveur;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

import Module_Evenement.Evenement;

public interface _CreationEvenement extends Remote {
	
	public void creationEvenement(Module_Evenement.Evenement evenement, File path) throws RemoteException;
}
