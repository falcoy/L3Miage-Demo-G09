package serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import Module_Evenement.Agenda;
import Module_Evenement.Evenement;

public class RechercheEvenement extends UnicastRemoteObject implements _RechercheEvenement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Agenda evenements;

	protected RechercheEvenement() throws RemoteException {
		super();
		evenements = new Agenda();
	}

	@Override
	public List<Evenement> getEvenements() throws RemoteException {
		return evenements.getEvenements();
	}

}
