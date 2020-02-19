import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Shape;

/**
 * Zeichnet eine Figur auf der Leinwand.
 * 
 * @author Bernd Gramlich
 * @version 2019-12-15
 */
public abstract class Figur extends Grafik
{
    /*
     * Ist die Figur bisher sichtbar?
     */
    private boolean bisherSichtbar = false;
    
    /**
     * Pruefe, ob ein Punkt in dieser Figur enthalten ist.
     */
    public boolean enthaelt (Point2D punkt)
    {
        Shape form = Leinwand.gibLeinwand ().gibForm (this);

        if (null != form)
        {
            return form.contains (punkt);
        }
        else
        {
            return false;
        }
    }

    /**
     * Pruefe, ob diese Figur eine andere Figur schneidet.
     */
    public boolean schneidet (Figur andere)
    {
        Shape meineForm =  Leinwand.gibLeinwand ().gibForm (this);
        Shape andereForm = Leinwand.gibLeinwand ().gibForm (andere);

        if (null != meineForm && null != andereForm)
        {
            return meineForm.intersects (andereForm.getBounds ());
        }
        else
        {
            return false;
        }
    }

    /**
     * Nenne die Figur, die zu zeichnen ist.
     */
    protected abstract Shape gibFigur ();

    /**
     * Zeichne diese Figur auf die Leinwand.
     * (Die Figur muss von der Unterklasse korrekt definiert sein.)
     */
    protected void zeichne ()
    {
        if (sichtbar)
        {
            Leinwand leinwand = Leinwand.gibLeinwand ();	
            Shape    figur    = gibFigur ();

            if (sofort)
            {
                leinwand.zeichne (this, farbe, figur);
            }
            else
            {
                leinwand.merkeZumZeichnenVor (this, farbe, figur);
            }
        }
        else
        {
            if (bisherSichtbar)
            {
                loesche ();
            }
        }
        
        bisherSichtbar = sichtbar;
    } 

    /**
     * Loesche diese Figur von der Leinwand.
     */
    private void loesche ()
    {
        Leinwand leinwand = Leinwand.gibLeinwand ();
        
        if (sofort)
        {
            leinwand.entferne (this);
        }
        else
        {
            leinwand.merkeZumEntfernenVor (this);
        }
    } 
}
