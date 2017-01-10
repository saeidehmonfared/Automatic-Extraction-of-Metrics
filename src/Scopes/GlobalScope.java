
//scope for imported..
package Scopes;

import Symbols.Symbol;

import java.util.Map;

public class GlobalScope extends BaseScope {
    public GlobalScope(Scope enclosingScope) { super(enclosingScope);
        this.enclosingScope = enclosingScope;
    }

    public String getScopeName() { return "globals"; }
}