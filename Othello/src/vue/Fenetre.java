package vue;
/**
 * @author Kom Patrick
 * Fenetre de jeu
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controleur.Controleur;
import enumeration.Case;
import enumeration.ModeJeu;
import modele.Partie;


public class Fenetre extends JFrame implements ActionListener
{
	Controleur controleur;
	private Plateau plateau;
	private List History;
	private JScrollPane scrollPane;
	private JPanel infos;
	Case[][] jeu;
	private MenuItem nouv;
	private MenuItem mode1;
	private MenuItem mode2;
	private MenuItem mode3;
	private MenuItem quit;
	/**A propos*/
	private MenuItem about;
	//Options
	private MenuItem opt;


	public Fenetre(Controleur c, Partie partie) 
	{
		super("Othello Morgane & Nico !");
		controleur = c;
		int i;
		setLayout(null);
		setResizable(false);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension taille = kit.getScreenSize();
		int width = taille.width;
		int height = taille.height;
		setBounds(width/4+75, height/4, 550, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//Plateau de jeu
		History = new List();
		History.setForeground(Color.BLACK);
		scrollPane = new JScrollPane(History);
		infos = new JPanel();
		plateau = new Plateau(partie, infos, scrollPane, History, controleur);
		plateau.setBounds(20, 60, 320, 320);
		//Affichage des labels
		for(i=0;i<=7;i++){
			JLabel lbl = new JLabel(String.valueOf(i+1));
			lbl.setBounds(8, 70 + 40*i, 10, 10);
			lbl.setFont(new Font("", Font.BOLD, 12));
			this.add(lbl);	
		}
		String ch = " ABCDEFGH";
		for(i=0;i<=7;i++)
		{
			JLabel lbl = new JLabel(String.valueOf(ch.charAt(i+1)));
			lbl.setBounds(35 + 40*i, 45, 10, 10);
			lbl.setFont(new Font("", Font.BOLD, 12));
			lbl.setForeground(Color.RED);
			this.add(lbl);

		}
		add(plateau);

		//Historique des coups

		add(scrollPane);

		//Panneau d'infos
		add(infos);
		setVisible(true);

		//Le menu
		MenuBar menu =new MenuBar();
		Menu jeu=new Menu("Jeu");
		menu.add(jeu); 
		
		nouv = new MenuItem("Nouvelle partie");
		nouv.addActionListener(this);
		jeu.add(nouv);
		
		Menu mode = new Menu("Mode de Jeu");
//		mode.addActionListener(this);
		jeu.add(mode);
		mode1 = new MenuItem("Deux joueurs");
		mode1.addActionListener(this);
		mode.add(mode1);
		mode2 = new MenuItem("Joueurs / Machine");
		mode2.addActionListener(this);
		mode.add(mode2);
		mode3 = new MenuItem("Deux machines");
		mode3.addActionListener(this);
		mode.add(mode3);
		
		opt = new MenuItem("Configuration");
		opt.addActionListener(this);
		jeu.add(opt);
		
		quit=new MenuItem("Quitter");
		quit.addActionListener(this);
		jeu.addSeparator();
		jeu.add(quit);
		Menu help =new Menu(" ? ");
		about = new MenuItem("A propos");
		about.addActionListener(this);
		help.add(about);
		menu.add(help);
		this.setMenuBar(menu);

		//Changer le look l'interface graphique
		String UnLook = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" ;
		try {
			UIManager.setLookAndFeel(UnLook); // assigne le look and feel choisi ici windows
			SwingUtilities.updateComponentTreeUI(this.getContentPane( )); // réactualise le graphisme de l'IHM
		}
		catch (Exception exc) {
			exc.printStackTrace( );
		}

		this.repaint();
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.nouv)
		{
			History.removeAll();
			plateau.repaint();
		}
		
		else if(e.getSource() == this.mode1)
		{
			ModeJeu mode = ModeJeu.MODE_2_JOUEURS;
			System.out.println("=========================================================================\n NOUVELLE PARTIE EN MODE "+mode);
		}
		
		else if(e.getSource() == this.mode2)
		{
			ModeJeu mode = ModeJeu.MODE_JOUEUR_MACHINE;
			System.out.println("=========================================================================\n NOUVELLE PARTIE EN MODE "+mode);
		}
		
		else if(e.getSource() == this.mode3)
		{
			ModeJeu mode = ModeJeu.MODE_2_MACHINES;
			System.out.println("=========================================================================\n NOUVELLE PARTIE EN MODE "+mode);
		}
		
		else if(e.getSource() == this.quit)
		{
			if(JOptionPane.showConfirmDialog(this, "Êtes vous sûr de vouloir quitter mon jeu ?", "OTHELLO KOM", JOptionPane.YES_NO_CANCEL_OPTION) == 0)
			{
				this.dispose();
				System.exit(0);
			}

		}
		
		else if(e.getSource() == this.about)
		{
			JOptionPane.showMessageDialog(this, "- Université Adventiste Cosendai, Nanga-Eboko, Cameroun \n- TP Intelligence Artificielle Janvier 2008 \n- 20488 KOM Patrick, patrick.kom@visionintel.com", "OTHELLO KOM", JOptionPane.INFORMATION_MESSAGE);
		}
		
		else if(e.getSource() == this.opt)
		{
			Options op = new Options(plateau);
		}
	}
	
	public void miseAJourVue(Partie p){
		System.out.println("Fenetre => MiseAJourVue();");
		plateau.miseAJourPartie(p);
	}


}



