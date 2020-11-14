package tra1.vk5.xtehtava;

import fi.uef.cs.tra.BTree;
import fi.uef.cs.tra.BTreeNode;

public class TRAI_20_X5_tommpuur implements TRAI_20_X5 {
    /**
        sisaEnsimmäinen algoritmi on yksinkertainen, mennään vasemmalle niin kauan puussa, kunnes tulee null ja sitten palautetaan tämä.
        sisäSeuraaja algoritmi on monimutkaisempi ja testaukseen kului aikaa, mutta sain tämän toimimaan.
        Jos oikea lapsi on, niin otetaan tämä uudeksi solmuksi. Mennään tässä solmussa niin kauan vasemmalle kunnes tulee solmu jossa ei ole vasenta solmua.
        Jos oikeaa lapsea ei ole, niin mennään vanhempaan ja tarkistetaan onko vanhemman vasen solmu sama kuin nykyinen solmu, jos ei ole niin jatketaan
        ja otetaan vanhempi uudeksi tarkistettavaksi solmuksi.

                    10
                  /
                8
              /   \
           7        9
         /
        6

        Jos ollaan 6 solmussa ja halutaan seuraaja, niin on tämä 7 solmu, koska nykyinen solmu on tämän vasemmassa juuressa
        Jos ollaan 9 solmussa ja halutaan seuraaja, niin tämä on 10, koska ensimmäiseksi tarkistetaan 8 ja sitten huomataan että nykyinen solmu ei ole tämän vasen lapsi,
        otetaan 8 solmu uudeksi tarkistettavaksi solmuksi ja huomataan että tämän vanhemman vasen lapsi on tämä lapsi, ja palautetaa arvo.

        Läpikäynnin aikavaativuus sisaEnsimmainen on pahimmassa tapauksessa O(n)
        Läpikäynnin aikavaativuus sisaSeuraaja on pahimmassa tapauksessa O(h), missä h on puun korkeus.
     **/

    /**
     * Palauttaa binÃ¤Ã¤ripuun sisÃ¤jÃ¤rjestyksessÃ¤ ensimmÃ¤isen solmun.
     *
     * @param T Tarkasteltava puu.
     * @param <E> solmun alkioiden tyyppi
     * @return SisÃ¤jÃ¤rjestyksessÃ¤ ensimmÃ¤inen solmu tai null jos puu on tyhjÃ¤.
     */
    @Override
    public <E> BTreeNode<E> sisaEnsimmainen(BTree<E> T) {

        // TODO
        BTreeNode<E> n = T.getRoot();
        if(n == null)
            return null;
        while(n.getLeftChild() != null) {
            n = n.getLeftChild();
        }
        return n;

    }

    /**
     * Palauttaa binÃ¤Ã¤ripuun solmun sisÃ¤jÃ¤rjestyksessÃ¤ seuraajasolmun.
     *
     * @param n BinÃ¤Ã¤ripuun solmu.
     * @return seuraajasolmu tai null jollei seuraajaa ole.
     */
    @Override
    public <E> BTreeNode<E> sisaSeuraaja(BTreeNode<E> n) {
        // TODO
        if(n.getRightChild() != null) {
            n = n.getRightChild();
            while(n.getLeftChild() != null) {
                n = n.getLeftChild();
            }
            return n;
        }
        while(n.getParent() != null) {
            if (n.equals(n.getParent().getLeftChild()))
                return n.getParent();
            n = n.getParent();
        }
        return null;
    }
}
