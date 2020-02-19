/**
 * Schnittstelle eines Kartenstapels fuer ein Uno-Spiel.
 * 
 * @author NQ 
 * @version 2020-02-19
 */
public interface Kartenstapel
{
    /**
     * Pruefe, ob der Stapel leer ist.
     * 
     * @return True genau dann, wenn der Stapel leer ist.
     */
    boolean istLeer();
    
    /**
     * Nenne die Anzahl der Karten im Stapel.
     * 
     * @return Die Anzahl der im Stapel verbliebenen Karten.
     */
    int gibKartenzahl();
    
    /**
     * Ziehe die oberste Karte.
     * 
     * @return Die bisherige oberste Karte des Stapels (oder null, falls
     *         der Stapel leer ist).
     */
    Karte ziehe();
    
    /**
     * Pruefe, ob die angegebene Karte laut Spielregeln auf den Stapel passt.
     * 
     * @param karte Die zu pruefende Karte.
     * 
     * @return True genau dann, wenn die Karte auf den Stapel abgelegt werden
     *         darf.
     */
    boolean passtZu(Karte karte);
    
    /**
     * Lege die angegebene Karte auf den Stapel ab.
     * 
     * @param karte Die abzulegende Karte.
     */
    boolean legeAb(Karte karte);
    
    /**
     * Mische den Kartenstapel.
     */
    void mische();
    
    /**
     * Beschreibe den Inhalt des Kartenstapels als Zeichenkette.
     * 
     * @return Alle Karten des Stapels als Zeichenkette.
     */
    String toString();
}