package Symbols;

import java.util.ArrayList;

/**
 * Created by saeideh on 12/19/16.
 */
public class VariableSymbol extends Symbol {

    public ArrayList<AccessModifier>variablemodifier=new ArrayList<AccessModifier>();
    public static enum TYPE{TINVALID, TINT,TFLOAT,TDOUBLE,TREFRENCE,TCHAR,TBYTE,TSHORT,TLONG,TBOOLean,TVOid}
    public TYPE vartype;
    public VariableSymbol(String name, ArrayList<AccessModifier>accesstype, TYPE vartype) {
        super(name);
        this.name=name;
        variablemodifier.addAll(accesstype);
        this.vartype=vartype;

    }

    public String toString() { return this.name+variablemodifier+this.vartype;}

    }

