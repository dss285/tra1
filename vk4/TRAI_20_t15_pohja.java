package vk4;

import fi.uef.cs.tra.ListNode;
import fi.uef.cs.tra.TraLinkedList;

public class TRAI_20_t15_pohja {
   

    /**
     * JÃ¤rjestettyjen listojen joko-tai-yhdiste.
     * Muodostaa uusen jÃ¤rjestetyn listan niistÃ¤ syÃ¶telistojen alkioista jotka esiintyvÃ¤t vain
     * toisessa syÃ¶telistoista.
     * Ei muuta syÃ¶telistoja.
     *
     * @param A syÃ¶telista, kasvavassa jÃ¤rjestyksessÃ¤
     * @param B syÃ¶telista, kasvavassa jÃ¤rjestyksessÃ¤
     * @param <E> alkiotyyppi, toteuttaa Comparable-rajapinnan
     * @return uusi lista jossa kasvavassa jÃ¤rjestyksessÃ¤ ne alkiot jotka ovat vain yhdessÃ¤ syÃ¶telistassa
     */
    public <E extends Comparable<? super E>> TraLinkedList<E>
                    jarjestettyjenVainToisessaYhdiste(TraLinkedList<E> A, TraLinkedList<E> B) {
        TraLinkedList<E> tulos = new TraLinkedList<E>();

        ListNode<E> pa = A.first();
        ListNode<E> pb = B.first();
        E tmp;
        // toisto kunnes molemmat ovat loppuneet
        // toki voit tehdÃ¤ myÃ¶s toiston joka loppuu kun toinen loppuu ja tÃ¤ydentÃ¤Ã¤ perÃ¤Ã¤n
        while (pa != A.EOL && pb != B.EOL) {
            if(pa.getElement().compareTo(pb.getElement()) == 0) {
                tmp = pa.getElement();
                while(pa != A.EOL && pa.getElement().compareTo(tmp) == 0) {
                    pa = pa.next();
                }
                tmp = pb.getElement();
                while(pb != B.EOL && pb.getElement().compareTo(tmp) == 0) {
                    pb = pb.next();
                }
            } else if(pa.getElement().compareTo(pb.getElement()) > 0) {
                while(pb != B.EOL && pb.getElement().compareTo(pa.getElement()) < 0) {
                    tulos.insert(tulos.EOL, pb.getElement());
                    pb = pb.next();
                }
            }
            else if(pa.getElement().compareTo(pb.getElement()) < 0) {
                while(pa != A.EOL && pa.getElement().compareTo(pb.getElement()) < 0) {
                    tulos.insert(tulos.EOL, pa.getElement());
                    pa = pa.next();
                }
            }
        }
        while(pa != A.EOL) {
            tulos.insert(tulos.EOL, pa.getElement());
            pa = pa.next();
        }
        while(pb != B.EOL) {
            tulos.insert(tulos.EOL, pb.getElement());
            pb = pb.next();
        }

        return tulos;
    }
}
