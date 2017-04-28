package serveur;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Module_Securite.Authentification;

public class Athentification extends UnicastRemoteObject implements _Authentification  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Authentification auth;
	
	public Athentification(String identifiant, String motDePasse) throws Exception {
		auth = new Authentification(identifiant, motDePasse);
	}

	@Override
	public boolean reco(String identifiant, String mdp) throws RemoteException{
		this.auth.setCheminXML( new File ("src/serveur/XML.xml")); 
		return auth.reconnaissance(identifiant, mdp);
	}
}
