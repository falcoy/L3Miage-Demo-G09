package serveur;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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

			System.out.println("Serveur lancee");
		} catch (RemoteException e) {
			e.printStackTrace();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
