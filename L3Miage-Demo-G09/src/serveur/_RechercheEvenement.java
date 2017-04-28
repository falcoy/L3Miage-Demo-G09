package serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import Module_Evenement.Evenement;

public interface _RechercheEvenement extends Remote {

	public List<Evenement> getEvenements() throws RemoteException;
}
