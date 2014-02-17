package vue;
/**
 * @author Kom Patrick
 * 
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


/**
 *  Les differentes options de jeu et du plateau
 *
 */
public class Options extends JFrame implements ActionListener
{
	
	JButton ok;
	Checkbox chkniv1;
	Checkbox chkniv2;
	Checkbox chkniv3;
	JButton btn;
	JButton btn2;
	Plateau plat;
	Color clPlateau, clHelp;
	
	public Options(Plateau plateau)
	{
		super("Configuration");
		this.plat = plateau;
		setLayout(new GridLayout(3,1));
		setResizable(false);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension taille = kit.getScreenSize();
		int width = taille.width;
		int height = taille.height;
		setBounds(width/4+75, height/4, 300, 300);
		ok = new JButton("  OK  ");
		ok.addActionListener(this);
			
		//Paneau des parametres de niveau
		JPanel niv = new JPanel(new GridLayout(3,1));
		 CheckboxGroup cbg = new CheckboxGroup();
		 chkniv1 = new Checkbox("Debutant", cbg, false);
		 chkniv2 = new Checkbox("Initie", cbg, false);
		 chkniv3 = new Checkbox("Expert", cbg, false);
//		 switch (plat.getIntelligence().getNiveau())
//		 {
//			 case Ia.DEBUTANT:
//				 chkniv1.setState(true);
//				 break;
//			 case Ia.INITIE:
//				 chkniv2.setState(true);
//				 break;
//			 case Ia.EXPERT:
//				 chkniv3.setState(true);
//				 break;
//		 }
//		 
		 niv.add(chkniv1);
		 niv.add(chkniv2);
		 niv.add(chkniv3);

		niv.setBorder(new TitledBorder(new EtchedBorder(1), 
										"Difficulte", 
										TitledBorder.LEFT, 
										TitledBorder.ABOVE_TOP));
		add(niv);
		JPanel color = new JPanel(new GridLayout(2,2));
		JLabel lblColorPlateau = new JLabel("Couleur du plateau :");
		JLabel lblColorCpsJouables = new JLabel("Cases d' indications :");
		 btn = new JButton("Choix de la couleur");
		btn2 = new JButton("Choix de la couleur");
		btn.addActionListener(this);
		btn2.addActionListener(this);
		color.add(lblColorPlateau);
		color.add(btn);
		color.add(lblColorCpsJouables);
		color.add(btn2);
		color.setBorder(new TitledBorder(new EtchedBorder(1), 
				"Couleurs", 
				TitledBorder.LEFT, 
				TitledBorder.ABOVE_TOP));
		add(color);
		JPanel bt = new JPanel();
		bt.setLayout(null);
		ok.setBounds(190, 30, 100, 30);
		bt.add(ok);
		add(bt);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == ok)
		{
			//Mise a jour de l'intelligence du jeu
//			if(chkniv1.getState() == true) plat.setIntelligence(new Ia(Ia.DEBUTANT));
//			else if(chkniv2.getState() == true) plat.setIntelligence(new Ia(Ia.INITIE));
//			else plat.setIntelligence(new Ia(Ia.EXPERT));
//			//Mise a jour des couleurs sur le plateau
//			if(clPlateau != null) plat.setCaseColor(clPlateau);
//			if(clHelp != null) plat.setHelpColor(clHelp);
//			plat.repaint();
//			this.dispose();
		}
//		if(e.getSource() == btn)
//		{
//			clPlateau = JColorChooser.showDialog(this, "Choix de la couleur", plat.getCaseColor());
//		}
//		if(e.getSource() == btn2)
//		{
//			clHelp = JColorChooser.showDialog(this, "Choix de la couleur", plat.getHelpColor());
//		}
	}
	
	
}
