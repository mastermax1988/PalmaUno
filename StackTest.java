

public class StackTest
{
    public void test()
    {
        KartenstapelStack ks = new KartenstapelStack();
        
        ks.legeAb(new Karte(Farbe.BLAU,Wert.DREI, "grau"));
        ks.legeAb(new Karte(Farbe.GELB,Wert.VIER, "grau"));
        ks.legeAb(new Karte(Farbe.GRUEN,Wert.DREI, "grau"));
        ks.legeAb(new Karte(Farbe.GRUEN,Wert.VIER, "grau"));
        ks.legeAb(new Karte(Farbe.GRUEN,Wert.FUENF, "grau"));
        ks.legeAb(new Karte(Farbe.GRUEN,Wert.SECHS, "grau"));
        ks.legeAb(new Karte(Farbe.GRUEN,Wert.SIEBEN, "grau"));
        ks.legeAb(new Karte(Farbe.GRUEN,Wert.ACHT, "grau"));
        System.out.println(ks.toString());
        ks.mische();
        System.out.println(ks.toString());
        ks.legeAb(new Karte(Farbe.ROT,Wert.NULL, "grau"));
        ks.legeAb(new Karte(Farbe.GRUEN,Wert.NULL, "grau"));
        System.out.println(ks.toString());
        ks.ziehe();
        ks.ziehe();
        System.out.println(ks.toString());
        System.out.println(ks.gibKartenzahl() + " Karten noch im Stapel");
    }
}
