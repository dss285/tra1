package tra1.vk8;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Menin linkitetyllä rakenteella, kun on omasta mielestäni järkevämpi toteuttaa tällä tavalla.
 * RingNode edustaa yhtä renkaan nodea, tässä on tuplalinkitetty, yksilinkitetylläkin saa toimimaan, mutta jos ajattelee
 * ns. tulevaisuutta kaksilinkkiä voi olla hyödyllinen, joskin vaatii tilanteen jossa on hyötyä.
 *
 *
 * remove() iteraattorissa, että luokassa toimii samallalailla, toisessa vain palautetaan E.
 * Pääsi läpi kaikista testeistä.
 *
 * Aikavaativuudet ovat vakioaikaisia perusoperaatioilla. (remove, hasNext, next)
 * Sisältää FailFast, removen.
 *
 * @param <E>
 */
public class TRAI_20_X8_tommpuur<E> implements TRAI_20_X8<E> {
    // ^^^^^ oma tunnus tÃ¤hÃ¤n

    private int n = 0;  // alkioiden mÃ¤Ã¤rÃ¤ renkaassa
    private int modcount = 0;
    private RingNode<E> first;
    private RingNode<E> last;
    public RingNode<E> selected;

    // TODO
    private class RingNode<E> {
        public E data;
        private RingNode<E> next;
        private RingNode<E> previous;
        public RingNode(E x) {
            data = x;
        }
        public void setNext(RingNode<E> x) {
            next = x;
        }
        public void setPrevious(RingNode<E> x) {
            previous = x;
        }
    }

    public TRAI_20_X8_tommpuur() {
        n = 0;
        first = null;
        last = null;
    }

    /**
     * Alkioiden mÃ¤Ã¤rÃ¤ renkaassa.
     *
     * @return Alkioiden mÃ¤Ã¤rÃ¤n.
     */
    @Override
    public int size() {
        return n;
    }

    /**
     * LisÃ¤Ã¤ renkaaseen alkion.
     *
     * @param x LisÃ¤ttÃ¤vÃ¤ alkio
     * @return palauttaa aina tosi (aina mahtuu)
     */
    @Override
    public boolean add(E x) {
        if (n == 0) {
            first = new RingNode<E>(x);
            first.setNext(first);
            first.setPrevious(first);
            last = first;
            n = 1;
            modcount++;
        } else {
            RingNode<E> node = new RingNode<E>(x);
            last.setNext(node);
            node.setNext(first);
            node.setPrevious(last);
            last = node;
            n += 1;
            modcount++;
        }
        return true;
    }

    /**
     * Poistaa syötetyn alkion.
     *
     * @return poistettava alkio
     */
    public E remove() {
        if (n == 1) {
            E palautettava = selected.data;
            first = null;
            last = null;
            selected = null;
            n = 0;
            return palautettava;
        }
        if (selected == first) {
            if (selected.next != null) {
                first = selected.next;
            }
        }
        if(selected == last) {
            if(selected.previous != null) {
                last = selected.previous;
            }
        }
        RingNode<E> prev = selected.previous;
        RingNode<E> next = selected.next;
        prev.setNext(next);
        next.setPrevious(prev);
        n--;
        modcount++;
        E data = selected.data;
        selected.data = null;
        return data;
    }


    /**
     * Palauttaa seuraavan alkion renkaasta.
     * Alussa palauttaa jonkin alkion.
     * Toistuvasti kutsuttaessa palauttaa kaikkia alkioita vuorotellen.
     *
     * @return Yhden renkaan alkion.
     * @throws NoSuchElementException jollei renkaassa ole alkoita
     */
    @Override
    public E next() {
        if (n == 0)
            throw new NoSuchElementException("Rengas on tyhjÃ¤, next() ei voi kutsua!");
        if (selected == null){
            selected = first;
            return selected.data;
        }
        selected = selected.next;
        return selected.data;
    }

    @Override
    public Iterator<E> iterator() {
        return new iter();
    }

    private class iter implements Iterator<E> {
        private RingNode<E> curr;
        private int initialmodcount=modcount;

        @Override
        public boolean hasNext() {
            checkModcount();
            return TRAI_20_X8_tommpuur.this.hasNext();
        }

        @Override
        public E next() {
            if (n == 0)
                throw new NoSuchElementException("Rengas on tyhjÃ¤, next() ei voi kutsua!");
            checkModcount();
            if (curr == null){
                curr = first;
                return curr.data;
            }
            curr = curr.next;
            return curr.data;
        }
        @Override
        public void remove() {
            checkModcount();
            if(n == 0)
                throw new NoSuchElementException("Rengas on tyhjÃ¤, remove() ei voi kutsua!");
            if(curr == null||curr.data == null)
                throw new IllegalStateException("Nextiä ei ole kutsuttu!");
            if(n == 1) {
                first = null;
                last = null;
                curr = null;
                n = 0;
            }
            if (curr == first) {
                if (curr.next != null) {
                    first = curr.next;
                }
            }
            if(curr == last) {
                if(curr.previous != null) {
                    last = curr.previous;
                }
            }
            RingNode<E> previous = curr.previous;
            RingNode<E> next = curr.next;
            previous.setNext(next);
            next.setPrevious(previous);
            curr.data = null;
            n--;
            initialmodcount = ++modcount;
            return;
        }
        private void checkModcount() {
            if(modcount != initialmodcount)
                throw new ConcurrentModificationException("Concurrent mod exception");
        }
    }
}
