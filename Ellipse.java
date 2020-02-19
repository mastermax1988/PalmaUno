import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.Shape;

/**
 * Zeichnet eine Ellipse mit achsenparallelen Halbachsen auf die Leinwand.
 * 
 * @author Bernd Gramlich
 * @version 2020-02-07
 */
public class Ellipse extends Figur
{
    /*
     * Die Breite und die Hoehe der Ellipse
     */
    private int breite;
    private int hoehe;

    /**
     * Konstruktor der Klasse Ellipse.
     * Erzeugt eine neue Ellipse in Standardgroesse und Standardfarbe.
     */
    public Ellipse ()
    {
        hoehe        = 20;
        breite       = 30;
        sofort       = true;
        
        setzeFarbe ("tuerkis");
        werdeUnsichtbar ();
    }
	
    /**
	 * Aendere die Hoehe dieser Ellipse.
	 */
	public void setzeHoehe (int neueHoehe)
	{
		hoehe = neueHoehe;
		zeichne ();
	}
	
	/**
	 * Aendere die Breite dieser Ellipse.
	 */
	public void setzeBreite (int neueBreite)
	{
		breite = neueBreite;
		zeichne ();
	}
	
	/**
	 * Nenne die Ellipse als Figur (festgelegt durch Koordinaten,
	 * Breite und Hoehe).
	 */
	protected Ellipse2D.Double gibFigur ()
	{
		return new Ellipse2D.Double (positionX, positionY, breite, hoehe);
	}
}
