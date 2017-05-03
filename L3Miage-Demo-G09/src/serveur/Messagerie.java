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

	public void envoiMsg(Contact proprio, String dest, String msg) {
		Contact desti = null;
		try {
			for (int i = 0; i < this.annuaire.getContacts().size(); i++) {
				if (this.annuaire.getContacts().get(i).getNom().equals(dest)) {
					desti = this.annuaire.getContacts().get(i);
					BoiteMessagerie btMsg = new BoiteMessagerie("boite" + desti.getNom(), desti);
					btMsg.envoyerMessage(new MessageTexte(proprio, desti, msg));
					btMsg.SaveMsg();
				}
			}

			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public Contact getProprio(String proprio) throws RemoteException
	{
		for (int i = 0; i < this.annuaire.getContacts().size(); i++) {
			if (this.annuaire.getContacts().get(i).getLogin().equals(proprio)) {
				return this.annuaire.getContacts().get(i);
			}
		}
		throw new FrameworkException("propriétaire inconnu2");
	}

}
