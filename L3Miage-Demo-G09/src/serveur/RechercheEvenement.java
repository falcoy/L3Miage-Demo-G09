package serveur;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import Module_Evenement.Agenda;
import Module_Evenement.Evenement;
import Structure_Contact.Contact;

public class RechercheEvenement extends UnicastRemoteObject implements _RechercheEvenement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Agenda evenements;

	public RechercheEvenement() throws RemoteException {
		super();
		evenements = new Agenda();

		try {
			// analyse du document
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			// récupération de la structure objet du document
			Document document = docBuilder.parse("src/serveur/event.xml");

			for (int i = 0; i < document.getElementsByTagName("evenement").getLength(); i++) {
				String nom = document.getElementsByTagName("nom").item(i).getTextContent();
				String lieu = document.getElementsByTagName("lieu").item(i).getTextContent();
				int date = Integer.parseInt(document.getElementsByTagName("date").item(i).getTextContent());

				this.evenements.ajouterEvenement(new Evenement(nom, lieu, date));
			}
		} catch (Exception ex) {
		}
	}

	@Override
	public List<Evenement> getEvenements() throws RemoteException {
		return evenements.getEvenements();
	}

	public Agenda getAgenda() {
		return this.evenements;
	}
}
