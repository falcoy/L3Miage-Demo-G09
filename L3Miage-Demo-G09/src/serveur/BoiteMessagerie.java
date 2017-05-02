package serveur;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import FrameworkExceptions.FrameworkException;
import Module_Messagerie.MessageTexte;
import Module_Messagerie.SalonDiscussion;
import Structure_Contact.Contact;

public class BoiteMessagerie extends SalonDiscussion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Contact proprietaire;

	public BoiteMessagerie(String nom, Contact proprio) throws RemoteException {
		super(nom);
		this.proprietaire = proprio;
		// TODO Auto-generated constructor stub
	}

	public void Save() {

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder p = dbFactory.newDocumentBuilder();
			Document doc = p.parse("XML.xml");

			for (int i = 0; i < doc.getElementsByTagName("user").getLength(); i++) {
				NodeList userCourant = doc.getElementsByTagName("user").item(i).getChildNodes();
				if (userCourant.item(7).getTextContent() == this.proprietaire.getNom()) {

					for (int index = userCourant.getLength() - 1; index < this.getMessages().size(); index++) {
						Element message = (Element) doc.createElement("message");
						Element destinataire = (Element) doc.createElement("destinataire");
						Element contenu = (Element) doc.createElement("contenu");
						Element date = (Element) doc.createElement("date");

						destinataire.setTextContent(this.getMessages().get(index).getDestinataire().getNom());
						date.setTextContent(this.getMessages().get(index).getDate());
						contenu.setTextContent(this.getMessages().get(index).getcontenu());

						message.appendChild(destinataire);
						message.appendChild(date);
						message.appendChild(contenu);
						
						userCourant.item(13).appendChild(message);
					}

				}
			}

			final TransformerFactory transformerFactory = TransformerFactory.newInstance();
			final Transformer transformer = transformerFactory.newTransformer();
			final DOMSource source = new DOMSource(doc);
			final StreamResult sortie = new StreamResult("XML.xml");

			transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");

			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

			transformer.transform(source, sortie);

		} catch (

		ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void load() {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder p = dbFactory.newDocumentBuilder();
			Document doc = p.parse("./src/XML.xml");

			for (int i = 0; i < doc.getElementsByTagName("user").getLength(); i++) {

				NodeList elCourant = doc.getElementsByTagName("user").item(i).getChildNodes();

				if (elCourant.item(7).getTextContent().equals( this.proprietaire.getNom())) {
					NodeList messagesBoite = elCourant.item(13).getChildNodes();
					for (int index = 0; index < messagesBoite.getLength()/2; index++) {
						NodeList message = messagesBoite.item(index+index+1).getChildNodes();
						Contact expediteur = null;
						String date;
						String contenu;
						for (int indice = 0; indice < this.getcontacts().size(); indice++) {
							if (this.getcontacts().get(indice).getNom().equals( message.item(1).getTextContent())) {
								expediteur = this.getcontacts().get(indice);
							} else {
								expediteur = new Contact(message.item(1).getTextContent(), null,
										"inconnu@inconnu.com");
								//throw new FrameworkException("expediteur inconnu");
							}
						}
						date = message.item(3).getTextContent();
						contenu = message.item(5).getTextContent();
						MessageTexte msg = new MessageTexte(expediteur, this.proprietaire, contenu);

						msg.setDate(date);
						this.getMessages().add(msg);
					}
				}

			}

		} catch (SAXException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void envoyerUnMessage(MessageTexte msg, Contact contact) {

	}
	

}
