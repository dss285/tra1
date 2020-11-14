package vk4;// TRAI_20_t18.java.java SJ

/**

 * 18. Kirjoita algoritmi, joka lisÃ¤Ã¤ sisÃ¤jÃ¤rjestyksessÃ¤ olevaan binÃ¤Ã¤ripuuhun
 * uuden solmun siten, ettÃ¤ puu sÃ¤ilyy sisÃ¤jÃ¤rjestyksessÃ¤. Jos sama alkio
 * (.equals()) oli jo puussa, niin alkiota ei lisÃ¤tÃ¤ puuhun. Parametreina puu
 * ja alkio. Algoritmi luo uuden solmun jos lisÃ¤ys tehdÃ¤Ã¤n.  Algoritmi
 * palauttaa totuusarvon lisÃ¤ttiinkÃ¶ alkio vai ei. Algoritmin toiminta kÃ¤ydÃ¤Ã¤n
 * lÃ¤pi luennolla. Aikavaativuus? 

 **/

// Tarvitset projektiin (tai komentoriville) TRA-kirjaston Moodlesta.

import fi.uef.cs.tra.BTree;
import fi.uef.cs.tra.BTreeNode;

import java.util.Random;

public class TRAI_20_t18_pohja {

    public static void main(String[] args) {

        BTree<Integer> puu = new BTree<Integer>();

        int N = 10;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        System.out.println("Puuhun:");
        Random r = new Random(42);
        Integer x = 0;
        for (int i = 0; i < N; i++) {
            x = r.nextInt(N*2);
            System.out.print(x + " ");
            inorderInsert(puu, x);
        }
        System.out.println();

        System.out.println("SisÃ¤jÃ¤rjestyksessÃ¤:");
        inorderPrint(puu);

        for (int i = 0; i < 17; i++) {
            System.out.println("Onko " + i + " : " +
                               inorderMember(puu, new Integer(i)));
        }

        BTree<Integer> puu2 =new BTree<>();
        inorderInsert(puu2, 10);
        inorderInsert(puu2, 13);
        inorderInsert(puu2, 7);
        inorderInsert(puu2, 3);
        inorderInsert(puu2, 8);
        inorderInsert(puu2, 2);
        inorderInsert(puu2, 6);
        inorderInsert(puu2, 4);
        inorderInsert(puu2, 5);


    } // main()






    /**
     * 18. LisÃ¤ys sisÃ¤jÃ¤rjestettyyn binÃ¤Ã¤ripuuhun.
     * @param T binÃ¤Ã¤ripuu
     * @param x lisÃ¤ttÃ¤vÃ¤ alkio
     * @return tehtiinkÃ¶ lisÃ¤ys vai ei
     */
    public static <E extends Comparable<? super E>> boolean inorderInsert(BTree<E> T, E x) {
        BTreeNode<E> n = T.getRoot();
        if(n == null) {
            T.setRoot(new BTreeNode<>(x));
            return true;
        }
        while(n != null) {
            if(x.compareTo(n.getElement()) == 0) {
                return false;
            } else if(x.compareTo(n.getElement()) < 0) {
                if(n.getLeftChild() != null) {
                    n = n.getLeftChild();
                } else {
                    n.setLeftChild(new BTreeNode<>(x));
                    return true;
                }
            } else {
                if(n.getRightChild() != null) {
                    n = n.getRightChild();
                } else {
                    n.setRightChild(new BTreeNode<>(x));
                    return true;
                }
            }
        }
        // TODO
        return true;
    } // inorderInsert()



    // -------------------------------
    // esimerkkejÃ¤

    public static <E extends Comparable<? super E>>
        boolean inorderMember(BTree<E> T, E x) {
            BTreeNode<E> n = T.getRoot();

            while (n != null) {
                if (x.compareTo(n.getElement()) == 0)
                    return true;
                else if (x.compareTo(n.getElement()) < 0)
                    n = n.getLeftChild();
                else
                    n = n.getRightChild();
            } // while
            return false;

        } // inorderMember()


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


} // class TRAI_20_t18.java
