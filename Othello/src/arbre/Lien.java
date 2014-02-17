package arbre;

public class Lien {
	
	Arbre arbre;
	
	Noeud from;
	Noeud to;
	int idFrom;
	int idTo;
	
	public Lien(Noeud n1, Noeud n2){
		from = n1;
		to = n2;
		idFrom = n1.getId();
		idTo = n2.getId();
	}
	
	public Noeud getNoeudFrom() {
		return from;
	}
	
	public int getIdFrom() {
		return idFrom;
	}
	
	public Noeud getNoeudTo() {
		return to;
	}
	
	public int getIdTo() {
		return idTo;
	}
	

	

}
