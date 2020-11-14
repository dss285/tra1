package vk5;// TRAI_20_t19_21.java.java SJ

/**
 * 27. Kirjoita algoritmi joka laskee annetun binÃ¤Ã¤ripuun korkeuden, ts. pisimmÃ¤n polun juuresta
 * lehtisolmuun. Aikavaativuus? Vihje: rekursio.
 * 28. Kirjoita algoritmi joka etsii binÃ¤Ã¤ripuun matalimman (vÃ¤hiten syvÃ¤n) lehtisolmun. Vihje:
 * tasoittainen lÃ¤pikÃ¤ynti. Aikavaativuus?
 * 29. Kirjoita algoritmi joka tarkistaa onko annettu binÃ¤Ã¤ripuu sisÃ¤jÃ¤rjestyksessÃ¤. Jos binÃ¤Ã¤ripuus-
 * sa on alkioita jotka eivÃ¤t ole keskenÃ¤Ã¤n oikeassa jÃ¤rjestyksessÃ¤, algoritmi palauttaa epÃ¤toden,
 * muuten toden. Parametrina binÃ¤Ã¤ripuu, palautusarvona totuusarvo. MikÃ¤ on algoritmisi
 * aikavaativuus?
 **/

import fi.uef.cs.tra.BTree;
import fi.uef.cs.tra.BTreeNode;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class TRAI_20_t19_21_pohja {

    public static void main(String[] args) {

        BTree<Integer> puu = null;

        int N = 15;
        if (args.length > 0)
            N = Integer.valueOf(args[0]);


        // testataan ensin vakiopuulla

        puu = exampleBTree();
        System.out.println("SisÃ¤jÃ¤rjestyksessÃ¤:");
        inorderPrint(puu);

        System.out.println("Korkeus = " + korkeus(puu));

        System.out.println("Matalin yksilapsinen solmu = " + matalinYksilapsinen(puu).getElement());

        BTree<Integer> puu2 = exampleBTree();

        System.out.println("Samat rakenteet : " + vertaaRakenne(puu, puu2));

        System.out.println("LisÃ¤tÃ¤Ã¤n: 7");
        inorderInsert(puu, 7);
        System.out.println("Samat rakenteet : " + vertaaRakenne(puu, puu2));

        System.out.println();


        // tehdÃ¤Ã¤n vielÃ¤ uusi generoitu

        puu = new BTree<Integer>();


        System.out.println("\nPuuhun:");
        Random r = new Random(42);
        Integer x = null;
        for (int i = 0; i < N; i++) {
            x = r.nextInt(N * 2);
            System.out.print(x + " ");
            inorderInsert(puu, x);
        }
        System.out.println();

        System.out.println("SisÃ¤jÃ¤rjestyksessÃ¤:");
        inorderPrint(puu);

        System.out.println("Korkeus = " + korkeus(puu));

        System.out.println("Matalin yksilapsinensolmu = " + matalinYksilapsinen(puu).getElement());

    } // main()
    /**
     * 19. Puun korkeus. KÃ¤ynnistysmetodi
     *
     * @param t tarkasteltava binÃ¤Ã¤ripuu.
     * @return puun korkeus tai -1 jos puu on tyhjÃ¤
     **/
    public static <E> int korkeus(BTree<E> t) {
        // TODO
        if (t.isEmpty()) {
            return -1;
        }
        BTreeNode<E> n = t.getRoot();
        int arvo1 = 0;
        int arvo2 = 0;
        if(n.getLeftChild() != null) {
            BTree<E> puu = new BTree<>(n.getLeftChild());
            arvo1 += 1+korkeus(puu);
        }
        if(n.getRightChild() != null) {
            BTree<E> puu = new BTree<>(n.getRightChild());
            arvo2 += 1+korkeus(puu);
        }
        if(arvo1 > arvo2) {
            return arvo1;
        } else {
            return arvo2;
        }
    }

    /**
     * 20. Kirjoita algoritmi joka etsii binÃ¤Ã¤ripuun matalimman (vÃ¤hiten syvÃ¤n) sellaisen solmun jolla
     * on tasan yksi lapsi. Vihje: tasoittainen lÃ¤pikÃ¤ynti. Aikavaativuus?
     *
     * @param T   binÃ¤Ã¤ripuu
     * @param <E> alkiotyyppi (ei kÃ¤ytÃ¶ssÃ¤)
     * @return matalin yksilapsinen solmu tai null jollei sellaista ole
     **/
    public static <E> BTreeNode<E> matalinYksilapsinen(BTree<E> T) {

        // TODO
        BTreeNode<E> n = T.getRoot(), n1, n2;
        if(n == null)
            return n;
        int korkeus = korkeus(T), n1korkeus, n2korkeus;
        if(yksiLapsinen(n)) {
            return n;
        } else {
            n1 = matalinYksilapsinen(new BTree<>(n.getLeftChild()));
            n2 = matalinYksilapsinen(new BTree<>(n.getRightChild()));
            if(n1 != null && n2 != null) {
                n1korkeus = korkeus(new BTree<>(n1));
                n2korkeus = korkeus(new BTree<>(n2));
                if (n1korkeus > n2korkeus) {
                    return n1;
                } else {
                    return n2;
                }
            } else if(n1 != null && n2 == null) {
                return n1;
            } else if(n1 == null && n2 != null) {
                return n2;
            } else {
                return null;
            }
        }
    } // matalinYksilapsinen()
    public static <E> boolean yksiLapsinen(BTreeNode<E> n) {
        if(n == null)
            return false;
        if(n.getRightChild() == null && n.getLeftChild() != null && onkoLehtisolmu(n.getLeftChild()))
            return true;
        if(n.getLeftChild() == null && n.getRightChild() != null && onkoLehtisolmu(n.getRightChild()))
            return true;
        return false;
    }
    public static <E> boolean onkoLehtisolmu(BTreeNode<E> n) {
        if(n == null)
            return false;
        if(n.getLeftChild() == null && n.getRightChild() == null)
            return true;

        return false;
    }

    /**
     * 21. Kirjoita algoritmi, joka vertaa kahta binÃ¤Ã¤ripuuta ja palauttaa toden jos puut ovat raken-
     * teeltaan samat, muuten epÃ¤toden. Puut ovat samat, jos juuren molemmat alipuut ovat
     * keskenÃ¤Ã¤n rakenteeltaan samanlaiset. Puun sisÃ¤ltÃ¤miÃ¤ elementtejÃ¤ ei siis vertailla, vain puun
     * rakennetta (â€oksaston muotoaâ€). Aikavaativuus? Tilavaativuus? Vihje: rekursio rinta rinnan.
     *
     * @param T1  syÃ¶tepuu1
     * @param T2  syÃ¶tepuu2
     * @param <E> alkiotyyppi (ei kÃ¤ytetÃ¤)
     * @return ovatko rakenteeltaan samat vai ei
     */
    public static <E> boolean vertaaRakenne(BTree<E> T1, BTree<E> T2) {
        // TODO
        BTreeNode n1 = T1.getRoot();
        BTreeNode n2 = T2.getRoot();
        if(n1 == null && n2 == null) {
            return true;
        } else if(n1 != null && n2 != null) {
            boolean n_oikea = vertaaRakenne(new BTree<E>(n1.getRightChild()), new BTree<E>(n2.getRightChild()));
            boolean n_vasen = vertaaRakenne(new BTree<E>(n1.getLeftChild()), new BTree<E>(n2.getLeftChild()));
            if(n_oikea && n_vasen) {
                return true;
            } else {
                return false;
            }
        }


        return false;
    } // vertaaRakenne()


    // esimerkkejÃ¤ ja pohjia


    public static BTree<Integer> exampleBTree() {

        BTree<Integer> T = new BTree<Integer>();

        // juuri
        T.setRoot(new BTreeNode<Integer>(9));

        BTreeNode<Integer> n = T.getRoot();

        // juuren lapset
        n.setLeftChild(new BTreeNode<Integer>(5));
        n.setRightChild(new BTreeNode<Integer>(15));

        // vasen haara
        BTreeNode<Integer> l = n.getLeftChild();

        l.setLeftChild(new BTreeNode<Integer>(3));
        l.setRightChild(new BTreeNode<Integer>(8));

        l.getLeftChild().setRightChild(new BTreeNode<Integer>(4));

        // oikea haara
        l = n.getRightChild();

        l.setLeftChild(new BTreeNode<Integer>(12));
        l.setRightChild(new BTreeNode<Integer>(18));

        l.getLeftChild().setLeftChild(new BTreeNode<Integer>(11));
        l.getLeftChild().setRightChild(new BTreeNode<Integer>(13));


        System.out.println("                 ");
        System.out.println("       9        ");
        System.out.println("    __/  \\__     ");
        System.out.println("   5        15   ");
        System.out.println("  / \\      /  \\  ");
        System.out.println(" 3   8   12    18");
        System.out.println("  \\      /\\      ");
        System.out.println("   4    11 13    ");
        System.out.println("                 ");

        return T;

    } // exampleBTree()


    /**
     * 22. LisÃ¤ys sisÃ¤jÃ¤rjestettyyn binÃ¤Ã¤ripuuhun.
     *
     * @param T binÃ¤Ã¤ripuu
     * @param x lisÃ¤ttÃ¤vÃ¤ alkio
     * @return tehtiinkÃ¶ lisÃ¤ys vai ei
     */
    public static <E extends Comparable<? super E>> boolean inorderInsert(BTree<E> T, E x) {

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

    } // inorderInsert()


    // Tulostus sisÃ¤jÃ¤rjestyksessÃ¤ rekursiivisesti
    public static void inorderPrint(BTree T) {
        inorderPrintBranch(T.getRoot());
        System.out.println();
    } // inorderPrint()


    public static void inorderPrintBranch(BTreeNode n) {
        if (n == null)
            return;

        inorderPrintBranch(n.getLeftChild());
        System.out.print(n.getElement() + " ");
        inorderPrintBranch(n.getRightChild());

    } // inorderPrintBranch()


} // class TRAI_20_t19_21.java
