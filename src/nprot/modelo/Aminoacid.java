/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nprot.modelo;

/**
 *
 * @author juanmoc
 */
public class Aminoacid {
    String name,nomenclature;
    int count=0;

    public Aminoacid(String name, String nomenclature,int count) {
        this.name = name;
        this.nomenclature = nomenclature;
    }

    public String getName() {
        return name;
    }

    public String getNomenclature() {
        return nomenclature;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature = nomenclature;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
}
