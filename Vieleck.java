import java.awt.Polygon;
import java.util.List;
import java.util.ArrayList;
import java.lang.Integer;

/**
 * Zeichnet ein Vieleck auf die Leinwand.
 * 
 * @author Bernd Gramlich
 * @version 2019-10-10
 */
public class Vieleck extends Figur
{
    /*
     * Anzahl der Ecken.
     */
    private int eckenzahl;

    /*
     * Koordinaten der Ecken als Integer 
     * (muessen vor dem Zeichnen in int umgewandelt werden).
     */
    private List<Integer> eckeX;
    private List<Integer> eckeY;

    /**
     * Konstruktor der Klasse Vieleck.
     * Erzeugt ein leeres Vieleck (noch ohne Eckpunkte) in
     * Standardfarbe.
     */
    public Vieleck ()
    {
        eckenzahl    = 0;
        eckeX        = new ArrayList<Integer> ();
        eckeY        = new ArrayList<Integer> ();
        sofort       = true;

        setzeFarbe ("gruen");
        werdeUnsichtbar ();
    }

    /**
     * Fuege zum Vieleck einen Eckpunkt mit Koordinaten (x|y) hinzu.
     */
    public void ergaenzeEcke (int x, int y)
    {
        eckenzahl++;
        eckeX.add (new Integer (x));
        eckeY.add (new Integer (y));
        zeichne ();
    }

    /**
     * Loesche den Eckpunkt mit der Nummer k.
     */
    public boolean loescheEcke (int k)
    {
        if (k >= 0 && k < eckenzahl)
        {
            eckenzahl--;
            eckeX.remove (k);
            eckeY.remove (k);
            zeichne ();
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Loesche alle Eckpunkte.
     */
    public void loescheAlleEcken ()
    {
        eckenzahl = 0;
        eckeX.clear ();
        eckeY.clear ();
        zeichne ();
    }

    /**
     * Verschiebe den Eckpunkt mit der Nummer k um den Vektor
     * (dx|dy).
     */
    public boolean verschiebeEcke (int k, int dx, int dy)
    {
        if (k >= 0 && k < eckenzahl)
        {
            eckeX.set (k, new Integer (eckeX.get (k).intValue () + dx));
            eckeY.set (k, new Integer (eckeY.get (k).intValue () + dy));       
            zeichne ();
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Gib dem Eckpunkt mit der Nummer k die neuen Koordinaten (x|y).
     */
    public boolean aendereEcke (int k, int x, int y)
    {
        if (k >= 0 && k < eckenzahl)
        {
            eckeX.set (k, new Integer (x));
            eckeY.set (k, new Integer (y));
            zeichne ();
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Verschiebe dieses Vieleck um den Vektor (dx, dy).
     */
    @Override
    public void verschiebe (int dx, int dy)
    {
        for (int i = 0; i < eckenzahl; i++)
        {
            eckeX.set (i, new Integer (eckeX.get (i).intValue () + dx));
            eckeY.set (i, new Integer (eckeY.get (i).intValue () + dy));
        }

        super.verschiebe (dx, dy);
    }

    /**
     * Nenne das Vieleck als Figur (festgelegt durch Koordinaten,
     * Breite und Hoehe).
     */
    protected Polygon gibFigur ()
    {
        int[] eckpunktX;
        int[] eckpunktY;

        eckpunktX = new int[eckenzahl];
        eckpunktY = new int[eckenzahl];

        for (int i = 0; i < eckenzahl; i++)
        {
            eckpunktX[i] = eckeX.get (i).intValue ();
            eckpunktY[i] = eckeY.get (i).intValue ();
        }

        return new Polygon (eckpunktX, eckpunktY, eckenzahl);
    }
}
