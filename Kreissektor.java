import java.awt.geom.Arc2D;
import java.awt.geom.Arc2D.Double;

/**
 * Zeichnet einen Kreissektor auf die Leinwand.
 * 
 * @author Bernd Gramlich nach einer Vorlage von Michael Kölling und David J. Barnes 
 * @version 2019-10-10
 */
public class Kreissektor extends Figur
{
    /*
     * Der Radius des Kreissektors.
     */
    private int radius;
    
    /*
     * Der Anfangswinkel des Kreissektor. (Um wie viele
     * Winkelgrad ist das eine Ende des Kreissektors
     * gegenueber der Ostrichtung verdreht?
     */
    private int anfangswinkel;
    
    /*
     * Der Gesamtwinkel des Kreissektors. (Wie viele
     * Winkelgrad ueberstreicht der Kreissektor im
     * Gegenuhrzeigersinn?
     */
    private int gesamtwinkel;

    /**
     * Konstruktor der Klasse Kreissektor.
     * Erzeugt einen neuen Kreissektor in Standardgroesse und Standardfarbe.
     */
    public Kreissektor ()
    {
        radius        = 50;
        anfangswinkel = 30;
        gesamtwinkel  = 300;
        sofort        = true;

        setzeFarbe ("gelb");
        werdeUnsichtbar ();
    }

    /**
     * Aendere den Radius dieses Kreissektors.
     */
    public void setzeRadius (int neuerRadius)
    {
        radius = neuerRadius;
        zeichne ();
    }

    /**
     * Aendere den Anfangswinkel dieses Kreissektors.
     */
    public void setzeAnfangswinkel (int neuerAnfangswinkel)
    {
        anfangswinkel = neuerAnfangswinkel;
        zeichne ();
    }

    /**
     * Aendere den Gesamtwinkel dieses Kreissektors.
     */
    public void setzeGesamtwinkel (int neuerGesamtwinkel)
    {
        gesamtwinkel = neuerGesamtwinkel;
        zeichne ();
    }

	/**
	 * Nenne den Kreissektor als Figur (festgelegt durch Koordinaten,
	 * Radius, Anfangswinkel und Gesamtwinkel).
	 */
	protected Arc2D.Double gibFigur ()
	{
		return new Arc2D.Double (positionX, positionY, 2*radius, 2*radius,
		                         anfangswinkel, gesamtwinkel, 2);
	}
}
