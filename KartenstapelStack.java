
import java.util.*;
/**
 * Write a description of class KartenstapelStack here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class KartenstapelStack implements Kartenstapel
{
    Stack stack;
    Random rnd;
    public KartenstapelStack()
    {
        stack = new Stack();
        rnd = new Random();
    }
    /**
     * Pruefe, ob der Stapel leer ist.
     * 
     * @return True genau dann, wenn der Stapel leer ist.
     */
    public boolean istLeer()
    {
        return stack.empty();
    }
    
    /**
     * Nenne die Anzahl der Karten im Stapel.
     * 
     * @return Die Anzahl der im Stapel verbliebenen Karten.
     */
    public int gibKartenzahl()
    {
        return stack.size();
    }
    
    /**
     * Ziehe die oberste Karte.
     * 
     * @return Die bisherige oberste Karte des Stapels (oder null, falls
     *         der Stapel leer ist).
     */
    public Karte ziehe()
    {
        return (Karte) stack.pop();
    }
    
    /**
     * Pruefe, ob die angegebene Karte laut Spielregeln auf den Stapel passt.
     * 
     * @param karte Die zu pruefende Karte.
     * 
     * @return True genau dann, wenn die Karte auf den Stapel abgelegt werden
     *         darf.
     */
    public boolean passtZu(Karte karte)
    {
        if(stack.empty())
            return true;
        Karte k = (Karte) stack.peek();
        return (k.gibFarbe().equals(karte.gibFarbe()) || k.gibWert().equals(karte.gibWert()));
    }
    
    /**
     * Lege die angegebene Karte auf den Stapel ab.
     * 
     * @param karte Die abzulegende Karte.
     */
    public boolean legeAb(Karte karte)
    {
        if(passtZu(karte))
        {
            stack.push(karte);
            return true;
        }
        return false;
    }
    
    /**
     * Mische den Kartenstapel.
     */
    public void mische()
    {
        ArrayList<Karte> temp = new ArrayList<Karte>();
        while(!stack.empty())
        {
            temp.add((Karte) stack.pop());
        }//jetzt ist der Stack leer
        while(temp.size()>0)
        {
            int index = rnd.nextInt(temp.size());
            stack.push(temp.get(index));
            temp.remove(index);
        }
    }
    
    /**
     * Beschreibe den Inhalt des Kartenstapels als Zeichenkette.
     * 
     * @return Alle Karten des Stapels als Zeichenkette.
     */
    public String toString()
    {
        return stack.toString();
    }
}
