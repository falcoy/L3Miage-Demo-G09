package controleur;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import GUI.*;
import Module_Messagerie._SalonDiscussion;
import serveur._Authentification;

public class Demarrer {

	public static void main(String[] args) throws Exception {

		String nomServeur = "127.0.0.2";
		Connexion connexion;

		Registry reg = LocateRegistry.getRegistry(nomServeur, 5555);

		System.setProperty("java.security.policy", "file:./security.policy");

		Remote remote = reg.lookup("rmi://" + nomServeur + "/Authentification");

		if (remote instanceof _Authentification) {
			connexion = new Connexion((_Authentification) remote);
			PageAuthentification pageAuth = new PageAuthentification(connexion);
		}

	}

}
