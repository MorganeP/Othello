package vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

import controleur.Controleur;
import enumeration.Case;
import enumeration.Etat;
import modele.Partie;


/**
 * @author Kom Patrick
 * Gestion de la partie 'VUE' du plateau
 */
public class Plateau extends JPanel implements MouseListener
{
	Partie partie;
	Controleur controleur;
	//Constantes
	public static final int VIDE = 0;
	public static final int NOIR = 1;
	public static final int BLANC = -1;
	//Représentation des cases sur le plateau
	private JPanel infos;
	private List history;
	private JScrollPane scrollPane;
	private JPanel p;
	private Color caseColor;
	private Color helpColor;
	//Labels des scores
	private JLabel lblPionsBlancs, lblPionsNoirs, lblJoueurCourant, lblNbrePB, lblNrePN, lblJC;

	public Plateau(Partie partie, JPanel infos, JScrollPane scrollPane, List history, Controleur c)
	{
		controleur = c;
		this.partie = partie;
		caseColor = new Color(112,210,13);
		helpColor = new Color(192, 192, 192);
		//Panneau des scores
		this.infos = infos;
		this.scrollPane = scrollPane;
		this.history = history;
		infos.setLayout(new GridLayout(3,1));
		lblPionsNoirs = new JLabel("Noirs :");
		lblPionsBlancs = new JLabel("IA :");
		lblJoueurCourant = new JLabel("");
		lblJoueurCourant.setForeground(Color.white);
		lblNbrePB = new JLabel(String.valueOf(partie.getScoreBlanc()));
		lblNrePN = new JLabel(String.valueOf(partie.getScoreNoir()));
		infos.add(lblPionsBlancs);
		infos.add(lblNbrePB);
		infos.add(lblPionsNoirs);
		infos.add(lblNrePN);
		p = new JPanel();
		p.add(lblJoueurCourant);
		p.setBackground(Color.GRAY);
		p.setBorder(new EtchedBorder(5));
		infos.add(p);
		infos.setBounds(360, 60, 170, 80);

		//Historique des coups
		scrollPane.setBounds(360, 150, 170, 230);

		this.addMouseListener(this);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	public void miseAJourPartie(Partie p){
		partie = p;
		revalidate();
		repaint();
	}


	public void paintComponent(Graphics g){
		System.out.println("Plateau.paintComponent()");
		super.paintComponent(g);
		g.setColor(caseColor);
		//Les scores
		lblNbrePB.setText(String.valueOf(partie.getScoreBlanc()));
		lblNrePN.setText(String.valueOf(partie.getScoreNoir()));
		for(int i=0; i<8; i++){
			for(int j=0;j<8; j++)
			{
				if(((partie.getJeu())[i][j]).equals(Case.NOIR)){
					g.drawRect(i*40, j*40, 40, 40);
					g.drawOval(i*40+4, j*40+4, 30, 30);
					g.setColor(caseColor);	
					g.fill3DRect(i*40, j*40, 40, 40, true);
					g.setColor(Color.BLACK);	
					g.fillOval(i*40+4, j*40+4, 30, 30);
				}
				else if(((partie.getJeu())[i][j]).equals(Case.BLANC)){
					g.drawRect(i*40, j*40, 40, 40);
					g.drawOval(i*40+4, j*40+4, 30, 30);
					g.setColor(caseColor);	
					g.fill3DRect(i*40, j*40, 40, 40, true);
					g.setColor(Color.WHITE);	
					g.fillOval(i*40+4, j*40+4, 30, 30);
				}
				else if(((partie.getJeu())[i][j]).equals(Case.VIDE)){
					//						if(board.coupPossible(i, j, partie.getCurrentPlayer()) && partie.getCurrentPlayer() != Plateau.BLANC)
					//						{
					//							g.setColor(helpColor);
					//							g.drawRect(i*40, j*40, 40, 40);
					//							g.fill3DRect(i*40, j*40, 40, 40, true);
					//						}
					//						else
					//						{
					g.setColor(caseColor);
					g.drawRect(i*40, j*40, 40, 40);
					g.fill3DRect(i*40, j*40, 40, 40, true);
					//						}
				}
//				System.out.println(i + "-" + j);
			}
		}
		System.out.println("FIN paintComponent()");
	}


	public void mouseClicked(MouseEvent me){
		String ch = " ABCDEFGH";
		/*On recupere la case ou on a clique*/
		int x = me.getX()/40;
		int y = me.getY()/40;
		if (partie.getEtatPartie().equals(Etat.ENCOURS)){
			controleur.clicSurPlateau(x, y);
		}
		this.repaint();
	}
	public void mousePressed(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}	
	public void mouseExited(MouseEvent me){}
	public void mouseEntered(MouseEvent me){}
}
