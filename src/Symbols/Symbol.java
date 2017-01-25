package Symbols;
import Scopes.*;
import java.util.ArrayList;
import java.util.List;

public class Symbol { // A generic programming language symbol
    public static enum AccessModifier {tINVALID, tpublic, tprivate, tprotected,TFINAL, TSTATIC, TABSTRACT,TNULL,TSTATICIMPORT}
    public static enum Type{tINVALID,tCLASS,tINTERFACE,tsubPACKAGE}

    //public static enum AccessType{tpublic, tprivate, tprotected, }
    public String name;
    // All symbols at least have a name
    public List<AccessModifier> accessmodifier= new ArrayList<AccessModifier>();



    //public AccessModifier accesstype;
    public Scope scope;      // All symbols know what scope contains them.
    public Type type;
    public String packagename;

    public Symbol(String name) { this.name = name; }
    public Symbol(String name,String packagename){
        this.name=name;
        this.packagename=packagename;

    }
    public Symbol(String name, ArrayList<AccessModifier>accesstype) { this(name); this.accessmodifier=accesstype; }
    public Symbol(String name, ArrayList<AccessModifier>accesstype,Type type){ this(name); this.accessmodifier.addAll(accesstype); this.type=type; }
    public Symbol(String name, ArrayList<AccessModifier>accesstype,Type type, String packagename){ this(name); this.accessmodifier.addAll(accesstype);   this.type=type; this.packagename=packagename; }
    public Symbol(String name, AccessModifier accesstype,Type type, String packagename){ this(name); this.accessmodifier.add(accesstype);   this.type=type; this.packagename=packagename; }



    public String getName()    { return name;      }

    public String toString() {
       // for (int i = 0; i < accessmodifier.size(); i++) {
          //  AccessModifier accesstype = accessmodifier.get(i);


           // if (accesstype != AccessModifier.tINVALID)
                return '<' + getName() + ":" + "accessmodofier is:"+accessmodifier +"  "+  "type is:" + "  "+ type+"  "+"package name is:"+packagename+ '>';

       // }
        //return getName();
    }

     //public Symbol Parent=null;
    public  Symbol Parent=null;

    public ArrayList<Symbol> Implementlist=new ArrayList<Symbol>();
    public  Symbol(String name, Symbol parent, ArrayList<Symbol> implementinterface){
        this.name=name;
        this.Parent=parent;
        this.Implementlist.addAll(implementinterface);

    }


}