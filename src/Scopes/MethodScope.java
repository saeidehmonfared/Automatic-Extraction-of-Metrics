/**
 * Created by saeideh on 12/27/16.
 */
package Scopes;

public class MethodScope extends ClassScope {
    public MethodScope(Scope enclosingScope) { super(enclosingScope);
        this.enclosingScope = enclosingScope;
    }

    public String getScopeName() { return "Methods"; }
}

