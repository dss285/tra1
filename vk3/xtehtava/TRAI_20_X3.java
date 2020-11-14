package tra1.vk3.xtehtava;

import java.util.ArrayList;

public interface TRAI_20_X3 {

    /**
     * SiirrÃ¤ pienemmÃ¤t toiseen listaan.
     * Poistaa listasta kaikki sellaiset alkiot jotka edeltÃ¤vÃ¤t alkiota x.
     * (Ovat "pienempiÃ¤", eli ne alkiot a joille a.compareTo(x) < 0. Poistetut alkiot
     * siirretÃ¤Ã¤n uuteen listaan joka palautetaan.
     * Lista A sÃ¤ilyy muuten jÃ¤rjestyksessÃ¤.
     * @param A syÃ¶telista
     * @param x alkio jota pienemmÃ¤t siirretÃ¤Ã¤n
     * @return siirretyt alkiot listana
     */
    public <E extends Comparable<? super E>> ArrayList<E> siirraPienemmat(ArrayList<E> A, E x);

}
