import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * <p>Leinwand ist eine Klasse, die einfache Zeichenoperationen auf einer
 * leinwandartigen Zeichenflaeche ermoeglicht. Sie ist eine vereinfachte Version
 * der Klasse Canvas (englisch fuer Leinwand) des JDK und wurde speziell fuer das
 * Projekt "Figuren" geschrieben.</p>
 * 
 * <p>In der erweiterten Fassung kann die Leinwand zusaetzlich auch Texte darstellen
 * und Tastaturbefehle abfragen. Dadurch ist es moeglich, einfache Spiele auf der
 * Leinwand zu programmieren. Die Farbpalette der Methode setzeZeichenfarbe ()
 * wird schrittweise erweitert, um den Anwendungsbeduerfnissen gerecht zu werden.</p>
 * 
 * @author Bruce Quig
 * @author Michael Koelling (mik)
 * @author Axel Schmolitzky
 * 
 * @author Bernd Gramlich
 * @author Anja Sundhaus
 * @author Stefanie E.
 * 
 * @version 2019-12-15
 */
public class Leinwand
{
    // Hinweis: Die Implementierung dieser Klasse (insbesondere die
    // Verwaltung der Farben und Identitaeten der Figuren) ist etwas
    // komplizierter als notwendig. Dies ist absichtlich so, weil damit
    // die Schnittstellen und Exemplarvariablen der Figuren-Klassen
    // fuer den Lernanspruch dieses Projekts einfacher und klarer
    // sein koennen.

    private static Leinwand leinwandSingleton;

    /**
     * Gib die einzige verfuegbare Leinwand zurueck (also den
     * "leinwandSingleton"). Wenn die Leinwand noch nicht
     * erzeugt ist, erzeuge sie mit Defaultwerten fuer Titel,
     * Breite, Hoehe und Grundfarbe.
     */
    public static Leinwand gibLeinwand ()
    {
        if (leinwandSingleton == null)
        {
            leinwandSingleton = erzeugeLeinwand ("Leinwand", 800, 600, Color.white);
        }
        return leinwandSingleton;
    }

    /**
     * Erzeuge die einzig verfuegbare Leinwand (also den
     * "leinwandSingleton"). Wenn die Leinwand bereits erzeugt
     * war, ersetze sie durch eine neue Leinwand.
     * 
     * @param titel
     *            Titel, der im Rahmen der Leinwand angezeigt wird
     * @param breite
     *            die gewuenschte Breite der Leinwand
     * @param hoehe
     *            die gewuenschte Hoehe der Leinwand
     * @param grundfarbe
     *            die Hintergrundfarbe der Leinwand
     */
    public static Leinwand erzeugeLeinwand (String titel, int breite, int hoehe, Color grundfarbe)
    {
        if (leinwandSingleton != null)
        {
            leinwandSingleton.setzeSichtbarkeit (false);
        }
        leinwandSingleton = new Leinwand (titel, breite, hoehe, grundfarbe);
        return leinwandSingleton;
    }

    // ----- Exemplarvariablen -----

    private JFrame fenster;

    private Zeichenflaeche zeichenflaeche;

    private Graphics2D graphic;

    private Color hintergrundfarbe;

    private Image leinwandImage;

    private List<Object> graphikobjekte;

    private List<Object> figuren;

    private List<Object> texte;

    private Map<Object, ShapeMitFarbe> figurZuShape;

    private Map<Object, TextMitFarbeUndRichtung> textZuString;

    private LinkedList<KeyEvent> tastenbefehle;

    private int verschwindezeit;

    private LinkedList<Thread> verschwindeprozesse;

    /**
     * Konstruktor.
     * Erzeuge eine Leinwand.
     * 
     * Der Konstruktor ist private, weil er von aussen nicht aufgerufen
     * werden soll. Stattdessen soll mit gibLeinwand () auf die
     * Leinwand zugegriffen werden.
     * 
     * @param titel
     *            Titel, der im Rahmen der Leinwand angezeigt wird
     * @param breite
     *            die gewuenschte Breite der Leinwand
     * @param hoehe
     *            die gewuenschte Hoehe der Leinwand
     * @param grundfarbe
     *            die Hintergrundfarbe der Leinwand
     */
    private Leinwand (String titel, int breite, int hoehe, Color grundfarbe)
    {
        fenster = new JFrame ();
        zeichenflaeche = new Zeichenflaeche ();
        fenster.setContentPane (zeichenflaeche);
        fenster.setTitle (titel);
        fenster.addKeyListener (new Horcher ());
        zeichenflaeche.setPreferredSize (new Dimension (breite, hoehe));
        hintergrundfarbe = grundfarbe;
        fenster.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        fenster.pack ();
        graphikobjekte = new ArrayList<Object> ();
        figuren = new ArrayList<Object> ();
        figurZuShape = new HashMap<Object, ShapeMitFarbe> ();
        texte = new ArrayList<Object> ();
        textZuString = new HashMap<Object, TextMitFarbeUndRichtung> ();
        tastenbefehle = new LinkedList<KeyEvent> ();
        verschwindezeit = 0;
        verschwindeprozesse = new LinkedList<Thread> ();
        setzeSichtbarkeit (false);
    }

    /**
     * Setze, ob diese Leinwand sichtbar sein soll oder nicht. Wenn die Leinwand
     * sichtbar gemacht wird, wird ihr Fenster in den Vordergrund geholt. Diese
     * Operation kann auch benutzt werden, um ein bereits sichtbares
     * Leinwandfenster in den Vordergrund (vor andere Fenster) zu holen.
     * 
     * @param sichtbar
     *            boolean fuer die gewuenschte Sichtbarkeit: true fuer sichtbar,
     *            false fuer nicht sichtbar.
     */
    public void setzeSichtbarkeit (boolean sichtbar)
    {
        if (graphic == null)
        {
            // erstmaliger Aufruf: erzeuge das Bildschirm-Image und fuelle
            // es mit der Hintergrundfarbe
            Dimension size = zeichenflaeche.getSize ();
            leinwandImage = zeichenflaeche.createImage (size.width, size.height);
            graphic = (Graphics2D) leinwandImage.getGraphics ();
            graphic.setColor (hintergrundfarbe);
            graphic.fillRect (0, 0, size.width, size.height);
            graphic.setColor (Color.black);
        }
        fenster.setVisible (sichtbar);
    }

    /**
     * Lege die Verschwindezeit fest.
     * Nach der angegebenen Zahl von Millisekunden schliesst sich die
     * Leinwand und loescht alle Inhalte.
     * 
     * @param millisec Die Zeit in Millisekunden, nach der die Leinwand
     *                 verschwindet. Falls 0 oder negativ, bleibt die
     *                 Leinwand stabil. Wenn zuvor schon Verschwindeprozesse
     *                 angestossen wurden, wird ihre Verschwindezeit nicht
     *                 mehr nachtraeglich geaendert, es sei denn, der
     *                 Parameter ist 0 oder negativ.
     */
    public void setzeVerschwindezeit (int millisec)
    {
        verschwindezeit = millisec;

        if (verschwindezeit <= 0)
        {
            for (Thread prozess: verschwindeprozesse)
            {
                prozess.stop ();
            }
        }
    }

    /**
     * Merke fuer das gegebene Figur-Objekt eine Java-Figur (einen Shape) auf
     * der Leinwand zum Zeichnen vor.
     * 
     * @param figur
     *            das Figur-Objekt, fuer das ein Shape gezeichnet werden soll
     * @param farbe
     *            die Farbe der Figur
     * @param shape
     *            ein Objekt der Klasse Shape, das tatsaechlich gezeichnet wird
     */
    public synchronized void merkeZumZeichnenVor (Object figur, Color farbe, Shape shape)
    {
        graphikobjekte.remove (figur); // entfernen, falls schon eingetragen
        graphikobjekte.add (figur); // am Ende hinzufuegen
        figuren.remove (figur);
        figuren.add (figur);
        figurZuShape.put (figur, new ShapeMitFarbe (shape, farbe));
    }

    /**
     * Zeichne fuer das gegebene Figur-Objekt eine Java-Figur (einen Shape) auf
     * die Leinwand.
     * 
     * @param figur
     *            das Figur-Objekt, fuer das ein Shape gezeichnet werden soll
     * @param farbe
     *            die Farbe der Figur
     * @param shape
     *            ein Objekt der Klasse Shape, das tatsaechlich gezeichnet wird
     */
    public void zeichne (Object figur, Color farbe, Shape shape)
    {
        merkeZumZeichnenVor (figur, farbe, shape);
        erneuere ();
    }

    /**
     * Merke die gegebene Figur zum Entfernen von der Leinwand vor.
     * 
     * @param figur
     *            die Figur, deren Shape entfernt werden soll
     */
    public synchronized void merkeZumEntfernenVor (Object figur)
    {
        graphikobjekte.remove (figur); // entfernen,falls schon eingetragen
        figurZuShape.remove (figur);
    }

    /**
     * Entferne die gegebene Figur von der Leinwand.
     * 
     * @param figur
     *            die Figur, deren Shape entfernt werden soll
     */
    public void entferne (Object figur)
    {
        merkeZumEntfernenVor (figur);
        erneuere ();
    }

    /**
     * Nenne den Shape zu einer Figur.
     * 
     * @param figur
     *            die Figur, deren Shape genannt werden soll
     */
    public Shape gibForm (Object figur)
    {
        ShapeMitFarbe farbigeFigur = figurZuShape.get (figur);

        if (null == farbigeFigur)
        {
            return null;
        }
        else
        {
            return farbigeFigur.getShape ();    
        }
    }

    /**
     * Merke fuer das gegebene Text-Objekt eine Zeichenkette (einen String) auf
     * der Leinwand zum Schreiben vor.
     * 
     * @param text
     *            das Text-Objekt, fuer das ein String geschrieben werden soll
     * @param farbname
     *            die Farbe des Textes
     * @param zeichenkette
     *            ein Objekt der Klasse String, das tatsaechlich geschrieben wird
     * @param x
     *            x-Koordinate des Textes
     * @param y
     *            y-Koordinate des Textes
     * @param drehX
     *            x-Koordinate des Drehvektors
     * @param drehY
     *            y-Koordinate des Drehvektors
     * @param zentrumX
     *            x-Koordinate des Drehzentrums
     * @param zentrumY
     *            y-Koordinate des Drehzentrums
     * @param ausrichtungX
     *            Referenzpunkt der x-Koordinatenverschiebung (0: Textmitte, 1: rechte Textmitte, -1: linke Textmitte)
     * @param ausrichtungY
     *            Referenzpunkt der y-Koordinatenverschiebung (0: Textmitte, 1: untere Textmitte, -1: obere Textmitte)
     * @param groesse
     *            Schriftgroesse des Textes
     */
    public synchronized void merkeZumSchreibenVor (Object text, Color farbe, String zeichenkette,int x, int y, int drehX, int drehY, int zentrumX, int zentrumY, int ausrichtungX, int ausrichtungY, int groesse)
    {
        graphikobjekte.remove (text); // entfernen, falls schon eingetragen
        graphikobjekte.add (text); // am Ende hinzufuegen
        texte.remove (text);
        texte.add (text);
        textZuString.put (text, new TextMitFarbeUndRichtung (zeichenkette, farbe, x, y, drehX, drehY, zentrumX, zentrumY, ausrichtungX, ausrichtungY, groesse));
    }

    /**
     * Schreibe fuer das gegebene Text-Objekt eine Zeichenkette (einen String) auf
     * die Leinwand.
     * 
     * @param text
     *            das Text-Objekt, fuer das ein String geschrieben werden soll
     * @param farbe
     *            die Farbe des Textes
     * @param zeichenkette
     *            ein Objekt der Klasse String, das tatsaechlich geschrieben wird
     * @param x
     *            x-Koordinate des Textes
     * @param y
     *            y-Koordinate des Textes
     * @param drehX
     *            x-Koordinate des Drehvektors
     * @param drehY
     *            y-Koordinate des Drehvektors
     * @param zentrumX
     *            x-Koordinate des Drehzentrums
     * @param zentrumY
     *            y-Koordinate des Drehzentrums
     * @param ausrichtungX
     *            Referenzpunkt der x-Koordinatenverschiebung (0: Textmitte, 1: rechte Textmitte, -1: linke Textmitte)
     * @param ausrichtungY
     *            Referenzpunkt der y-Koordinatenverschiebung (0: Textmitte, 1: untere Textmitte, -1: obere Textmitte)
     * @param groesse
     *            Schriftgroesse des Textes
     */
    public void schreibe (Object text, Color farbe, String zeichenkette, int x, int y, int drehX, int drehY, int zentrumX, int zentrumY, int ausrichtungX, int ausrichtungY, int groesse)
    {
        merkeZumSchreibenVor (text, farbe, zeichenkette, x, y, drehX, drehY, zentrumX, zentrumY, ausrichtungX, ausrichtungY, groesse);
        erneuere ();
    }

    /**
     * Merke den gegebenen Text zum Radieren von der Leinwand vor.
     * 
     * @param text
     *            der Text, dessen Zeichenkette entfernt werden soll
     */
    public synchronized void merkeZumRadierenVor (Object text)
    {
        graphikobjekte.remove (text); // entfernen,falls schon eingetragen
        textZuString.remove (text);
    }

    /**
     * Radiere den gegebenen Text von der Leinwand.
     * 
     * @param text
     *            der Text, dessen Zeichenkette entfernt werden soll
     */
    public void radiere (Object text)
    {
        merkeZumRadierenVor (text);
        erneuere ();
    }

    /**
     * Setze die Zeichenfarbe der Leinwand.
     * 
     * @param farbe
     *            die neue Zeichenfarbe.
     */
    private void setzeZeichenfarbe (Color farbe)
    {
        if (farbe != null)
        {
            graphic.setColor (farbe);
        }
        else
        {
            graphic.setColor (Color.black); // ungueltige Farben werden als Schwarz interpretiert.
        }
    }

    /**
     * Gib der Leinwand eine neue Ueberschrift.
     * 
     * @param ueberschrift
     *            die neue Ueberschrift.
     */
    public void setzeUeberschrift (String ueberschrift)
    {
        fenster.setTitle (ueberschrift);
    }

    /**
     * Warte fuer die angegebenen Millisekunden. Mit dieser Operation wird eine
     * Verzoegerung definiert, die fuer animierte Zeichnungen benutzt werden kann.
     * 
     * @param millisekunden
     *            die zu wartenden Millisekunden
     */
    public static void warte (int millisekunden)
    {
        try
        {
            Thread.sleep (millisekunden);
        }
        catch (Exception e)
        {
            // Exception ignorieren
        }
    }

    /**
     * Zeichne erneut alle Figuren und Texte auf der Leinwand.
     */
    public synchronized void erneuere ()
    {
        loesche ();

        for (Object graphikobjekt: graphikobjekte)
        {
            if (figuren.contains (graphikobjekt))
            {
                figurZuShape.get (graphikobjekt).draw (graphic);
            }
            else if (texte.contains (graphikobjekt))
            {
                textZuString.get (graphikobjekt).write (graphic);
            }
        }

        zeichenflaeche.repaint ();
        leinwandSingleton.setzeSichtbarkeit (true);

        if (verschwindezeit > 0)
        {
            Thread prozess = new Thread (new Verschwinder (verschwindezeit));            
            verschwindeprozesse.add (prozess);
            prozess.start ();
        }
    }

    /**
     * Loesche die gesamte Leinwand.
     */
    private void loesche ()
    {
        Color original = graphic.getColor ();
        graphic.setColor (hintergrundfarbe);
        Dimension size = zeichenflaeche.getSize ();
        graphic.fill (new Rectangle(0, 0, size.width, size.height));
        graphic.setColor (original);
    }

    /**
     * Loesche die gesamte Leinwand und ihre Daten.
     */
    public synchronized void loescheAlles ()
    {
        loesche ();

        graphikobjekte.clear ();
        figurZuShape.clear ();
        textZuString.clear ();
        tastenbefehle.clear ();
        verschwindeprozesse.clear ();
    }

    /**
     * Gib den letzten, noch nicht verarbeiteten Tastendruck zurueck,
     * den die Leinwand empfangen hat. Falls kein Tastendruck vorkam,
     * wird null zurueckgegeben.
     */
    public KeyEvent gibTastendruck ()
    {
        warte(5);
        if (tastenbefehle.size () == 0)
        {
            return null;
        }
        else
        {
            KeyEvent tastenbefehl = tastenbefehle.getFirst ();
            tastenbefehle.removeFirst ();
            return tastenbefehl;
        }
    }

    /**
     * Nenne die Breite der Zeichenflaeche.
     */
    public int gibBreite ()
    {
        Dimension size = zeichenflaeche.getSize ();
        return size.width;
    }

    /**
     * Nenne die Hoehe der Zeichenflaeche.
     */
    public int gibHoehe ()
    {
        Dimension size = zeichenflaeche.getSize ();
        return size.height;
    }

    /**
     * Nenne das Fenster, auf dem die Leinwand liegt.
     */
    public JFrame gibFenster ()
    {
        return fenster;
    }

    /**
     * Nenne die Zeichenflaeche der Leinwand.
     */
    public JPanel gibZeichenflaeche ()
    {
        return zeichenflaeche;
    }

    /***************************************************************************
     * Interne Klasse Zeichenflaeche - die Klasse fuer die GUI-Komponente, die
     * tatsaechlich im Leinwand-Fenster angezeigt wird. Diese Klasse definiert
     * ein JPanel mit der zusaetzlichen Moeglichkeit, das auf ihm gezeichnet Image
     * aufzufrischen (erneut zu zeichnen).
     */
    private class Zeichenflaeche extends JPanel
    {
        private static final long serialVersionUID = 20060330L;

        public void paint (Graphics g)
        {
            g.drawImage (leinwandImage, 0, 0, null);
        }
    }

    /***************************************************************************
     * Interne Klasse ShapeMitFarbe - Da die Klasse Shape des JDK nicht auch
     * eine Farbe mitverwalten kann, muss mit dieser Klasse die Verknuepfung
     * modelliert werden.
     */
    private class ShapeMitFarbe
    {
        private Shape shape;

        private Color farbe;

        public ShapeMitFarbe (Shape shape, Color farbe)
        {
            this.shape = shape;
            this.farbe = farbe;
        }

        public Shape getShape ()
        {
            return shape;
        }

        public void draw (Graphics2D graphic)
        {
            setzeZeichenfarbe (farbe);
            graphic.fill (shape);
        }
    }

    /***************************************************************************
     * Interne Klasse TextMitFarbeUndRichtung - Da die Klasse String des JDK
     * nicht auch eine Farbe und Drehrichtung mitverwalten kann, muss mit dieser
     * Klasse die Verknuepfung modelliert werden.
     */
    private class TextMitFarbeUndRichtung
    {
        private String zeichenkette;

        private Color farbe;

        private int x, y;
        private int drehX, drehY;
        private int zentrumX, zentrumY;
        private int ausrichtungY, ausrichtungX;
        private int groesse;

        public TextMitFarbeUndRichtung (String zeichenkette, Color farbe, int x, int y, int drehX, int drehY, int zentrumX, int zentrumY, int ausrichtungX, int ausrichtungY, int groesse)
        {
            this.zeichenkette = zeichenkette;
            this.farbe = farbe;
            this.x = x;
            this.y = y;
            this.drehX = drehX;
            this.drehY = drehY;
            this.zentrumX = zentrumX;
            this.zentrumY = zentrumY;
            this.ausrichtungY = ausrichtungY;
            this.ausrichtungX = ausrichtungX;
            this.groesse = groesse;
        }

        public void write (Graphics2D graphic) 
        {
            Font f = new Font ("Serif", 0, groesse);
            AffineTransform affinitaet = graphic.getTransform ();
            AffineTransform drehung = AffineTransform.getRotateInstance (drehX, drehY, zentrumX, zentrumY);

            String[] einzelkette = zeichenkette.split ("\n");
            Rectangle2D[] rand = new Rectangle2D[einzelkette.length];

            double maxX    = 0;
            double maxY    = 0;
            double abstand = graphic.getFontMetrics().getHeight() / 10;

            for (int i = 0; i < einzelkette.length; i++)
            {
                rand[i] = new TextLayout (einzelkette[i], f, graphic.getFontRenderContext ()).getBounds ();

                maxX = Math.max (maxX, rand[i].getWidth  ());
                maxY = Math.max (maxY, rand[i].getHeight ());
            }
            
            double hoehe   = einzelkette.length * (maxY + abstand) - abstand;

            graphic.transform (drehung);
            setzeZeichenfarbe (farbe);
            graphic.setFont (f);

            for (int i = 0; i < einzelkette.length; i++)
            {
                int dx;
                int dy;
                
                double darueber = (i+1)*(maxY + abstand) - abstand;

                switch (ausrichtungY)
                {
                    case  1: dy = (int) Math.round (darueber); break;
                    case -1: dy = (int) Math.round (darueber - hoehe); break;
                    default: dy = (int) Math.round (darueber - hoehe / 2); break;
                }

                switch (ausrichtungX)
                {
                    case  1: dx = 0; break;
                    case -1: dx = (int) Math.round (rand[i].getWidth ()); break;
                    default: dx = (int) Math.round (rand[i].getWidth () / 2); break;
                }

                graphic.drawString (einzelkette[i], zentrumX + x - dx, zentrumY + y + dy);
            }

            graphic.setTransform (affinitaet);
        }
    }

    /****************************************************************************
     * Interne Klasse Horcher - Speichert alle Tastaturereignisse in der Schlange
     * tastenbefehle
     */
    private class Horcher extends KeyAdapter
    {
        public void keyPressed (KeyEvent taste)
        {
            tastenbefehle.addLast (taste);
        }
    }

    /*****************************************************************************
     * Interne Klasse Verschwinder - Laesst die Leinwand nach der angegebenen Zahl
     * von Millisekunden verschwinden.
     */
    private class Verschwinder implements Runnable
    {
        int verschwindezeit;

        public Verschwinder (int millisec)
        {
            verschwindezeit = millisec;
        }

        public void run ()
        {
            warte (verschwindezeit);
            loescheAlles ();
            setzeSichtbarkeit (false);
        }
    }
}