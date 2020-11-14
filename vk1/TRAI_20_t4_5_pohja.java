package vk1;// TraI_19_t5_6_pohja.java SJ
// Esimerkkiratkaisu viikon 1 tehtÃ¤viin 5-6

/**
 * 4. Kirjoita algoritmi (Java-metodi) joka saa parametrinaan kaksi kokonaislukutaulukkoa (In-
 * teger[] A, Integer[] B) ja joka luo ja palauttaa uuden kokonaislukutaulukon jossa on kaikki
 * ne alkiot jotka lÃ¶ytyvÃ¤t taulukosta A mutta eivÃ¤t lÃ¶ydy taulukosta B (siis niiden erotuksen).
 * Ã„lÃ¤ muuta syÃ¶tetaulukoita. MikÃ¤ on algoritmisi aikavaativuus? Voisiko sitÃ¤ parantaa? Ota
 * tehtÃ¤vÃ¤Ã¤n pÃ¤Ã¤ohjelma Moodlesta.
 *
 * 5. Kertaa Olio-ohjelmointi (Ohjelmointi III) -kurssilla kÃ¤sitelty ArrayList -luokka. LisÃ¤tie-
 * toa: http://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html. Muuta
 * edellisen tehtÃ¤vÃ¤n 4 algoritmi toimimaan taulukoiden sijaan ArrayList -kokoelmilla. Ã„lÃ¤ kÃ¤ytÃ¤
 * valmista .removeAll() -metodia. MikÃ¤ on algoritmisi aikavaativuus? Voisiko sitÃ¤ parantaa?
 **/

import java.util.ArrayList;
import java.util.Arrays;

public class TRAI_20_t4_5_pohja {


    // PÃ¤Ã¤ohjelman kÃ¤yttÃ¶:
    // java TRAI_20_t4_5 [N] [N2] [S]
    // missÃ¤ N on alkioiden mÃ¤Ã¤rÃ¤, N2 on alkoiden mÃ¤Ã¤rÃ¤ toisessa taulukossa
    // ja S on satunnaislukusiemen
    public static void main(String[] args) {

        // taulukoiden koko
        int n1 = 10;
        if (args.length > 0)
            n1 = Integer.valueOf(args[0]);

        int n2 = n1+2;
        if (args.length > 1)
            n2 = Integer.valueOf(args[1]);

        // satunnaislukusiemen
        int siemen = 42;
        if (args.length > 2)
            siemen = Integer.valueOf(args[2]);

        // luodaan esimerkkitaulukot
        Integer[] T1 = new Integer[n1];
        Integer[] T2 = new Integer[n2];

        // tÃ¤ytetÃ¤Ã¤n alkioilla
        java.util.Random r = new java.util.Random(siemen);
        for (int i = 0; i < n1; i++) {
            T1[i] = r.nextInt(n1);
        }

        for (int i = 0; i < n2; i++) {
            T2[i] = r.nextInt(n2 * 2);
        }

        // tulostetaan taulukot jos alkioita ei ole paljoa
        if (n1 <= 20 && n2 <= 20) {
            System.out.println("T1: " + Arrays.toString(T1));
            System.out.println("T2: " + Arrays.toString(T2));
        }

        // kutsutaan tehtÃ¤vÃ¤Ã¤ 4
        Integer[] E4 = erotus4(T1, T2);

        System.out.print("TehtÃ¤vÃ¤ 4, erotus = ");
        if (n1 <= 20 && n2 <= 20) {
            System.out.println(Arrays.toString(E4));
        } else {
            System.out.println(E4.length + " alkioinen taulukko");
            // huom: tÃ¤mÃ¤ tulostaa taulukon koon, ei todellisten alkioiden mÃ¤Ã¤rÃ¤Ã¤!
        }


        // Muodostetaan taulukoista ArrayList:t

        ArrayList<Integer> L1 = new ArrayList<Integer>(T1.length);
        ArrayList<Integer> L2 = new ArrayList<Integer>(T2.length);
        for (Integer x : T1)
            L1.add(x);

        for (Integer x : T2)
            L2.add(x);

        // kutsutaan tehtÃ¤vÃ¤Ã¤ 5
        ArrayList<Integer> E5 = erotus5(L1, L2);

        System.out.print("TehtÃ¤vÃ¤ 5, erotus = ");
        if (n1 <= 20 && n2 <= 20) {
            System.out.println(E5);
        } else {
            System.out.println(E5.size() + " alkiota");
        }

    } // main()




    /**
     * 4.
     * Palauttaa taulukoiden erotuksen, siis ne alkiot jotka ovat
     * taulukossa T1, mutta eivÃ¤t taulukossa T2.
     * Taulukot kÃ¤sitellÃ¤Ã¤n kokonaan, null alkiot ohitetaan.
     *
     * @param T1 ensimmÃ¤inen taulukko
     * @param T2 toinen taulukko
     * @return erotus taulukkona
     **/
    // neliÃ¶llinen ratkaisu, tehdÃ¤Ã¤n myÃ¶hemmin tehokkaampi

    /*
        O(n^2)
        Askelia n*n*10 + 3 (alustukset)


        Yksinkertainen, mutta toimiva algoritmi.
        Alustetaan 'tulos' taulukko T1 taulukon koolla.
        Alustetaan apumuuttuja 'koko', jolla laitetaan tulos taulukon alkuun.
        Alustetaan apumuuttuja 'loytynyt', jolla määritetään onko alkio löydetty toisesta listasta.
        Käydään Taulukko 1 Läpi:
            Alustetaan
            Joka kerran kun haetaan uusi alkio, käydään toinen taulukko läpi, joka kerta ja katsotaan onko taulukon 2 alkio sama kuin taulukon 1 alkio.
            Jos näin on, niin muutetaan 'loytynyt' true tilaan ja rikotaan loop.
        Taulukon 2 loopin päättyessä tarkistetaan onko loytynyt muuttuja false tilassa, jos on niin laitetaan 'tulos' taulukkoon alkion arvo.

     */
    public static Integer[] erotus4(Integer[] T1, Integer[] T2) {
        Integer[] tulos = new Integer[T1.length];
        int koko = 0;
        boolean loytynyt = false;
        for(int i = 0;i<T1.length;i++) {
            loytynyt = false;
            for(int j = 0;j<T2.length;j++) {
                if(T1[i] == T2[j]) {
                   loytynyt = true;
                   break;
                }
            }
            if (!loytynyt) {
                tulos[koko] = T1[i];
                koko++;
            }
        }
        return tulos;
    } // erotus4



    /**
     * 5.
     * Palauttaa taulukkopohjaisten listojen erotuksen uutena listana.
     *
     * @param L1 ensimmÃ¤inen lista
     * @param L2 toinen lista
     * @return erotus listana
     */
        /*
        O(n^2)
        Askelia n*n*3 + 1 + askeleet arraylist alustuksessa


        Yksinkertainen, mutta toimiva algoritmi.
        Alustetaan 'tulos' taulukko T1 taulukon koolla.
        Alustetaan apumuuttuja 'loytynyt', jolla määritetään onko alkio löydetty toisesta listasta.
        Käydään Taulukko 1 Läpi:
            Alustetaan
            Joka kerran kun haetaan uusi alkio, käydään toinen taulukko läpi, joka kerta ja katsotaan onko taulukon 2 alkio sama kuin taulukon 1 alkio.
            Jos näin on, niin muutetaan 'loytynyt' true tilaan ja rikotaan loop.
        Taulukon 2 loopin päättyessä tarkistetaan onko loytynyt muuttuja false tilassa, jos on niin laitetaan 'tulos' taulukkoon alkion arvo.

     */
    public static ArrayList<Integer> erotus5(ArrayList<Integer> L1, ArrayList<Integer> L2) {

        ArrayList<Integer> tulos = new ArrayList<Integer>();
        boolean loytynyt = false;
        for (Integer i : L1) {
            loytynyt = false;
            for(Integer j : L2) {
                if(j == i) {
                    loytynyt = true;
                    break;
                }
            }
            if(!loytynyt) {
                tulos.add(i);
            }
        }

        return tulos;
    } // erotus5()


} // class

