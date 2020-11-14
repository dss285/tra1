package vk1.xtehtava;

public interface TRAI_20_X1 {

    /**
     * Onko tuplaa.
     * Jos taulukon kaikki alkiot ovat eri lukuja, metodi
     * palauttaa false. Jos taas jokin (tai jotkin) luku esiintyy
     * kahdesti tai useammin, metodi palauttaa true.
     * Voit olettaa taulukossa olevan vain kelvollisia kokonaislukuja
     * (ei siis null:eja).
     *
     * @param A SyÃ¶tetaulukko.
     * @return false jos kaikki alkiot ovat eri lukuja, muuten true.
     */
    boolean onkoTuplaa(Integer[] A);

}
