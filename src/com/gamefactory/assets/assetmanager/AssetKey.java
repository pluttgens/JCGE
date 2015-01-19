package com.gamefactory.assets.assetmanager;

import java.util.Objects;

/**
 * La classe AssetKey représente une clé permettant d'identifier un asset.
 *
 * Elle est composée de : - le type qui représente le dossier dans lequel se
 * trouve l'asset. - le nom de l'asset qui contient l'extension
 *
 * Supposons que l'on est un fichier asset.png dans le dossier images, on pourra
 * accéder à celui ci en créeant un new AssetKey("images", "asset.png").
 *
 * @author Pascal Luttgens
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class AssetKey {

    protected final String type;

    protected final String name;

    protected final String extension;

    public AssetKey(String type, String name) {
        this.type = type;
        this.name = name;
        this.extension = retrieveExtension(name);
    }

    private String retrieveExtension(String assetName) {
        int index = assetName.lastIndexOf(".");
        if (index <= 0 || index == assetName.length() - 1) {
            throw new IllegalArgumentException("Nom d'asset invalide.");
        }
        return assetName.substring(index + 1);
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.type);
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.extension);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AssetKey other = (AssetKey) obj;
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

}
