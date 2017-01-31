package MetricExtraction;

import ANTLRParser.javaBaseListener;
import ANTLRParser.javaParser;
import Scopes.GlobalScope;
import Scopes.Scope;
import Symbols.Symbol;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by saeideh on 1/30/17.
 */
public class Defectdensitymetrics extends javaBaseListener {

    ParseTreeProperty<Scope> scopes;
    Scope currentscope;
    GlobalScope globals;
    public  int numberofcatchClause=0;
    public Defectdensitymetrics(GlobalScope globals, ParseTreeProperty<Scope>scopes){
        this.globals=globals;
        this.scopes=scopes;
    }
    Map<Symbol,String>Classcatchslist=new LinkedHashMap<Symbol,String>();

    @Override public void enterCompilationUnit(javaParser.CompilationUnitContext ctx) {
        currentscope=this.globals;
    }

    @Override public void exitCompilationUnit(javaParser.CompilationUnitContext ctx) {

        System.out.println("List of catchs of class:");
        for(Symbol value:Classcatchslist.keySet()){
            Symbol name=value;
            System.out.println("method name:"+name.name+" number of cathchs:"+Classcatchslist.get(name));
        }


    }

    //------------------------------------------------------------------------------------


    @Override public void enterNormalClassDeclaration1(javaParser.NormalClassDeclaration1Context ctx) {
        currentscope=scopes.get(ctx);
    }

    @Override public void exitNormalClassDeclaration1(javaParser.NormalClassDeclaration1Context ctx) {
        currentscope=currentscope.getEnclosingScope();
    }

    @Override public void enterNormalClassdeclaration2(javaParser.NormalClassdeclaration2Context ctx) {
        currentscope=scopes.get(ctx);
    }

    @Override public void exitNormalClassdeclaration2(javaParser.NormalClassdeclaration2Context ctx) {
        currentscope=currentscope.getEnclosingScope();
    }

    //-------------------------------------------------------------------------------------------------

    @Override public void enterMethodDeclaration(javaParser.MethodDeclarationContext ctx) {
        currentscope=scopes.get(ctx);

    }

    @Override public void exitMethodDeclaration(javaParser.MethodDeclarationContext ctx) {


        Scope myscope=currentscope.getEnclosingScope();
        while (myscope.getClass().getName().equals("Block")){
            myscope=myscope.getEnclosingScope();
        }
if(myscope.getClass().getName().equals("Symbols.Method")){
        Classcatchslist.put(((Symbol)myscope),Integer.toString(numberofcatchClause));
        numberofcatchClause=0;}

        currentscope=currentscope.getEnclosingScope();
    }

    //-------------------------------------------------------------------------------------------------------

    @Override public void enterCatches(javaParser.CatchesContext ctx) {
        for(int i=0; i<ctx.getChildCount();i++)
            numberofcatchClause++;
    }

    @Override public void exitCatches(javaParser.CatchesContext ctx) { }

    //-----------------------------------------------------------------------------------
}
