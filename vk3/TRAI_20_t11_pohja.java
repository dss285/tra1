package vk3;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class TRAI_20_t11_pohja {

    /**
     * SiirrÃ¤ pienemmÃ¤t toiseen listaan.
     * Poistaa listasta kaikki sellaiset alkiot jotka edeltÃ¤vÃ¤t alkiota x.
     * (Ovat "pienempiÃ¤", eli ne alkiot a joille a.compareTo(x) < 0. Poistetut alkiot
     * siirretÃ¤Ã¤n uuteen listaan joka palautetaan.
     * Lista L sÃ¤ilyy muuten jÃ¤rjestyksessÃ¤.
     * @param L syÃ¶telista
     * @param x alkio jota pienemmÃ¤t siirretÃ¤Ã¤n
     * @return siirretyt alkiot listana
     */
    public <E extends Comparable<? super E>>  LinkedList<E> siirraPienemmat(LinkedList<E> L, E x) {

        LinkedList<E> U = new LinkedList<>();//O(1)
        ListIterator<E> li = L.listIterator();//O(1)
        while(li.hasNext()) { //O(n)
            E o = li.next(); //O(1)
            if(o.compareTo(x) < 0) { //O(1)
                U.add(o); //O(1)
                li.remove(); //O(1)
            }
        }
        return U;
    }

}
