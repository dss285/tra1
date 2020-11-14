package tra1.vk4.xtehtava;

import java.util.*;

public class TRAI_20_X4_tommpuur implements TRAI_20_X4 {
    //                  ^^^^^ oma tunnus tÃ¤hÃ¤n
    /**
     * O(n)
     * Tämän olisi varmaan voinut tehdä linkedlistin iteraattoreilla, mutta itse olen enemmän indeksien kanssa pyörinyt 
     * niin tutumpaa.
     *
     * Algoritmi käy ekana kumpaakin listaa yhtäaikaa ja lisää sitä mukaan mikäli listassa on pienempi kuin toisessa listassa 
     * ja ei esiinny toisessa listassa.
     * jos toinen lista käydään nopeammin läpi niin sitten lisätään vain loput toisesta listasta tulokseen.
     * Läpäisi testit, 100% varmuutta ei ole että onko jonkinlainen tapaus, jossa tämä epäonnistuu.
     * 
     * Algoritmissa oli pientä hankaluuksia, alhaalla on kommenteissa jotain aikaisempia yrityksien pätkiä.
     *
     */

    /**
     * JÃ¤rjestettyjen listojen joko-tai-yhdiste.
     * Muodostaa uusen jÃ¤rjestetyn listan niistÃ¤ syÃ¶telistojen alkioista jotka esiintyvÃ¤t vain
     * toisessa syÃ¶telistoista.
     * Ei muuta syÃ¶telistoja.
     *
     * @param A   syÃ¶telista, kasvavassa jÃ¤rjestyksessÃ¤
     * @param B   syÃ¶telista, kasvavassa jÃ¤rjestyksessÃ¤
     * @param <E> alkiotyyppi, toteuttaa Comparable-rajapinnan
     * @return uusi lista jossa kasvavassa jÃ¤rjestyksessÃ¤ ne alkiot jotka ovat vain yhdessÃ¤ syÃ¶telistassa
     */
    @Override
    public <E extends Comparable<? super E>> LinkedList<E>
    jarjestettyjenVainToisessaYhdiste(LinkedList<E> A, LinkedList<E> B) {

        LinkedList<E> tulos = new LinkedList<E>();
        // TODO
        if (A.isEmpty()) {
            tulos.addAll(B);//O(n)
            return tulos;
        } else if (B.isEmpty()) {
            tulos.addAll(A);//O(n)
            return tulos;
        }
        ArrayList<E> ar = new ArrayList<>(A);
        ArrayList<E> br = new ArrayList<>(B);
        E tmp;
        int i, j;
        for(i=0, j=0;i<ar.size() && j < br.size();) {
            if(ar.get(i).compareTo(br.get(j)) == 0) {
                tmp = ar.get(i);
                while(i<ar.size() && ar.get(i).equals(tmp)) {
                    i++;
                }
                while (j<br.size()&&br.get(j).equals(tmp)) {
                    j++;
                }
            } else if(ar.get(i).compareTo(br.get(j)) > 0) {
                while(j < br.size() && br.get(j).compareTo(ar.get(i)) < 0) {
                    tulos.add(br.get(j));
                    j++;
                }
            } else if(ar.get(i).compareTo(br.get(j)) < 0) {
                while(i<ar.size() && ar.get(i).compareTo(br.get(j)) < 0) {
                    tulos.add(ar.get(i));
                    i++;
                }
            }
        }

        while (i<ar.size()) {
            tulos.add(ar.get(i));
            i++;
        }
        while (j<br.size()) {
            tulos.add(br.get(j));
            j++;
        }
        return tulos;
    }
}

/*
LINKEDLIST TOTEUTUS (EI TOIMI)
        ListIterator<E> ai = A.listIterator();
        ListIterator<E> bi = B.listIterator();
        E tmpa = null, tmpb = null, tmp;
        while(ai.hasNext()&&bi.hasNext()) {
            if(tmpa == null && tmpb == null) {
                tmpa = ai.next();
                tmpb = bi.next();
            }
            if(tmpa.compareTo(tmpb) == 0) {
                tmp = tmpa;
                while(ai.hasNext() && tmpa.equals(tmp)) {
                    tmpa = ai.next();
                }
                while(bi.hasNext() && tmpb.equals(tmp)) {
                    tmpb = bi.next();
                }
            } else if(tmpa.compareTo(tmpb) > 0) {
                while (bi.hasNext() && tmpb.compareTo(tmpa) < 0) {
                    tulos.add(tmpb);
                    tmpb = bi.next();
                }
            }  else if(tmpa.compareTo(tmpb) < 0) {
                while (ai.hasNext() && tmpa.compareTo(tmpb) < 0) {
                    tulos.add(tmpa);
                    tmpa = ai.next();
                }
            }
        }

        while (ai.hasNext()) {
            tulos.add(ai.next());
        }
        while (bi.hasNext()) {
            tulos.add(bi.next());
        }













ARRAYLIST TOTEUTUS
ArrayList<E> ar = new ArrayList<>(A);
        ArrayList<E> br = new ArrayList<>(B);
        E tmp;
        int i, j;
        for(i=0, j=0;i<ar.size() && j < br.size();) {
            if(ar.get(i).compareTo(br.get(j)) == 0) {
                tmp = ar.get(i);
                while(i<ar.size() && ar.get(i).equals(tmp)) {
                    i++;
                }
                while (j<br.size()&&br.get(j).equals(tmp)) {
                    j++;
                }
            } else if(ar.get(i).compareTo(br.get(j)) > 0) {
                while(j < br.size() && br.get(j).compareTo(ar.get(i)) < 0) {
                    tulos.add(br.get(j));
                    j++;
                }
            } else if(ar.get(i).compareTo(br.get(j)) < 0) {
                while(i<ar.size() && ar.get(i).compareTo(br.get(j)) < 0) {
                    tulos.add(ar.get(i));
                    i++;
                }
            }
        }


















               if(A_iterator.hasNext() && B_iterator.hasNext()) {
                    if(A_viimeisin.compareTo(B_viimeisin) == 0) {
                        A_viimeisin = skippaa(A_viimeisin, A_iterator);
                        B_viimeisin = skippaa(B_viimeisin, B_iterator);
                    } else if(A_viimeisin.compareTo(B_viimeisin) == 1) {
                        B_viimeisin = lisaa(B_viimeisin, B_iterator, tulos);
                    } else if(A_viimeisin.compareTo(B_viimeisin) == -1) {
                        A_viimeisin = lisaa(A_viimeisin, A_iterator, tulos);
                    }
                } else if(B_iterator.hasNext() && !A_iterator.hasNext()) {
                    if(B_viimeisin.compareTo(A_viimeisin) == 0) {
                        B_viimeisin = skippaa(B_viimeisin, B_iterator);
                    } else {
                        if(A_viimeisin.compareTo(B_viimeisin) == -1) {
                            tulos.add(A_viimeisin);
                        }
                        B_viimeisin = lisaa(B_viimeisin, B_iterator, tulos);
                    }
                } else if(A_iterator.hasNext() && !B_iterator.hasNext()) {
                    if(A_viimeisin.compareTo(B_viimeisin) == 0) {
                        A_viimeisin = skippaa(A_viimeisin, A_iterator);
                    } else {
                        if(B_viimeisin.compareTo(A_viimeisin) == -1) {
                            tulos.add(B_viimeisin);
                        }
                        A_viimeisin = lisaa(A_viimeisin, A_iterator, tulos);
                    }
                }


 */







/*
            if(a_iterator.hasNext() && b_iterator.hasNext()) {
                if (a_viimeisin.compareTo(b_viimeisin) == 0) {
                    a_viimeisin = a_iterator.next();
                    b_viimeisin = b_iterator.next();
                    a_count = 1;
                    b_count = 1;
                } else if (a_viimeisin.compareTo(b_viimeisin) == -1) {
                    tmp = a_iterator.next();
                    while(a_iterator.hasNext() && tmp.compareTo(a_viimeisin) == 0) {
                        tmp = a_iterator.next();
                        a_count++;
                    }
                    for(int i=0;i<a_count;i++) {
                        tulos.add(tmp);
                    }
                    a_viimeisin = tmp;
                    a_count = 1;

                } else if (a_viimeisin.compareTo(b_viimeisin) == 1) {
                    tmp = b_iterator.next();
                    while(b_iterator.hasNext() && tmp.compareTo(b_viimeisin) == 0) {
                        tmp = b_iterator.next();
                        b_count++;
                    }
                    for(int i=0;i<b_count;i++) {
                        tulos.add(tmp);
                    }
                    b_viimeisin = tmp;
                    b_count = 1;

                }
            }
 */















            /*
            if(a_iterator.hasNext()) {
                tmp = a_iterator.next();
                cmp = tmp.compareTo(a_viimeisin);
                if(cmp == 0) {
                    a_count++;
                    a_viimeisin = tmp;
                }
            } else {
                a_viimeisin = null;
            }


            if(b_iterator.hasNext()) {
                tmp = b_iterator.next();
                cmp = tmp.compareTo(b_viimeisin);
                if(cmp == 0) {
                    b_count++;
                    b_viimeisin = tmp;
                }
            } else {
                b_viimeisin = null;
            }



















                        if (a_viimeisin.compareTo(b_viimeisin) == 0) {
                tmp = a_viimeisin;
                while (a_iterator.hasNext() && a_viimeisin.compareTo(tmp) == 0) {
                    a_viimeisin = a_iterator.next();
                }
                tmp = b_viimeisin;
                while (b_iterator.hasNext() && b_viimeisin.compareTo(tmp) == 0) {
                    b_viimeisin = b_iterator.next();
                }
                a_count = 0;
                b_count = 0;
            } else if (a_viimeisin.compareTo(b_viimeisin) == 1) {
                tmp = b_viimeisin;
                if (!b_iterator.hasNext()) {
                    b_count++;
                }
                while (b_iterator.hasNext() && tmp.compareTo(b_viimeisin) == 0) {
                    tmp = b_iterator.next();
                    b_count++;
                }
                for (int i = 0; i < b_count; i++) {
                    tulos.add(b_viimeisin);
                }
                b_viimeisin = tmp;
                b_count = 0;
            } else if (a_viimeisin.compareTo(b_viimeisin) == -1) {
                tmp = a_viimeisin;
                if (!a_iterator.hasNext()) {
                    a_count++;
                }
                while (a_iterator.hasNext() && tmp.compareTo(a_viimeisin) == 0) {
                    tmp = a_iterator.next();
                    a_count++;
                }
                for (int i = 0; i < a_count; i++) {
                    tulos.add(a_viimeisin);
                }
                a_viimeisin = tmp;
                a_count = 0;
            }


            if (b_viimeisin != null && a_viimeisin == null) {
                tulos.add(b_viimeisin);
                while (b_iterator.hasNext()) {
                    tulos.add(b_iterator.next());
                }
            } else if (b_viimeisin == null && a_viimeisin != null) {
                tulos.add(a_viimeisin);
                while (a_iterator.hasNext()) {
                    tulos.add(a_iterator.next());
                }
            }


            */