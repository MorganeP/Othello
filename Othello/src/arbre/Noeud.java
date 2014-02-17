package arbre;

import enumeration.Case;
import modele.Partie;

public class Noeud {

	int id;
	Partie partie;
	
	public Noeud(int i,Partie p){
		id = i;
		partie = p;
	}
	
	
	public int getId() {
		return id;
	}

	public Partie getPartie() {
		return partie;
	}

	
}
