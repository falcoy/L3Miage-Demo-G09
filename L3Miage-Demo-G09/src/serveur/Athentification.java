package serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Module_Securite.Authentification;

public class Athentification extends UnicastRemoteObject implements _Authentification  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Authentification auth;
	
	public Athentification(String login, String motDePasse) throws Exception {
		auth = new Authentification(login, motDePasse);
	}

	@Override
	public boolean reco(String login, String mdp) throws RemoteException{
		return auth.reconnaissance(login, mdp);
	}
}
