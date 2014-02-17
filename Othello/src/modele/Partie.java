package modele;

import controleur.Controleur;
import enumeration.Case;
import enumeration.Etat;
import enumeration.Joueur;
import enumeration.ModeJeu;


public class Partie {

	Controleur controleur;
	Intelligence intelligence;
	public Case[][] jeu;

	Etat etatPartie;
	int pionsDisponibles;
	int scoreBlanc;
	int scoreNoir;
	int nbCoupsJoues;

	Joueur joueurCourant;
	Joueur adversaire;

	public Partie(Controleur c){
		controleur = c;
		initialisation();
		intelligence = new Intelligence(this);
	}

	public void initialisation(){

		System.out.println("INITIALISATION PARTIE ...");
		jeu = new Case[8][8];

		etatPartie = Etat.ENCOURS;
		pionsDisponibles = 60;
		scoreBlanc = 2;
		scoreNoir = 2;

		joueurCourant = Joueur.NOIR;
		adversaire = Joueur.BLANC;

		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){
				jeu[i][j] = Case.VIDE;
			}
		}
		jeu[3][3] = Case.BLANC;
		jeu[3][4] = Case.NOIR;
		jeu[4][3] = Case.NOIR;
		jeu[4][4] = Case.BLANC;
	}

	public void jouerMachine(){
		
	}

	//verifie si le coup est possible
	public void jouer(int i, int j){

		if (intelligence.peutJouer(i,j,joueurCourant)){ 
			if (intelligence.capturePossible ( i ,  j ,  -1 , 0 , joueurCourant, adversaire )){
				capturer ( i ,  j ,  -1 , 0 );
				System.out.println("capturer direction 1");
			}
			if (intelligence.capturePossible ( i ,  j ,  1 , 0 , joueurCourant, adversaire  ))	{     capturer ( i ,  j ,  1 , 0 );
			System.out.println("capturer direction 2");
			}	
			if (intelligence.capturePossible ( i ,  j ,  0 , -1 , joueurCourant, adversaire  ))	{     capturer ( i ,  j ,  0 , -1 );
			System.out.println("capturer direction 3");
			}	
			if (intelligence.capturePossible ( i ,  j ,  0 , 1 , joueurCourant, adversaire  ))	{     capturer ( i ,  j ,  0 , 1 );
			System.out.println("capturer direction 4");
			}
			if (intelligence.capturePossible ( i ,  j ,  -1 , -1 , joueurCourant, adversaire  ))  {     capturer ( i ,  j ,  -1 , -1 );
			System.out.println("capturer direction 5");
			}
			if (intelligence.capturePossible ( i ,  j ,  -1 , 1 , joueurCourant, adversaire  ))	{     capturer ( i ,  j ,  -1 , 1 );
			System.out.println("capturer direction 6");
			}
			if (intelligence.capturePossible ( i ,  j ,  1 , -1 , joueurCourant, adversaire  ))	{     capturer ( i ,  j ,  1 , -1 );
			System.out.println("capturer direction 7");
			}
			if (intelligence.capturePossible ( i ,  j ,  1 , 1 , joueurCourant, adversaire  ))	{     capturer ( i ,  j ,  1 , 1 );
			System.out.println("capturer direction 8");
			}
			System.out.println("Maj variables");

			// Mise à jour des variables
			if(joueurCourant.equals(Joueur.BLANC)){
				jeu[i][j] = Case.BLANC;
				joueurCourant = Joueur.NOIR;
				adversaire = Joueur.BLANC;
			}
			else if(joueurCourant.equals(Joueur.NOIR)){
				jeu[i][j] = Case.NOIR;
				joueurCourant = Joueur.BLANC;
				adversaire = Joueur.NOIR;
			}
			pionsDisponibles  = pionsDisponibles -  1;
			System.out.println("Peut jouer =>  partie modifiée");
		}
	}


	public void capturer(int i, int j, int dx, int dy) {
		System.out.println("Intelligence . Capturer(" + i + "," + j +")");
		int x = i + dx;
		int y = j + dy;
		while ( (x>=0) && (x<=7) && (x>=0) && (x<=7)) { 	    // on n’atteint pas le bord 
			System.out.println("x="+x +"  y="+y);
			System.out.println(jeu[x][y]);
			if (   ( (jeu[x][y].equals(Case.BLANC)) && adversaire.equals(Joueur.BLANC) )
					|| ( (jeu[x][y].equals(Case.NOIR)) && adversaire.equals(Joueur.NOIR) ) ){	   // adversaire => changer la couleur puis continuer
				if (jeu[x][y].equals(Case.BLANC)){
					jeu[x][y] = Case.NOIR;
				}
				else if (jeu[x][y].equals(Case.NOIR)){
					jeu[x][y] = Case.BLANC;
				}
				if (joueurCourant.equals(Joueur.NOIR)){
					scoreNoir ++;
					scoreBlanc --;  
				}  
				else if (joueurCourant.equals(Joueur.BLANC)){
					scoreNoir --;
					scoreBlanc ++;  
				}  
				x = x + dx;      
				y = y + dy;     
			}
			else if (   ( (jeu[x][y].equals(Case.BLANC)) && joueurCourant.equals(Joueur.BLANC) )
					|| ( (jeu[x][y].equals(Case.NOIR)) && joueurCourant.equals(Joueur.NOIR) ) ){	   // pion du joueur => sortir de la boucle
				break;
			}
		}
	}	


	/* ACCESSEURS */

	public Case[][] getJeu() {
		return jeu;
	}


	public Etat getEtatPartie() {
		return etatPartie;
	}


	public void setEtatPartie(Etat etatPartie) {
		this.etatPartie = etatPartie;
	}


	public int getPionsDisponibles() {
		return pionsDisponibles;
	}


	public int getScoreBlanc() {
		return scoreBlanc;
	}


	public int getScoreNoir() {
		return scoreNoir;
	}


	public Joueur getJoueurCourant() {
		return joueurCourant;
	}


	public Joueur getAdversaire() {
		return adversaire;
	}


	public int getNbCoupsJoues() {
		return nbCoupsJoues;
	}

	public String jeuToString(){
		String s = "-------------------------------------- \n \n" ;
		for(int i=0; i<8; i++){
			for(int j=0;j<8; j++){
				s = s + jeu[i][j].toString() + "  ";
				if(j==7){
					s = s + "\n";
				}
			}
		}
		s = s + "\n \n -------------------------------------- \n \n ";
		return s;
	}


}