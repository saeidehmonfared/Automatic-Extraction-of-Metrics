package Scopes;
import Symbols.*;

import java.util.Map;

public interface  Scope {
    public String getScopeName();

    /** Where to look next for symbols */
    public Scope getEnclosingScope();

    /** Define a symbol in the current scope */
    public void define(Symbol sym);

    /** Look up name in this scope or in enclosing scope if not here */
    public Symbol resolve(String name);
    public Symbol resolve1(String name);
    public Map<String,Symbol> symboltableshow();

}
