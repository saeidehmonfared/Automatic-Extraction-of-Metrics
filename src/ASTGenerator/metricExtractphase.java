package ASTGenerator;
import ANTLRParser.*;
import Scopes.*;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import Symbols.*;

/**
 * Created by saeideh on 1/3/17.
 */
public class metricExtractphase extends javaBaseListener{
    ParseTreeProperty<Scope> scopes;
    GlobalScope globals;
    Scope currentScope;
    public metricExtractphase(GlobalScope globals, ParseTreeProperty<Scope> Scopes) {
        this.globals = globals;
        this.scopes = Scopes;
    }

        public void enterNormalClassDeclaration(javaParser.NormalClassDeclarationContext ctx) {
            currentScope=(ClassScope)scopes.get(ctx);
            //VariableSymbol var=(VariableSymbol)currentScope.resolve("m1");
           // System.out.println(var.vartype);
            System.out.println( currentScope.symboltableshow());


        }
        //-----------------------------------------------------------------
        @Override public void enterMethodDeclarator(javaParser.MethodDeclaratorContext ctx) {


            currentScope=scopes.get(ctx);
            //VariableSymbol var=(VariableSymbol)currentScope.resolve("m1");
            // System.out.println(var.vartype);
            System.out.println( currentScope);



        }

        @Override public void enterBlock(javaParser.BlockContext ctx) {


    }
    @Override public void exitBlock(javaParser.BlockContext ctx) {

        currentScope=scopes.get(ctx);
        VariableSymbol var=(VariableSymbol)currentScope.resolve("m1");
        //2System.out.println(var.vartype);
        System.out.println( currentScope.symboltableshow());
    }












}
