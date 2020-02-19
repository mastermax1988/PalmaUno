import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Kurzdemonstration zu den Uno-Karten.
 * Man kann das komplette Kartenspiel anschauen und wenden.
 * 
 * @author NQ
 * @version 2020-02-19
 */
public class Demo implements MouseListener
{
    /*
     * Die Spielkarten.
     */
    private Karte[][] karte;
    
    /*
     * Die Anzahl der Farben und Werte.
     */
    int anzahlFarben;
    int anzahlWerte;
    
    /**
     * Konstruktor.
     * Erzeuge die Karten und warte auf Mausereignisse der Leinwand.
     */
    public Demo()
    {
        Leinwand.erzeugeLeinwand ("Demo", 1200, 800, Color.white);
        
        anzahlFarben = Farbe.values().length;
        anzahlWerte  = Wert.values().length;
        
        karte = new Karte[anzahlFarben][anzahlWerte];
        
        int nrFarbe = 0;
        
        for (Farbe farbe: Farbe.values ())
        {
            int nrWert = 0;
            
            for (Wert wert: Wert.values ())
            {
                karte[nrFarbe][nrWert] = new Karte(farbe, wert, "grau");
                
                karte[nrFarbe][nrWert].setzePosition(65*nrWert, 105*nrFarbe);
                karte[nrFarbe][nrWert].werdeSichtbar();
                
                nrWert ++;
            }
            
            nrFarbe++;
        }

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
        for (int nrFarbe = 0; nrFarbe < anzahlFarben; nrFarbe++)
        {
            for (int nrWert = 0; nrWert < anzahlWerte; nrWert++)
            {
                if (karte[nrFarbe][nrWert].enthaelt(ereignis.getPoint()))
                {
                    if (karte[nrFarbe][nrWert].istAufgedeckt())
                    {
                        karte[nrFarbe][nrWert].deckeZu();
                    }
                    else
                    {
                        karte[nrFarbe][nrWert].deckeAuf();
                    }
                }
            }
        }
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

