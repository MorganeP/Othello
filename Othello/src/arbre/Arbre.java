package arbre;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Arbre {
	//liste avec pour chaque élèment son id qui va servir de Key
	HashMap<Integer,Noeud> mapNoeuds;
	List<Lien> liens;
	// noeud racine
	Noeud racine;
	
	//création de l'arbre en précisant le noeud racine
	public Arbre(Noeud racine){
		this.racine=racine;
		this.mapNoeuds=new HashMap<Integer,Noeud>();
		this.liens=new ArrayList<Lien>();
		mapNoeuds.put(racine.getId(),racine);
	}
	//ajoute un fils à la racine de l'arbre
	public void ajouterFils(Noeud fils){
		mapNoeuds.put(fils.getId(),fils);
		Lien lien=new Lien(racine,fils);
		liens.add(lien);
	}
	
	public void ajouterNoeud(Noeud pere, Noeud fils) {
		mapNoeuds.put(fils.getId(),fils);
		Lien lien=new Lien(pere,fils);
		liens.add(lien);
	}
	
	public HashMap<Integer,Noeud> getNoeudsFeuille(){
		HashMap<Integer,Noeud> noeuds_feuilles=new HashMap<Integer,Noeud>();
		
		for(int i=0;i<mapNoeuds.size();i++){
			if(isNoeudFeuille(mapNoeuds.get(i))){
				noeuds_feuilles.put(i, mapNoeuds.get(i));
			}
		}
		return noeuds_feuilles;
	}
	
	
	
	public Noeud getNoeud(int i){
		return mapNoeuds.get(i);
	}
	
	public void addLien(Lien l) {
		liens.add(l);
	}
	
	public Lien getLien(Noeud n1, Noeud n2){
		Lien reponse = null;
		for (Lien lien : liens){
			if((lien.getNoeudFrom().equals(n1)) && (lien.getNoeudTo().equals(n2)) ){
				reponse = lien;
			}
		}
		return reponse;
	}
	
	public Lien getLien(int i1, int i2){
		Lien reponse = null;
		for (Lien lien : liens){
			if((lien.getIdFrom()==i1) && (lien.getIdTo()==i2) ){
				reponse = lien;
			}
		}
		return reponse;
	}
	
	public List<Lien> getLiens() {
		return liens;
	}
	
	//méthode getFils
	public List getFils(int i){
			List fils=new ArrayList<Noeud>();
			for (Lien lien : liens){
				if((lien.getIdFrom()==i) ){
					fils.add(lien.from);
				}
		
		}
			return fils;	
	}
	//renvoie le nombre de fils pour un id donné
	public int nbFils(int i){
		int nbFils=0;
	for (Lien lien : liens){
			if((lien.getIdFrom()==i) ){
				nbFils++;
			}
	
	}
	return nbFils;
	}
	
	//indique si le noeud a des fils ou pas
	public boolean isNoeudFeuille(Noeud a){
		boolean b=true;
		for (Lien lien : liens){
			if((lien.getIdFrom()==a.id) ){
				b=false;
			}
	
	}
		
		return b;
	}
	
	public int getNombreNoeuds(){
		return mapNoeuds.size();
	}
	
}
