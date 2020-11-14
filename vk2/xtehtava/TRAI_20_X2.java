package vk2.xtehtava;

import java.util.ArrayList;

public interface TRAI_20_X2 {

    /**
     * Kasvavien listojen yhdiste.
     * Palauttaa sellaiset alkiot jotka lÃ¶ytyvÃ¤t jommastakummasta tai molemmista syÃ¶telistoista.
     * Kukin alkio tulee tuloslistaan vain kerran.
     * Tuloslista tulee myÃ¶s kasvavaan jÃ¤rjestykseen.
     * @param A ensimmÃ¤inen syÃ¶telista, alkiot kasvavassa jÃ¤rjestyksessÃ¤
     * @param B toinen syÃ¶telista, alkiot kasvavassa jÃ¤rjestyksessÃ¤
     * @return yhdistelista
     */
    ArrayList<Integer> kasvavienYhdiste(ArrayList<Integer> A, ArrayList<Integer> B);

}
