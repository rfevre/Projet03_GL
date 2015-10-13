public class AffichageSimple implements Affichage{
    public String affichage_tableau(Disque [] d, int n) {
	String s = "";
	for (int i = 0; i<=n-1; i++)
	    s +=d[i].diametre() + " " ;
	return s ;
    }
}
