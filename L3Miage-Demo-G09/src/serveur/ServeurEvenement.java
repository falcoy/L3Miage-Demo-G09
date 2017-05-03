package serveur;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Module_Annuaire.Annuaire;
import Structure_Contact.Contact;

public class ServeurEvenement {

	public static void main(String[] args) throws Exception {

		try {
			Registry reg = LocateRegistry.createRegistry(5555);
			System.setProperty("java.security.policy", "file:./security.policy");

			Athentification auth = new Athentification(null,null);
			String url_1 = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/Authentification";
			System.out.println("Enregistrement de l'objet avec l'url : " + url_1);
			reg.rebind(url_1, auth);

			RechercheEvenement rechercheEvenement = new RechercheEvenement();
			String url_2 = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/RechercheEvenement";
			System.out.println("Enregistrement de l'objet avec l'url : " + url_2);
			reg.rebind(url_2, rechercheEvenement);
			
			Annuaire ann= new Annuaire();
			ann.ajouterContact(new Contact("juntir", null, "juntir@inc.com","juntir1"));
			ann.ajouterContact(new Contact("raj", null, "raj@inc.com","raj26"));			
			ann.ajouterContact(new Contact("jerome", null, "jerome@inc.com","jeromeF"));
			
			Messagerie messagerieEvenement = new Messagerie(ann);
			String url_3 = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/MessagerieEvenement";
			System.out.println("Enregistrement de l'objet avec l'url : " + url_3);
			reg.rebind(url_3, messagerieEvenement);
			
			CreationEvenement creationEvenement = new CreationEvenement();
			String url_4 = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/CreationEvenement";
			System.out.println("Enregistrement de l'objet avec l'url : " + url_4);
			reg.rebind(url_4, creationEvenement);

			System.out.println("Serveur lance");
		} catch (RemoteException e) {
			e.printStackTrace();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
