
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * Write a description of class Spielleiter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Spielleiter implements MouseListener
{
    Spieler spieler[];
    KartenstapelStack ablagestapel, ziehstapel;
    int anzahlFarben;
    int anzahlWerte;
    public Spielleiter()
    {
        spieler = new Spieler[1];
        spieler[0] = new MenschlicherSpieler();
        ziehstapel = new KartenstapelStack();
        
        Leinwand.erzeugeLeinwand ("Demo", 1200, 800, Color.white);
        
        anzahlFarben = Farbe.values().length;
        anzahlWerte  = Wert.values().length;
               
        int nrFarbe = 0;
        
        for (Farbe farbe: Farbe.values ())
        {
            int nrWert = 0;
            
            for (Wert wert: Wert.values ())
            {
                ziehstapel.legeAb(new Karte(farbe, wert, "grau"));
                nrWert ++;
            }
            
            nrFarbe++;
        }
        ziehstapel.mische();
        for(Spieler s : spieler)
            s.zieheKarte(7);
        System.out.println(spieler[0].nenneKarten());
        Leinwand.gibLeinwand ().gibZeichenflaeche ().addMouseListener (this);
    }
    /**
     * Reagiere auf das Klicken einer Maustaste.
     */
    public void mouseClicked (MouseEvent ereignis)
    {
    }

    /**
     * Reagiere auf das Druecken einer Maustaste.
     */
    public void mousePressed (MouseEvent ereignis)
    {
    }

    /**
     * Reagiere auf das Loslassen einer Maustaste.
     */
    public void mouseReleased (MouseEvent ereignis)
    {

    }

    /**
     * Reagiere darauf, dass der Mauszeiger die Leinwand betritt.
     */
    public void mouseEntered (MouseEvent ereignis)
    {
    }

    /**
     * Reagiere darauf, dass der Mauszeiger die Leinwand verlaesst.
     */
    public void mouseExited (MouseEvent ereignis)
    {
    }
    
}
