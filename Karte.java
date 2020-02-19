import java.awt.geom.Point2D;

/**
 * Eine Spielkarte im Uno-Spiel.
 * 
 * @author Bernd Gramlich
 * @version 2020-02-19
 */
public class Karte 
{
    private Wert wert;
    private Farbe farbe;
    private String rueckenfarbe;
    private boolean aufgedeckt;
    private Rechteck kartenrechteck;
    private Beschriftung aufdruck;
    private int positionY;
    private int positionX;

    /**
     * Konstruktor.
     * Erzeuge eine Spielkarte mit der genannten Farbe, dem genannten
     * Wert und der genannten Rueckenfarbe und positioniere sie links oben
     * auf die Leinwand.
     */
    public Karte(Farbe farbe, Wert wert, String rueckenfarbe)
    {
        this.farbe        = farbe;
        this.wert         = wert;
        this.rueckenfarbe = rueckenfarbe;
        
        kartenrechteck = new Rechteck(); 
        kartenrechteck.setzeHoehe(100);
        kartenrechteck.setzeBreite(60);

        aufdruck = new Beschriftung();
        aufdruck.setzeSchriftgroesse(25);
        aufdruck.setzePosition(30, 50);
        aufdruck.setzeFarbe("weiss");
        
        deckeAuf();
        werdeUnsichtbar();
    }

    /**
     * Mache die Karte sichtbar.
     */
    public void werdeSichtbar()
    {
        kartenrechteck.werdeSichtbar();
        aufdruck.werdeSichtbar();
    }
    
    /**
     * Mache die Karte unsichtbar.
     */
    public void werdeUnsichtbar()
    {
        kartenrechteck.werdeUnsichtbar();
        aufdruck.werdeUnsichtbar();
    }
    
    /**
     * Decke die Karte auf.
     */
    public void deckeAuf()
    {
        kartenrechteck.setzeFarbe(farbe.gibZeichenfarbe());
        aufdruck.setzeInhalt(wert.gibAufdruck());
        
        aufgedeckt = true;
    }
    
    /**
     * Decke die Karte zu.
     */
    public void deckeZu()
    {
        kartenrechteck.setzeFarbe(rueckenfarbe);
        aufdruck.setzeInhalt(" ");
        
        aufgedeckt = false;
    }
    
    /**
     * Pruefe, ob die Karte aufgedeckt ist.
     */
    public boolean istAufgedeckt()
    {
        return aufgedeckt;
    }

    /**
     * Nenne die Farbe der Karte.
     */
    public Farbe gibFarbe()
    {
        return farbe;
    }
    
    /**
     * Nenne den Wert der Karte.
     */
    public Wert gibWert()
    {
        return wert;
    }

    /**
     * Aendere die Position der Karte.
     */
    public void setzePosition(int neuePositionX, int neuePositionY)
    {
        positionX = neuePositionX;
        positionY = neuePositionY;
        
        kartenrechteck.setzePosition(positionX, positionY);
        aufdruck.setzePosition(positionX + 25, positionY + 50);        
    }

    /**
     * Pruefe, ob ein Punkt in dieser Karte enthalten ist.
     */
    public boolean enthaelt(Point2D punkt)
    {
        return kartenrechteck.enthaelt(punkt);
    }
    
    /**
     * Beschreibe die Karte als Zeichenkette.
     */
    public String toString()
    {
        return farbe + " " + wert;
    }
}
