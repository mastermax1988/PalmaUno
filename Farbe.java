/**
 * Die Farbe einer Spielkarte.
 * 
 * @author NQ
 * @version 2020-02-19
 */
public enum Farbe
{
    /*
     * Die Farbnamen mit ihren Zeichenfarben.
     */
    GELB("gelb"),
    GRUEN("gruen"),
    ROT("rot"),
    BLAU("blau");
    
    /*
     * Die Farbe, in der die Karte gezeichnet wird.
     */
    private String zeichenfarbe;
    
    /**
     * Konstruktor.
     * Erzeuge eine Farbe mit ihrer gewuenschten Zeichenfarbe.
     */
    Farbe(String gewuenschteZeichenfarbe)
    {
        zeichenfarbe = gewuenschteZeichenfarbe;
    }
    
    /**
     * Nenne die Zeichenfarbe.
     */
    public String gibZeichenfarbe()
    {
        return zeichenfarbe;
    }
}
