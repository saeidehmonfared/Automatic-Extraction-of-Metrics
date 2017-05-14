package Scopes;

import Symbols.Symbol;

import java.util.Map;

/**
 * Created by saeideh on 1/21/17.
 */
public class InterfaceScope extends GlobalScope {
    public InterfaceScope(Scope enclosingScope) {
        super(enclosingScope);
        this.enclosingScope = enclosingScope;
    }

    public String getScopeName() { return "Interface"; }
    public Map<String,Symbol> symboltableshow(){

        return symbols;


    }
    int id=0;
    public void define(Symbol sym) {
        id++;
        symbols.put(sym.name+id, sym); //TODO
        //***sym.scope = this; // track the scope in each symbol
    }
}
