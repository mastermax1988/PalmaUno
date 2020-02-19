import java.awt.Polygon;

/**
 * Zeichnet ein Parallelogramm auf die Leinwand.
 * 
 * @author Bernd Gramlich
 * @version 2019-10-10
 */
public class Parallelogramm extends Figur
{
    /*
     * Die x- und y-Koordinaten der vier Eckpunkte werden
     * in jeweils einem Feld gespeichert.
     */
    private int[] x;
    private int[] y;

    /**
     * Konstruktor der Klasse Parallelogramm.
     * Erzeugt ein neues Parallelogramm in Standardgroesse und Standardfarbe.
     */
    public Parallelogramm ()
    {
        /*
         * Erzeuge die Felder fuer die x- und die y-Koordinaten
         * der Eckpunkte. 
         */
        x = new int[4];
        y = new int[4];

        /*
         * Waehle Standardwerte fuer die x-Koordinaten der ersten drei
         * Eckpunkte.
         */
        x[0] = 0;
        y[0] = 30;
        x[1] = 40;
        y[1] = 60;
        x[2] = 80;
        y[2] = 50;

        /*
         * Faerbe das Parallelogramm blau und mache es zunaechst sichtbar.
         */
        sofort       = true;

        setzeFarbe ("blau");
        werdeUnsichtbar ();
    }

    /**
     * Setze die Eckpunkte des Parallelogramms.
     * Dazu werden die x- und die y-Koordinaten der ersten drei
     * Eckpunkte benoetigt. Die Koordinaten des vierten Eckpunkts
     * werden vor dem Zeichnen automatisch so berechnet, dass er
     * im Gegenuhrzeigersinn den drei angegebenen Punkten folgt.
     * (Genauer: (x2|y2) wird an der Geraden durch (x1|y1) und
     * (x3|y3) gespiegelt.)
     */
    public void setzeEckpunkte (int x1, int y1, int x2, int y2, int x3, int y3)
    {
        /*
         * Uebernimm die gewuenschten Koordinaten in die Koordinatenfelder.
         */
        x[0] = x1;
        y[0] = y1;
        x[1] = x2;
        y[1] = y2;
        x[2] = x3;
        y[2] = y3;

        /*
         * Zeichne das Parallelogramm neu.
         */
        zeichne ();
    }

    /**
     * Verschiebe dieses Parallelogramm um den Vektor (dx, dy).
     */
    @Override
    public void verschiebe (int dx, int dy)
    {
        x[0] += dx;
        y[0] += dy;
        x[1] += dx;
        y[1] += dy;
        x[2] += dx;
        y[2] += dy;
        
        super.verschiebe (dx, dy);
    }

    /**
     * Nenne das Parallogramm als Figur (festgelegt durch drei
     * Eckpunkte).
     */
    protected Polygon gibFigur ()
    {
        /*
         * Berechne die Koordinaten des vierten Eckpunkts.
         */
        
        x[3] = x[2] + x[0] - x[1];
        y[3] = y[2] + y[0] - y[1];
        
        /*
         * Nenne nun das Parallelogramm.
         */
        return new Polygon (x, y, 4);
    }
}