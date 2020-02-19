import java.awt.Color;

/**
 * Schreibt einen Text auf die Leinwand.
 * 
 * @author Bernd Gramlich
 * @version 2019-12-15
 */
public class Beschriftung extends Grafik
{
    /*
     * Art des Referenzpunkts für die Y-Achse.
     * (0: Textmitte, 1: obere Textmitte, -1: untere Textmitte)
     */
    private int ausrichtungY;

    /*
     * Art des Referenzpunkts für die X-Achse.
     * (0: Textmitte, 1: rechts der Textmitte, -1: links der Textmitte)
     */
    private int ausrichtungX;

    /*
     * Der Text.
     */
    private String inhalt;

    /*
     * Die Richtung, in die der Text verlaeuft.
     * (Ein waagrechter Text entsteht bei drehX = 1 und drehY = 0,
     * ein senkrechter von unten nach oben bei drehX = 0 und
     * drehY = 1.)
     */
    private int drehX;
    private int drehY;

    /*
     * Das Zentrum, um das der Text gedreht wird.
     */
    private int zentrumX;
    private int zentrumY;

    /*
     * Die Schriftgroesse.
     */
    private int schriftgroesse;
    
    /*
     * Ist die Beschriftung bisher sichtbar?
     */
    private boolean bisherSichtbar = false;

    /**
     * Konstruktor der Klasse Beschriftung.
     * Erzeugt eine neue Beschriftung mit Standardtext in Standardgroesse und Standardfarbe.
     */
    public Beschriftung ()
    { 
        positionX      = 300;
        positionY      = 225;

        ausrichtungX   = 0;
        ausrichtungY   = 0;

        inhalt         = "Informatik macht Spass.";

        drehX          = 1;
        drehY          = 0;

        zentrumX       = 0;
        zentrumY       = 0;
        
        schriftgroesse = 12;
        sofort         = true;

        setzeFarbe ("grau");
        werdeUnsichtbar ();
    }

    /**
     * Aendere die Drehrichtung des Textes.
     */
    public void setzeDrehrichtung (int dX, int dY)
    {
        drehX = dX;
        drehY = dY;
        schreibe ();
    }

    /**
     * Aendere das Drehzentrum des Textes.
     */
    public void setzeDrehzentrum (int zX, int zY)
    {
        zentrumX = zX;
        zentrumY = zY;
        schreibe ();
    }

    /**
     * Aendere die vertikale Ausrichtung am Referenzpunkt.
     * (0: Textmitte, 1: untere Textmitte, -1: obere Textmitte)
     */
    public void setzeAusrichtungY (int neueAusrichtungY)
    {
        ausrichtungY = 0;

        if (neueAusrichtungY > 0)
        {
            ausrichtungY = 1;
        }
        else if (neueAusrichtungY < 0)
        {
            ausrichtungY = -1;
        }
        schreibe ();
    }

    /**
     * Aendere horizontale Ausrichtung am Referenzpunkt.
     * (0: Textmitte, 1: rechts der Textmitte, -1: links der Textmitte)
     */
    public void setzeAusrichtungX (int neueAusrichtungX)
    {
        ausrichtungX = 0;

        if (neueAusrichtungX > 0)
        {
            ausrichtungX = 1;
        }
        else if (neueAusrichtungX < 0)
        {
            ausrichtungX = -1;
        }
        schreibe ();
    }

    /**
     * Aendere die Schriftgroesse dieses Textes.
     */
    public void setzeSchriftgroesse (int neueSchriftgroesse)
    {
        schriftgroesse = neueSchriftgroesse;
        schreibe ();
    }

    /**
     * Aendere den Inhalt dieses Textes.
     */
    public void setzeInhalt (String neuerInhalt)
    {
        inhalt = neuerInhalt;
        schreibe ();
    }

    /**
     * Zeichne diesen Text auf die Leinwand.
     */
    protected void zeichne ()
    {
        schreibe ();
    }

    /**
     * Schreibe diesen Text auf die Leinwand.
     */
    protected void schreibe ()
    {
        if (sichtbar)
        {
            Leinwand leinwand = Leinwand.gibLeinwand ();

            if (sofort)
            {
                leinwand.schreibe (this, farbe, inhalt,
                    positionX, positionY, drehX, drehY, zentrumX, zentrumY,
                    ausrichtungX, ausrichtungY, schriftgroesse);
            }
            else
            {
                leinwand.merkeZumSchreibenVor (this, farbe, inhalt,
                    positionX, positionY, drehX, drehY, zentrumX, zentrumY,
                    ausrichtungX, ausrichtungY, schriftgroesse);
            }
        }
        else
        {
            if (bisherSichtbar)
            {
                radiere ();
            }
        }
        
        bisherSichtbar = sichtbar;
    } 

    /**
     * Loesche diesen Text von der Leinwand.
     */
    private void radiere ()
    {
        Leinwand leinwand = Leinwand.gibLeinwand ();

        if (sofort)
        {
            leinwand.radiere (this);
        }
        else
        {
            leinwand.merkeZumRadierenVor (this);
        }
    }

    /**
     * Nenne den Text dieser Beschriftung.
     */
    public String gibText ()
    {
        return inhalt;
    }
}