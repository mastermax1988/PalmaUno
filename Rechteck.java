import java.awt.Rectangle;

/**
 * Zeichnet ein achsenparelleles Rechteck auf die Leinwand.
 * 
 * @author Bernd Gramlich nach einer Vorlage von Michael Koelling und David J. Barnes 
 * @version 2019-10-10
 */
public class Rechteck extends Figur
{
    /*
     * Die Breite und die Hoehe der Rechtecks.
     */
    private int breite;
    private int hoehe;

    /**
     * Konstruktor der Klasse Rechteck.
     * Erzeugt ein neues Rechteck in Standardgroesse und Standardfarbe.
     */
    public Rechteck ()
    {
        positionX    = 0;
        positionY    = 0;
        breite       = 100;
        hoehe        = 30;
        sofort       = true;

        setzeFarbe ("lila");
        werdeUnsichtbar ();
    }
	
	/**
	 * Aendere die Breite des Rechtecks.
	 */
	public void setzeBreite (int neueBreite)
	{
		breite = neueBreite;
		zeichne ();
	}

	/**
	 * Aendere die Höhe des Rechtecks.
	 */
	public void setzeHoehe (int neueHoehe)
	{
		hoehe = neueHoehe;
		zeichne ();
	}

	/**
	 * Nenne das Rechteck als Figur (festgelegt durch Koordinaten,
	 * Breite und Hoehe).
	 */
	protected Rectangle gibFigur ()
	{
		return new Rectangle (positionX, positionY, breite, hoehe);
	}  
}