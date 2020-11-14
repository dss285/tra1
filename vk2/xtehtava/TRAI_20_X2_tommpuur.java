package vk2.xtehtava;

import java.util.ArrayList;

public class TRAI_20_X2_tommpuur implements TRAI_20_X2 {
    //                  ^^^^^ OMA TUNNUS TÃ„HÃ„N
    // NimeÃ¤ tiedosto TRAI_20_X2_tunnus.java

    /** ITSEARVIOINTI TÃ„HÃ„N
     *
     * Toimivuus: Läpäisi kaikki testit.
     *
     * O notaatio = O(n^2)
     * Algoritmi käy lisäämisen yhteydessä listan läpi, löytyykö jo arvoa, jos löytyy niin ei lisää listaan alkiota.
     * Parannusehdotuksia on koodin siivoamista alifunktioihin enemmän, ja jos mahdollista, niin nopeampi algoritmi.
     *
     * 1. Tulos listan alustus.
     * 2. Tarkistetaan onko toinen listoista tyhjä, jos on niin laitetaan tulos listaan kaikki tyhjästä listasta ja palautetaan
     * 3. Alustetaan muuttujat auttamaan loopissa.
     * 4. Aloitetaan "ikuinen" loop, jonka sisällä on ehto joka rikkoo tämän. (Varmaan voi laittaa tuohon while kohtaan saman)
     * 5. Ensimmäisenä loopissa tarkastetaan onko molemmat indeksejä ilmoittavat numerot yli listojen koon, jos on niin rikotaan loop.
     * 6. Tarkistetaan onko indeksi muuttuja isompi kuin listojen indeksit, ja laitetaan min_ muuttuja nulliksi ilmoittamaan myöhemmälle koodille, että kaikki on käyty läpi.
     * 7. Haetaan listasta indeksi muuttujan perusteella alkio.
     * 8. Tarkistetaan, että min_ muuttujat eivät ole nulleja.
     *  8.1. Tarkistetaan ovatko min_ muuttujat samat, jos on, niin nostetaan molempia indeksimuuttujia yhdellä.
     *  8.2. Tarkistetaan kumpi min_ muuttujista on pienempi, ja lisätään se listaan ja nostetaan indeksi muuttujaa yhdellä.
     * 9. Jos min_ muuttujista jompi kumpi on null, niin lisätään se min_ muuttuja listaan mikä ei ole null ja nostetaan indeksi muuttujaa yhdellä.
     * 10. Palautetaan tulos lista
     **/

    /**
     * Kasvavien listojen yhdiste.
     * Palauttaa sellaiset alkiot jotka lÃ¶ytyvÃ¤t jommastakummasta tai molemmista syÃ¶telistoista.
     * Kukin alkio tulee tuloslistaan vain kerran.
     * Tuloslista tulee myÃ¶s kasvavaan jÃ¤rjestykseen.
     * @param A ensimmÃ¤inen syÃ¶telista, alkiot kasvavassa jÃ¤rjestyksessÃ¤
     * @param B toinen syÃ¶telista, alkiot kasvavassa jÃ¤rjestyksessÃ¤
     * @return yhdistelista
     */

    @Override
    public ArrayList<Integer> kasvavienYhdiste(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> tulos = new ArrayList<Integer>();//O(1)

        if(A.isEmpty() || B.isEmpty()) {//O(1)
            if(A.isEmpty()) {//O(1)
                for(Integer i : B) {//O(n)
                    lisaa(tulos, i);//O(3)
                }
            }
            if(B.isEmpty()) {//O(1)
                for(Integer i : A) {//O(n)
                    lisaa(tulos, i);//O(3)
                }
            }
            return tulos;//O(1)
        }

        Integer min_a = null, min_b = null, indx_a = 0, indx_b = 0;
        while(true) {//O(1)
            if(indx_a >= A.size() && indx_b >= B.size()) {//O(1)
                break;//O(1)
            }
            if(indx_a > A.size()-1) {//O(1)
                min_a = null;//O(1)
            }
            if(indx_b > B.size()-1) {//O(1)
                min_b = null;//O(1)
            }
            if(indx_a<A.size()) {//O(1)
                min_a = A.get(indx_a);//O(n)
            }
            if(indx_b<B.size()) {//O(1)
                min_b = B.get(indx_b);//O(n)
            }

            if(min_a != null && min_b != null) {//O(1)
                if (min_a.equals(min_b)) {//O(1)
                    lisaa(tulos, min_a);//O(3)
                    indx_a++;//O(1)
                    indx_b++;//O(1)
                }
                if (min_a < min_b) {//O(1)
                    lisaa(tulos, min_a);//O(3)
                    indx_a++;//O(1)
                    continue;//O(1)
                }
                if (min_b < min_a) {//O(1)
                    lisaa(tulos, min_b);//O(3)
                    indx_b++;//O(1)
                    continue;//O(1)
                }
            } else {
                if(min_a == null) { //O(1)
                    lisaa(tulos, min_b);//O(3)
                    indx_b++;//O(1)
                    continue;//O(1)
                }
                if(min_b == null) {//O(1)
                    lisaa(tulos, min_a);//O(3)
                    indx_a++;//O(1)
                    continue;//O(1)
                }
            }
        }

        return tulos;
    }
    private void lisaa(ArrayList<Integer> tulos, Integer lisattava) {
        if(!tulos.contains(lisattava)) {
            tulos.add(lisattava);
        }
    }
}
