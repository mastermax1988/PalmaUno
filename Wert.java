/**
 * Der Wert einer Spielkarte.
 * 
 * @author NQ
 * @version 2020-02-19
 */
public enum Wert
{
    /*
     * Die Kartenwerte mit ihren Aufdrucken.
     */
    NULL("0"),
    EINS("1"),
    ZWEI("2"),
    DREI("3"),
    VIER("4"),
    FUENF("5"),
    SECHS("6"),
    SIEBEN("7"),
    ACHT("8"),
    NEUN("9"),
    RICHTUNGSWECHSEL("-> <-"),
    AUSSETZEN("X"),
    ZIEHEN_2("+2");
        
    /*
     * Der Aufdruck, der dem Kartenwert entspricht.
     */
    private String aufdruck;
    
    /**
     * Konstruktor.
     * Erzeuge einen Wert mit dem gewuenschten Aufdruck.
     */
    Wert(String gewuenschterAufdruck)
    {
        aufdruck = gewuenschterAufdruck;
    }
    
    /**
     * Nenne den Aufdruck.
     */
    public String gibAufdruck ()
    {
        return aufdruck;
    }   
}
