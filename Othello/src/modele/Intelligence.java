package modele;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enumeration.Case;
import enumeration.Joueur;
import arbre.Arbre;
import arbre.Noeud;

public class Intelligence {

	List<int[]> coupsPossiblesBlanc;
	List<int[]> coupsPossiblesNoir;
	Partie partie;
	Arbre arbre;


	public Intelligence(Partie p){
		coupsPossiblesBlanc = coupsPossibles(p, Joueur.BLANC);
		coupsPossiblesNoir = coupsPossibles(p, Joueur.NOIR);
	}


	public void miseAJourPartie(Partie p){
		partie = p;
	}


	public List<int[]> coupsPossibles(Partie p, Joueur couleur) {
		partie = p;
		Case[][] jeu = partie.getJeu();
		System.out.println(partie.jeuToString());
		List<int[]> reponse = new ArrayList<int[]>();
		for(int i=0; i<8; i++){
			for(int j=0;j<8; j++){
				if (jeu[i][j].equals(Case.VIDE)){		// tester  toutes les cases vides
					if (peutJouer(i,j,couleur)){
						int[] tab = {i,j};				// CORRECT ?????
						reponse.add(tab);
					}
				}
			}
		}
		return reponse;
	}


	public boolean capturePossible(int i, int j, int dx, int dy, Joueur joueur, Joueur adversaire) {
		//		System.out.println("capturePossible   i="+i+"  j="+j+ "  dx=" + dx +"  dy="+dy);
		Case[][] jeu = partie.getJeu();
		Boolean  reponse = false;          
		int x = i  +  dx;
		int y = j  +  dy;
		Boolean  adversaireRencontre  = false;      // si on a sauté au moins un pion adverse
		while ( (x>=0) && (x<=7) && (y>=0) && (y<=7)) { 	    // on n’atteint pas le bord 
			//			System.out.println("Jeu[" + x + "][" + y + "] = "+ jeu[x][y]);

			//  case vide => impossible
			if (jeu[x][y].equals(Case.VIDE)){
				reponse = false;          			
				break;
			}
			// adversaire =>  continuer
			else if (   ( (jeu[x][y].equals(Case.BLANC)) && adversaire.equals(Joueur.BLANC) )
					|| ( (jeu[x][y].equals(Case.NOIR)) && adversaire.equals(Joueur.NOIR) ) ){
				//				System.out.println("adversaire");
				x  =   x + dx;       
				y  =   y + dy;       
				adversaireRencontre = true;
			}
			// pion du joueur
			else if (   ( (jeu[x][y].equals(Case.BLANC)) && joueur.equals(Joueur.BLANC) )
					|| ( (jeu[x][y].equals(Case.NOIR)) && joueur.equals(Joueur.NOIR) ) ){
				//				System.out.println("Joueur");
				if  (adversaireRencontre) {
					reponse = true;
					break;
				}
				else   {			// deux pions du joueur consécutifs, on n'a pas croisé de pions adversaires entre
					reponse = false;
					break;
				}
			}
		}
		return reponse;
	}


	public boolean peutJouer(int i, int j, Joueur joueur) {
		System.out.println("Intelligence . peutJoueur(i,j)" + i + "-" + j + "    joueur : "+ joueur.toString());
		Boolean reponse  = false;
		if ( (partie.getPionsDisponibles() > 0 )  &&  (i>=0) && (i<=7) && (j>=0) && (j<=7) ) { 	 // si il reste des pions et on ne sort pas du damier
			Joueur adv = null;
			if(joueur.equals(Joueur.BLANC)){
				adv = Joueur.NOIR;
			}
			else if(joueur.equals(Joueur.NOIR)){
				adv = Joueur.BLANC;
			}
			if ( 	capturePossible ( i ,  j ,  -1 , 0 , joueur, adv )	//  capture vers la gauche
					||	capturePossible ( i ,  j ,  1 , 0 , joueur, adv )	//  capture vers la droite
					||	capturePossible ( i ,  j ,  0 , -1 , joueur, adv )	//  capture vers le haut
					||	capturePossible ( i ,  j ,  0 , 1 , joueur, adv )	//  capture vers le bas
					||	capturePossible ( i ,  j ,  -1 , -1 , joueur, adv )	//  capture vers la diagonale haut-gauche
					||	capturePossible ( i ,  j ,  -1 , 1 , joueur, adv )	//  capture vers la diagonale haut-droite
					||	capturePossible ( i ,  j ,  1 , -1 , joueur, adv )	//  capture vers la diagonale bas-gauche
					||	capturePossible ( i ,  j ,  1 , 1 , joueur, adv )	//  capture vers la diagonale bas-droite
					){  reponse = true;
			}
		}
		System.out.println("Intelligence.peutJouer : reponse="+reponse);
		return reponse;
	}


	public Boolean fin(){
		if ( coupsPossiblesBlanc.size()==0 && coupsPossiblesNoir.size()==0 ){
			return true;
		}
		else return false;
	}




	//========================================================================================================
	//=====================                  ARBRES                   ========================================
	//========================================================================================================


	private void creerArbre(int profondeur){
		
		Partie partieRacine = partie;
		Noeud noeudRacine = new Noeud(0, partieRacine);
		arbre = new Arbre(noeudRacine);
		Map<Integer,Noeud> hashmapEtagesNoeuds = new HashMap<Integer,Noeud>();

		// etage 1 : coups du joueur courant
		for(int[] coup : coupsPossibles(partie,partie.getJoueurCourant())){
			Partie partieSuivante = partieRacine;
			partieSuivante.jouer(coup[0],coup[1]);
			Noeud nouveauNoeud = new Noeud(arbre.getNombreNoeuds(),partieSuivante);
			arbre.ajouterFils(nouveauNoeud);
			//			hashmapEtagesNoeuds.put(1, noeud);
		}

		// étages suivants
		for (int p=2;p<profondeur;p++){
			System.out.println("\n-------------------------------------------\nETAGE "+p);
			Map<Integer,Noeud> noeudsFeuilles = arbre.getNoeudsFeuille();
			for(int i : noeudsFeuilles.keySet()){
				Noeud noeudFeuille = noeudsFeuilles.get(i);
				Partie partieNoeudFeuille = noeudFeuille.getPartie();
				Joueur joueurCourantNoeud = partieNoeudFeuille.getJoueurCourant();
				
				for(int[] coup : coupsPossibles(partieNoeudFeuille,joueurCourantNoeud)){
					Partie partieSuivante = partieNoeudFeuille;
					partieSuivante.jouer(coup[0],coup[1]);
					Noeud nouveauNoeud = new Noeud(arbre.getNombreNoeuds(),partieSuivante);
					arbre.ajouterNoeud(noeudFeuille, nouveauNoeud);
					
					System.out.println(noeudFeuille.getId() +" ==> "+nouveauNoeud.getId());
				}
				
				
			}
		}
	}

}
