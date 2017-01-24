package Symbols; /**
 * Created by saeideh on 12/19/16.
 */
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import Scopes.*;
public class MethodSymbol extends Symbol implements Scope  {
        VariableSymbol.TYPE returntype;
   public ArrayList<Symbol.AccessModifier>methodmodifier=new ArrayList<Symbol.AccessModifier>();
    Map<String, Symbol> arguments = new LinkedHashMap<String, Symbol>();
    Scope enclosingScope;

    public MethodSymbol(String name, ArrayList<Symbol.AccessModifier>accesstype, VariableSymbol.TYPE returntype, Scope enclosingScope) {
        super(name);
       methodmodifier.addAll(accesstype);
        this.returntype=returntype;
        this.enclosingScope = enclosingScope;
    }

  public Symbol resolve(String name) {
        Symbol s = arguments.get(name);
        if (s !=null) return s;
        // if not here, check any enclosing scope
        if (getEnclosingScope() != null) {
            return getEnclosingScope().resolve(name);
        }
        return null; // not found
    }

    public Symbol resolve1(String name){
        Symbol s=arguments.get(name);
        return  s;

    }

    public void define(Symbol sym) {

        arguments.put(sym.name, sym);
        sym.scope = this; // track the scope in each symbol
    }
    public Map<String,Symbol> symboltableshow(){
        return arguments;

    }

    public Scope getEnclosingScope() { return enclosingScope; }
    public String getScopeName() { return name; }

    public String toString() { return "Method:"+this.name+methodmodifier+"returntype is:"+returntype+" args:"+arguments.keySet(); }

}

