package tra1.vk5.xtehtava;

import fi.uef.cs.tra.BTree;
import fi.uef.cs.tra.BTreeNode;

import java.util.*;

/**
 * Testausluokka TRA I tehtÃ¤vÃ¤Ã¤n X5.
 *
 */
public class TRAI_20_X5_testi {

    static Random rnd = new Random();

    static TRAI_20_X5 testattava = new TRAI_20_X5_tommpuur(); /* <-- Oma tunnus tÃ¤hÃ¤n */


    public static void main(String[] args) {

        // alkioiden mÃ¤Ã¤rÃ¤
        int N = 10;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        // satunnaislukusiemen
        int siemen = 42;
        if (args.length > 1)
            siemen = Integer.parseInt(args[1]);

        rnd.setSeed(siemen);

        // tulostusten mÃ¤Ã¤rÃ¤, kasvata tai vÃ¤hennÃ¤ mielesi mukaan
        int print = 3;
        if (args.length > 2)
            print = Integer.parseInt(args[2]);

        boolean ok = true;


        ok &= testaaX5(N, print);

        ok &= testaaX5(0, print);
        ok &= testaaX5(1, print);
        ok &= testaaX5(2, print);
        ok &= testaaX5(2, print);
        ok &= testaaX5(2, print);
        ok &= testaaX5(3, print);
        ok &= testaaX5(3, print);
        ok &= testaaX5(4, print);

        ok &= testaaX5(N*2, print);
        ok &= testaaX5(N*4, print);
        ok &= testaaX5(N*8, print);
        ok &= testaaX5(N*16, print);


        System.out.println("=== laitetaan vielÃ¤ joka kerta vaihtuva satunnaissiemen");
        rnd.setSeed(System.currentTimeMillis());
        System.out.println("\n== testataan monta satunnaista syÃ¶tettÃ¤");
        int nTest = 1000;
        int k = 0;
        int virheet = 0;
        for (k = 0; k < nTest; k++) {
            if (!testaaX5(rnd.nextInt(k+1), 0)) {
                virheet++;
            }
            if (virheet >= 30) {
                k++;
                break;
            }
        }
        if (virheet > 0)
            ok &= false;
        System.out.println("\n" + k + " testistÃ¤ " + (k - virheet) + " oikein.");

        if (ok)
            System.out.println("\nKaikki tehdyt testit antoivat oikean tuloksen.\nMuista myÃ¶s itsearviointi ja aikavaativuus.");
        else
            System.out.println("\nJoissain testeissÃ¤ virheitÃ¤.");


    }


    /**
     * testaa X5:sta n kokoisella syÃ¶tteellÃ¤
     * @param n syÃ¶tekoko
     * @param print tulostusten mÃ¤Ã¤rÃ¤
     * @return true testi onnistui, muuten false
     */
    static boolean testaaX5(int n, int print) {
        ArrayList<Integer> A = satunnainenLista(n, n*3);
        return testaaX5(A, print);
    }

    /**
     * Testaa lisÃ¤ystÃ¤ viemÃ¤llÃ¤ puuhun syoteLista:n alkiot ja kÃ¤ymÃ¤llÃ¤ puun lÃ¤pi X5:tÃ¤ kÃ¤yttÃ¤en
     * @param syoteLista syÃ¶tetaulukko
     * @param print tulostusten mÃ¤Ã¤rÃ¤
     * @return true testi onnistui, muuten false
     */
    static <E extends Comparable<? super E>> boolean testaaX5(ArrayList<E> syoteLista, int print) {
        
        boolean ok = true;
        int na = syoteLista.size();
        BTree<E> T = new BTree<E>();
        
        if (print > 1) System.out.println("\nViedÃ¤Ã¤n puuhun alkiot (" + na + " kpl) : " + syoteLista);
        for (E x : syoteLista) {
            sisaLisaa(T, x);
        }
        if (print > 2) {
            System.out.print("Puu sisÃ¤jÃ¤rjestyksessÃ¤:");
            sisaTulosta(T);
        }

        // kÃ¤ydÃ¤Ã¤n puu lÃ¤pi, eli kutsutaan X5 metodeja

        ArrayList<E> puunSisalto = new ArrayList<E>(na);
        
        if (print > 0) System.out.print("Testataan lÃ¤pikÃ¤ynti X5:llÃ¤:");
        int tn = 0;
        BTreeNode<E> n = testattava.sisaEnsimmainen(T);
        while (n != null) {
            E x = n.getElement();
            tn++;
            if (print > 2) System.out.print(" " + x);
            puunSisalto.add(x);
            n = testattava.sisaSeuraaja(n);
        }
        if (print > 0) System.out.println(" (" + tn + " kpl)");

        // lajitellaan alkuperÃ¤inen syoteLista ja verrataan lÃ¤pikÃ¤ynnin tuloksen kÃ¤Ã¤nteiseen jÃ¤rjestykseen
        Collections.sort(syoteLista);
        ok = samat(syoteLista, puunSisalto);
        if (! ok)
            if (print > 1) System.out.println("Eri alkiot tai ei tasan kÃ¤Ã¤nteinen jÃ¤rjestys");
        else
            if (print > 1) System.out.println("Samat tuli, hienoa");
        
        return ok;

    }

    /**
     * LisÃ¤Ã¤ alkion sisÃ¤jÃ¤rjestettyyn binÃ¤Ã¤ripuuhun jollei sitÃ¤ ennestÃ¤Ã¤n ollut.
     * @param T Puu
     * @param x lisÃ¤ttÃ¤vÃ¤ alkio
     * @param <E> alkioiden tyyppi
     * @return true jos lisÃ¤ys tehtiin, muuten false
     */
    static <E extends Comparable<? super E>> boolean sisaLisaa(BTree<E> T, E x) {

        BTreeNode<E> n = T.getRoot();
        if (n == null) {
            T.setRoot(new BTreeNode<E>(x));
            return true;
        }

        while (true) {

            if (x.compareTo(n.getElement()) == 0)
                // x jo puussa, ei lisata
                return false;

            else if (x.compareTo(n.getElement()) < 0) {
                // x edeltaa n:n alkiota
                if (n.getLeftChild() == null) {
                    // lisayskohta loydetty
                    n.setLeftChild(new BTreeNode<E>(x));
                    return true;
                } else
                    n = n.getLeftChild();
            } else {
                // x suurempi kuin n
                if (n.getRightChild() == null) {
                    // lisayskohta loydetty
                    n.setRightChild(new BTreeNode<E>(x));
                    return true;
                } else
                    n = n.getRightChild();
            }
        } // while

    }

    /**
     * tulostaa puun solmujen alkiot sisÃ¤jÃ¤rjestyksessÃ¤
     * @param T
     */
    static void sisaTulosta(BTree T) {
        sisaTulosta(T.getRoot());
        System.out.println();
    }


    static void sisaTulosta(BTreeNode n) {
        if (n == null)
            return;

        sisaTulosta(n.getLeftChild());
        System.out.print(" " + n.getElement());
        sisaTulosta(n.getRightChild());

    }




    /**
     * Tarkastaa onko listoissa samat alkiot samassa jÃ¤rjestyksessÃ¤
     * @param A lista1
     * @param B lista2
     * @return true jos samat alkiot, muuten false
     */
        public static boolean samat(List A, List B) {
        ListIterator iA = A.listIterator();
        ListIterator iB = B.listIterator();
        while (iA.hasNext() && iB.hasNext()) {
            if (! iA.next().equals(iB.next())) {
                return false;
            }

        }
        if (iA.hasNext() || iB.hasNext())
            return false;
        else
            return true;
    }


    /**
     * Generoi satunnaisen n kokoisen listan jossa alkiot ovat uniikkeja
     * Alkiot 1..max.
     * @param n alkioiden mÃ¤Ã¤rÃ¤
     * @param max suurin alkio
     * @return uusi lista.
     */
    static ArrayList<Integer> satunnainenLista(int n, int max) {
        return  satunnainenLista(n, 1, max);
    }

    /**
     * Generoi satunnaisen n kokoisen listan jossa alkiot ovat uniikkeja
     * Alkiot min..max ovat uniikkeja.
     * @param n alkioiden mÃ¤Ã¤rÃ¤
     * @param max suurin alkio
     * @return uusi lista.
     */
    static ArrayList<Integer> satunnainenLista(int n, int min, int max) {
        ArrayList<Integer> tulos = new ArrayList<Integer>(n);
        int diff = max-min+1;
        if (diff < 1)
            diff = 1;

        HashSet<Integer> hs = new HashSet<>(n*2);

        int kierr = n*20;
        int i = 0;
        while (tulos.size() < n && kierr > 0) {
            Integer x = rnd.nextInt(diff) + min;
            if (hs.add(x))
                tulos.add(x);
            kierr--;
        }
        if (tulos.size() < n)
            System.out.println("satunnainenLista: varoitus vain " + tulos.size() + " alkiota tehtiin vaikka "
            + n + " pyydettiin, min ja max liian lÃ¤hellÃ¤ toisiaan.");

        return tulos;
    }



}
