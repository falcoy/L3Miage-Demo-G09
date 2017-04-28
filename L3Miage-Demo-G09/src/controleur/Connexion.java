package controleur;

import java.rmi.RemoteException;

import serveur._Authentification;

public class Connexion {
	
	private _Authentification authentfication;
	
	public Connexion(_Authentification auth)
	{
		this.authentfication = auth;
	}
	public boolean connexion(String login, String motDePasse)
	{		
		try {
			return this.authentfication.reco(login,motDePasse);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}
	

	
}
