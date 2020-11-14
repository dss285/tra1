package vk1.xtehtava;

public class TRAI_20_X1_tommpuur implements TRAI_20_X1 {
    //                  ^^^^^ OMA TUNNUS TÃ„HÃ„N
    // NimeÃ¤ tiedosto TRAI_20_X1_tunnus.java
    

    /** ITSEARVIOINTI TÃ„HÃ„N
     * Algoritmi on yksinkertainen.
     * Alustetaan muuttuja 'count' ja käynnistetään loop
     * Käydään läpi kaikki alkiot
     * Aloitetaan käymään läpi uuden kerran, jolloin voidaan löytää onko samaa numeroa enemmän kuin 2.
     *
     * Ohjelma läpäisi kaikki testit.
     *
     * Huomasin, että suora vertailu ei toimi, vaan pitää käyttää Integer luokan equals funktiota, jolloin verrataan arvoja, ei ilmentymiä.
     * Toimisi suoralla vertailulla, jos olisi int[] tyyppinen taulukko.
     *
     * Parannuksena olisi muokata algorytmiä nopeammaksi jollakin tavalla.
     **/


    /**
     * Onko tuplaa.
     * Jos siis taulukon kaikki alkiot ovat eri lukuja, metodi
     * palauttaa false. Jos taas jokin (tai jotkin) luku esiintyy
     * kahdesti tai useammin, metodi palauttaa true.
     * Voit olettaa taulukossa olevan vain kelvollisia kokonaislukuja
     * (ei siis null:eja).
     *
     * Askelmäärä: n*n*4+1
     * O notaatio olisi O(n^2)
     *
     * @param A SyÃ¶tetaulukko.
     * @return false jos kaikki alkiot ovat eri lukuja, muuten true.
     */
    @Override
    public boolean onkoTuplaa(Integer[] A) {
        int count;
        for(Integer i : A) {
            count = 0;
            for(Integer j : A) {
                if(j.equals(i)) {
                    count +=1;
                }
            }
            if(count > 1) {
                return  true;
            }
        }
        return false;

    }
}
