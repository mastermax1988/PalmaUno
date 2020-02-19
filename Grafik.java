import java.awt.Color;

/**
 * Zeichnet eine Grafik auf die Leinwand.
 *
 * @author Bernd Gramlich
 * @version 2020-01-03
 */
public abstract class Grafik
{       
    /*
     * Die Koordinaten der Grafik (genauer: der linken
     * oberen Ecke des umbeschriebenen Rechtecks).
     */
    protected int positionX;
    protected int positionY;

    /*
     * Die Farbe der Grafik.
     */
    protected Color farbe;

    /*
     * Ist die Grafik sichtbar?
     */
    protected boolean sichtbar;

    /*
     * Werden Aenderungen an der Grafik sofort gezeichnet?
     * (Animierte Grafiken reagieren schneller, wenn
     * Aenderungen nicht sofort gezeichnet, sondern erst
     * bei der naechsten Erneuerung der Leinwand
     * umgesetzt werden).
     */
    protected boolean sofort;   

    /**
     * Mache diese Grafik sichtbar.
     */
    public void werdeSichtbar ()
    {
        sichtbar = true;
        zeichne ();
    }

    /**
     * Mache diese Grafik unsichtbar.
     */
    public void werdeUnsichtbar ()
    {
        sichtbar = false;
        zeichne ();
    }

    /**
     * Verschiebe diese Grafik waagrecht um 'entfernung'
     * Bildschirmpunkte.
     */
    public void verschiebeWaagrecht (int entfernung)
    {
        verschiebe (entfernung, 0);
    }

    /**
     * Verschiebe diese Grafik senkrecht um 'entfernung'
     * Bildschirmpunkte.
     */
    public void verschiebeSenkrecht (int entfernung)
    {
        verschiebe (0, entfernung);
    }

    /**
     * Verschiebe diese Grafik um den Vektor (dx, dy).
     */
    public void verschiebe (int dx, int dy)
    {
        positionX += dx;
        positionY += dy;

        zeichne ();
    }

    /**
     * Gleite diese Grafik waagrecht um 'entfernung' Bildschirmpunkte.
     */
    public void gleiteWaagrecht (int entfernung)
    {
        int delta;

        if (entfernung < 0)
        {
            delta = -1;
            entfernung = -entfernung;
        }
        else
        {
            delta = 1;
        }

        for (int i = 0; i < entfernung; i++)
        {
            verschiebe (delta, 0);
        }
    }

    /**
     * Gleite diese Grafik senkrecht um 'entfernung' Bildschirmpunkte.
     */
    public void gleiteSenkrecht (int entfernung)
    {
        int delta;

        if (entfernung < 0)
        {
            delta = -1;
            entfernung = -entfernung;
        }
        else
        {
            delta = 1;
        }

        for (int i = 0; i < entfernung; i++)
        {
            verschiebe (0, delta);
        }
    }

    /**
     * Aendere die Koordinaten dieser Grafik.
     */
    public void setzePosition (int neuePositionX, int neuePositionY)
    {
        verschiebe (neuePositionX - positionX, neuePositionY - positionY);
    }	
    
    /**
     * Nenne zu einem Farbnamen die passende Farbe.
     */
    private Color entschluessleFarbname (String farbname)
    {        
        switch (farbname)
        {
            case "rot":         return Color.red;
            case "schwarz":     return Color.black;
            case "blau":        return Color.blue;
            case "gelb":        return Color.yellow;
            case "gruen":       return Color.green;
            case "lila":        return Color.magenta;
            case "weiss":       return Color.white;
            case "grau":        return Color.gray;
            case "orange":      return Color.orange;
            case "tuerkis":     return Color.cyan;
            case "pink":        return Color.pink;
            case "braun":       return new Color (102,  51,   0);
            case "bleich":      return new Color (248, 248, 190);
            case "dunkelbraun": return new Color ( 76,  25,   0);
            case "hellbraun":   return new Color (153, 102,  51);
            case "blond":       return new Color (255, 211,  51);
            case "gebraeunt":   return new Color (255, 222, 194);
            default:            return Color.black; // ungueltige Farbnamen werden als Schwarz interpretiert.
        }
    }

    /**
     * Aendere den Farbnamen dieser Grafik in 'neuerFarbname'. Gueltige
     * Angaben sind waren zuletzt "blau", "bleich", "blond", "braun",
     * "dunkelbraun", "gebraeunt", "gelb", "grau", "gruen", "lila",
     * "orange", "pink", "rot", "schwarz", "tuerkis" und "weiss".)
     */
    public void setzeFarbe (String neuerFarbname)
    {
        farbe = entschluessleFarbname (neuerFarbname);
        zeichne ();
    }

    /**
     * Aendere die Farbe dieser Grafik in 'neueFarbe'.
     * (Tipp: "rot"   entspricht new java.awt.Color (255,   0,   0),
     *        "gruen" entspricht new java.awt.Color (  0, 255,   0),
     *        "blau"  entspricht new java.awt.Color (  0,   0, 255).
     * Java erlaubt neben RGB noch weitere Farbeffekte, siehe
     * Dokumentation von java.awt.Color,)
     */
    public void setzeRGBFarbe (Color neueFarbe)
    {
        farbe = neueFarbe;
        zeichne ();
    }

    /**
     * Nenne die Farbe dieser Grafik.
     */
    public Color gibFarbe ()
    {
        return farbe;
    }
    
    /**
     * Pruefe, ob die Grafik die angegebene Farbe hat.
     */
    public boolean hatFarbe (String pruefFarbname)
    {
        return entschluessleFarbname (pruefFarbname).equals (farbe);
    }
    
    /**
     * Pruefe, ob die Grafik die angegebene RGB-Farbe hat.
     */
    public boolean hatRGBFarbe (Color pruefFarbe)
    {
        return pruefFarbe.equals (farbe);
    }

    /**
     * Lass diese Grafik sofort auf Zeichenbefehle reagieren.
     */
    public void reagiereSofort ()
    {
        sofort = true;
        zeichne ();
    }

    /**
     * Lass diese Grafik verzoegert auf Zeichenbefehle reagieren.
     */
    public void reagiereSpaeter ()
    {
        sofort = false;
    }

    /**
     * Zeichne diese Grafik auf die Leinwand.
     */
    protected abstract void zeichne ();
}
