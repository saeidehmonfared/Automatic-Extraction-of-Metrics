package MetricExtraction;

/**
 * Created by saeideh on 2/12/17.
 */
import ANTLRParser.*;
import MetricExtraction.CouplingExtraction.CouplingMetrics;
import MetricExtraction.CouplingExtraction.Invoc;
import Scopes.GlobalScope;
import Scopes.Scope;
import Symbols.Symbol;
import Symbols.VariableSymbol;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Iterator;

public class CohesionMetrics2 extends javaBaseListener {

    ParseTreeProperty<Scope> scopes;
    GlobalScope globals;
    Scope currentScope;
    public Map<Symbol, Map<String, ArrayList<String>>> lisofclasses = new LinkedHashMap<Symbol, Map<String, ArrayList<String>>>();
    //public static Map<String,Map<String,Map>>

    public CohesionMetrics2(GlobalScope globals, ParseTreeProperty<Scope> scopes, Map<Symbol, Map<String, ArrayList<String>>> lisofclasses) {
        this.globals = globals;
        this.scopes = scopes;
        this.lisofclasses = lisofclasses;

    }


    public void enterCompilationUnit(javaParser.CompilationUnitContext ctx) {

        currentScope = this.globals;
        System.out.println("cohesion metrics2 is:");


    }

    public void exitCompilationUnit(javaParser.CompilationUnitContext ctx) {


    }

    //---------------------------------------------------------------------------
    public void enterNormalClassDeclaration1(javaParser.NormalClassDeclaration1Context ctx) {
        currentScope = scopes.get(ctx);
    }

    public void exitNormalClassDeclaration1(javaParser.NormalClassDeclaration1Context ctx) {
        if (currentScope.getScopeName().equals("Class")) {
            currentScope = currentScope.getEnclosingScope();
        }
    }

    //------------------------------------------------------------------------
    public void enterNormalClassdeclaration2(javaParser.NormalClassdeclaration2Context ctx) {
        currentScope = scopes.get(ctx);
    }

    public void exitNormalClassdeclaration2(javaParser.NormalClassdeclaration2Context ctx) {
        currentScope = currentScope.getEnclosingScope();
    }

    //-----------------------------------------------------------------------------
    public void enterMethodDeclaration(javaParser.MethodDeclarationContext ctx) {
        currentScope = scopes.get(ctx);

    }

    @Override
    public void exitMethodDeclaration(javaParser.MethodDeclarationContext ctx) {
        currentScope = currentScope.getEnclosingScope();
    }
    //----------------------------------------------------------------------------------
}