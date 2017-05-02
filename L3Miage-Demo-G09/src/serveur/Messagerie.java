package serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import FrameworkExceptions.FrameworkException;
import Module_Annuaire.Annuaire;
import Module_Messagerie.MessageTexte;
import Structure_Contact.Contact;

public class Messagerie extends UnicastRemoteObject implements _Messagerie {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Annuaire annuaire;

	public Messagerie(Annuaire annuaire) throws RemoteException {
		this.annuaire = annuaire;
	}

	@Override
	public List<MessageTexte> consulterMessagerie(Contact proprio) throws RemoteException, FrameworkException {

		for (int i = 0; i < this.annuaire.getContacts().size(); i++) {
			if (this.annuaire.getContacts().get(i).getNom().equals(proprio.getNom())) {
				BoiteMessagerie btMsg = new BoiteMessagerie("boite " + proprio.getNom(), proprio);
				btMsg.setAnnuaire(this.annuaire);
				btMsg.load();
				return btMsg.getMessages();
			}
		}
		throw new FrameworkException("contact inconnu");

	}

}
