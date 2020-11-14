package tra1.vk5.xtehtava;

import fi.uef.cs.tra.BTree;
import fi.uef.cs.tra.BTreeNode;

public interface TRAI_20_X5 {

    /**
     * Palauttaa binÃ¤Ã¤ripuun sisÃ¤jÃ¤rjestyksessÃ¤ ensimmÃ¤isen solmun.
     * @param T Tarkasteltava puu.
     * @param <E> solmun alkioiden tyyppi
     * @return SisÃ¤jÃ¤rjestyksessÃ¤ ensimmÃ¤inen solmu tai null jos puu on tyhjÃ¤.
     */
    public <E> BTreeNode<E> sisaEnsimmainen(BTree<E> T);

    /**
     * Palauttaa binÃ¤Ã¤ripuun solmun sisÃ¤jÃ¤rjestyksessÃ¤ seuraajasolmun.
     * @param n BinÃ¤Ã¤ripuun solmu.
     * @return seuraajasolmu tai null jollei seuraajaa ole.
     */
    public <E> BTreeNode<E> sisaSeuraaja(BTreeNode<E> n);

}
