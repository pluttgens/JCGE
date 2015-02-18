package com.gamefactory.assets.assetmanager;

import java.io.InputStream;

/**
 * La Superclass AssetInputStreamProvider sert de base au différentes classe
 * permettant de récupérer un InputStream à partir d'une source découplée du
 * programme.
 *
 * On peut en effet récupérer un InputStream à partir d'un fichier, d'une base
 * de données ou peu importe, cela ne nous interesse pas au niveau du programme
 * lui même tant que nous avons l'InputStream et l'extension (MIME) provenant de
 * l'asset.
 *
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public interface AssetInputStreamProvider {

    /**
     * Un InputStream décoré (Pattern Decorator) pour connaitre son extension
     * afin de savoir comment le lire si il y'a besoin.
     *
     * @author Pascal Luttgens
     *
     * @version 1.0
     *
     * @since 1.0
     */
    public class InputStreamWithMime {

        private final InputStream inputStream;

        private final String mime; // L'extension du fichier.

        public InputStreamWithMime(InputStream inputStream, String mime) {
            this.inputStream = inputStream;
            this.mime = mime;
        }

        /**
         * R�cup�re le flux d'entr�e
         * @return
         */
        public InputStream getInputStream() {
            return this.inputStream;
        }

        /**
         * R�cup�re l'extension du fichier
         * @return
         */
        public String getMime() {
            return this.mime;
        }
    }

    /**
     * Récupère l'asset via le provider à partir de l'AssetKey
     * 
     * @param assetKey
     * @return 
     */
    public abstract InputStreamWithMime getInputStream(AssetKey assetKey);

}
