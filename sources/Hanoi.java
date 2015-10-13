/** Programme permettant de manipuler et d'expérimenter le problème
 *  des tours de Hanoï */
public class Hanoi 
{
    // Les trois piles représentant les tours de Hanoï
    private static PileHanoi a, b, c ;

    // Initialisation des tours pour n disques, placés au début en A
    private static void init(int n) 
    {
	a = new PileHanoi("pile A", new AffichageGraphique("A")) ;
	b = new PileHanoi("pile B", new AffichageGraphique("B")) ;
	c = new PileHanoi("pile C", new AffichageGraphique("C")) ;
	for (int i=n; i>0; i--)
	    a.empile(new DisqueHanoi(i)) ;
    }

    // Affichage des trois tours
    public static void affiche() 
    {
	System.out.println(a) ;
	System.out.println(b) ;
	System.out.println(c) ;
    }

    // Resou automatiquement le problème Hanoi
    public static void resoudreAuto(PileHanoi a, PileHanoi b, PileHanoi c) {
	int n = a.nbElements();
	a.deplacerDesDisques(n,b,c);
    }

    // Pour le mode interactif, le choix de la pile est donné par le joueur
    // en toutes lettres ("A", "B", "C"). -> retourne la pile correspondante
    private static PileHanoi analyse(String r) 
    {
	if (r.equalsIgnoreCase("A"))
	    return a ;
	if (r.equalsIgnoreCase("B"))
	    return b ;
	return c ;
    }

    // Méthode principale du programme.
    public static void main (String [] arg) 
    {
	// le nombre de disques (on peut aussi le demander au joueur)
	int nbDisques = 4 ;
	
	// initialisation des piles
	init(nbDisques) ;
	
	boolean fini = false ;
	String rep ;
	PileHanoi depart, arrivee ;

	// Si argument --auto
	if (arg.length != 0 && arg[0].equals("--auto"))
	    resoudreAuto(a,b,c); // On résou le problème automatiquement
	else {
	    do {
		// on commence par afficher les tours	    
		affiche() ;
		// on demande au joueur la tour de départ (A, B, C)
		System.out.print("Déplacer de : ") ;
		rep = Clavier.readString() ;
		if (rep.equalsIgnoreCase("STOP"))
		    fini = true ;
		// on en déduit l'objet correspondant
		depart = analyse(rep) ;
		if (!fini) 
		    {
			// même chose pour la tour d'arrivée
			System.out.print("Vers : ") ;
			rep = Clavier.readString() ;
			if (rep.equalsIgnoreCase("STOP"))
			    fini = true ;
			arrivee = analyse(rep) ;
			// on effectue le déplacement si c'est possible
			if (arrivee.peutEmpiler(depart.sommet()))
			    depart.deplacerUnElementVers(arrivee) ;
			else
			    System.out.println("Impossible !") ;
		    }
		// et on continue tant que le joueur n'a pas dit STOP
	    } while (!fini) ;
	}
	System.out.println("OK, c'est fini !") ;
    }
}
