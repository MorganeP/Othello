package controleur;
import enumeration.Case;
import enumeration.Joueur;
import enumeration.ModeJeu;
import modele.Partie;
import vue.Fenetre;


public class Controleur {

	Fenetre fenetre;
	Partie partie;
	ModeJeu mode;
	int temps = 4000; // temps d'attente avant que la machine joue

	public static void main(String[] args) {
		Controleur controleur = new Controleur();		
	}

	public Controleur(){
		mode = ModeJeu.MODE_2_JOUEURS;
		partie = new Partie(this);
		fenetre = new Fenetre(this, partie);
	}

	public void nouvellePartie(ModeJeu m){
		mode = m;
		partie = new Partie(this);
		fenetre = new Fenetre(this, partie);
	}

	public void clicSurPlateau(int x, int y){

		if(mode.equals(ModeJeu.MODE_2_JOUEURS)){
			partie.jouer(x, y);
			fenetre.miseAJourVue(partie);
		}

		if(mode.equals(ModeJeu.MODE_JOUEUR_MACHINE)){
			// vérifier que c'est bien au tour du joueur noir
			if(partie.getJoueurCourant().equals(Joueur.NOIR)){
				partie.jouer(x, y);
				fenetre.miseAJourVue(partie);
				Thread.sleep(temps);
//				Thread thread = new Thread();
//				thread.wait(2000);
				partie.jouerMachine();
				fenetre.miseAJourVue(partie);
			}
		}

		if(mode.equals(ModeJeu.MODE_2_MACHINES)){
			// pas de clics à traiter
		}
	}

}
