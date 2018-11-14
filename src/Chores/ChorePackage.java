package Chores;

import db.Chore;

import java.io.Serializable;
import java.util.List;

public class ChorePackage implements Serializable {
    public List<Chore> mychores;
    public List<Chore> allchores;
    public List<Chore> shamedchores;

    public ChorePackage(List<Chore> mychores, List<Chore> allchores, List<Chore> shamedchores) {
        this.mychores= mychores;
        this.allchores = allchores;
        this.shamedchores = shamedchores;
    }
}
