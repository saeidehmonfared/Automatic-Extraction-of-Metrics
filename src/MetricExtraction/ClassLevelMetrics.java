package MetricExtraction;
import ANTLRParser.*;
import ASTGenerator.StaticList;
import Scopes.ClassScope;
import Scopes.GlobalScope;
import Scopes.Scope;
import Symbols.Symbol;
import Symbols.VariableSymbol;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.Iterator;

/**
 * Created by saeideh on 1/9/17.
 */
public class ClassLevelMetrics extends javaBaseListener{
    ParseTreeProperty<Scope> scopes;
    GlobalScope globals;
    Scope currentScope;
    public ClassLevelMetrics(GlobalScope globals, ParseTreeProperty<Scope> Scopes) {
        this.globals = globals;
        this.scopes = Scopes;
    }


    public void enterCompilationUnit(javaParser.CompilationUnitContext ctx) {
        currentScope = this.globals;
        for (Symbol value : currentScope.symboltableshow().values()) {
            Symbol s = value;
            System.out.println("Class name is:" + s.name + "\npackage of this class is:" + s.packagename);


        }
    }

    public void exitCompilationUnit(javaParser.CompilationUnitContext ctx) {


        }


    public void enterNormalClassDeclaration(javaParser.NormalClassDeclarationContext ctx) {
        currentScope=(ClassScope)scopes.get(ctx);
        int Methodclasscount=0;

        for(Symbol value:currentScope.symboltableshow().values())
        {
            Symbol s=value;
           if(s.getClass().getName().equals("MethodSymbol")){
               Methodclasscount=Methodclasscount+1;


           }


        }
        System.out.println();



    }
    //-----------------------------------------------------------------
    @Override public void enterMethodDeclarator(javaParser.MethodDeclaratorContext ctx) {


        currentScope=scopes.get(ctx);
        //VariableSymbol var=(VariableSymbol)currentScope.resolve("m1");
        // System.out.println(var.vartype);
       /// System.out.println( currentScope);



    }

    @Override public void enterBlock(javaParser.BlockContext ctx) {


    }
    @Override public void exitBlock(javaParser.BlockContext ctx) {

        //currentScope=scopes.get(ctx);
        //VariableSymbol var=(VariableSymbol)currentScope.resolve("m1");
        //2System.out.println(var.vartype);
        //System.out.println( currentScope.symboltableshow());
    }



}
