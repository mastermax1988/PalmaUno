
import java.util.*;
/**
 * Abstract class Spieler - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Spieler
{
    ArrayList<Karte> karten;
    KartenstapelStack ablagestapel, ziehstapel;
    Spieler(KartenstapelStack a, KartenstapelStack z)
    {
        ablagestapel = a;
        ziehstapel = z;
        karten=new ArrayList<Karte>();
    }
    void zieheKarte(int Anzahl)
    {
        for(int i=0;i<Anzahl;i++)
            karten.add(ziehstapel.ziehe());
    }
    String nenneKarten()
    {
       return karten.toString(); 
    }
}
