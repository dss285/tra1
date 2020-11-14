package vk3;// TRAI_20_t12.java SJ
// PÃ¤Ã¤ohjelma viikon 3 tehtÃ¤vÃ¤Ã¤n 12

/**
 *12. Kirjoita algoritmi joka poistaa mistÃ¤ tahansa kokoelmasta A (java.util.Collection -rajapinnan
toteutus) ne alkiot joita ei lÃ¶ydy toisesta kokoelmasta B. SÃ¤ilytetÃ¤Ã¤n siis ne kokoelman A
alkiot joille .equals() metodi antaa toden jollekin kokoelman B alkioita vastaan. Vihje: kaikki
kokoelmat voidaan kÃ¤ydÃ¤ lÃ¤pi foreach -toistolla, mutta silloin lÃ¤pikÃ¤ytÃ¤vÃ¤Ã¤ kokoelmaa ei voi
muuttaa. Sensijaan iteraattorilla on remove() -operaatio ja kokoelmalla on operaatio remo-
ve(Object). Ã„lÃ¤ kÃ¤ytÃ¤ valmista retainAll()-operaatiota. MikÃ¤ on algoritmisi aikavaativuus?
Miten aikavaativuus riippuu kÃ¤ytettÃ¤vÃ¤stÃ¤ kokoelmasta?

 */

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class TRAI_20_t12_pohja {


    // PÃ¤Ã¤ohjelman kÃ¤yttÃ¶:
    // java TRAI_20_t12 [N] [S]
    // missÃ¤ N on alkioiden mÃ¤Ã¤rÃ¤, 
    // ja S on satunnaislukusiemen
    public static void main(String[] args) {

        // taulukoiden koko
        int N = 11;
        if (args.length > 0)
            N = Integer.valueOf(args[0]);

        // satunnaislukusiemen
        int siemen = 42;
        if (args.length > 1)
            siemen = Integer.valueOf(args[1]);

        java.util.Random r = new java.util.Random(siemen);
        // muodostetaan LinkedList:it
        LinkedList<Integer> L1 = new LinkedList<Integer>();
        LinkedList<Integer> L2 = new LinkedList<Integer>();
        for (int i = 0 ; i < N; i++) {
            L1.add(r.nextInt(N));
            L2.add(r.nextInt(N + 5));
        }

        if (N <= 20) {
            System.out.println("L1:" + L1);
            System.out.println("L2:" + L2);
        }


        // tehtÃ¤vÃ¤n 12 testaus
        System.out.println("\nLeikkaus");
        int pois = sailytaKaikki(L1, L2);

        if (N <= 20) {
            System.out.println("L1:" + L1);
            System.out.println("L2:" + L2);
        }
        System.out.println("Poistettu " + pois + " kpl");



    } // main()


    /**
     * Aikavaativuus on O(n*2n) = O(n*n) = O(n^2)
     * Aikavaativuus ei tässä algoritmissa vaikuta lopputulokseen, mutta jos Collection on LinkedList, niin välitulos on O(n*n) eikä O(n*2n)
     *
     * 12. poista kaikki B:n alkiot A:sta.
     *
     *
     *
     * @param A muutettava kokoelma
     * @param B poistettavat alkiot
     * @param <E> parametrityyppi
     * @return poistettujen mÃ¤Ã¤rÃ¤
     */
    public static <E> int sailytaKaikki(Collection<E> A, Collection<E> B) {
        int pois = 0;
        Iterator<E> li = A.iterator();
        while(li.hasNext()) {
            boolean loydetty = false;
            E verrattava = li.next();
            for(E o : B) {
                if(o.equals(verrattava)) {
                    loydetty = true;
                }
            }
            if(loydetty) {
                li.remove(); //O(1) Linkitetty listä, O(n) ArrayList
                pois++;
            }
        }
        return pois;
    }





} // class

