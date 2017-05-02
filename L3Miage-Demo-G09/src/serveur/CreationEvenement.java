package serveur;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


import Module_Evenement.Evenement;

public class CreationEvenement extends UnicastRemoteObject implements _CreationEvenement {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Module_Evenement.Evenement evenement;
	
	public CreationEvenement() throws RemoteException {
		this.evenement = new Evenement(null, null, 0, null);
	}

	@Override
	public void creationEvenement(Module_Evenement.Evenement evenement, File path) throws RemoteException {
		this.evenement.creationEvt(evenement, path);
	}

}
