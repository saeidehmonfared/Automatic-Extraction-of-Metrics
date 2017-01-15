package ASTGenerator; /**
 * Created by saeideh on 12/11/16.
 */
import Scopes.ClassScope;
import Scopes.GlobalScope;
import Scopes.Scope;
import org.antlr.runtime.tree.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.*;
import com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.v4.misc.Graph;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.semantics.SymbolCollector;
import XMLManipulator.*;

import sun.reflect.generics.scope.*;

import javax.xml.bind.JAXBElement.*;
import javax.xml.ws.handler.MessageContext;

import static javax.xml.bind.JAXBElement.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.*;

import ANTLRParser.*;
import Symbols.*;
import Scopes.*;


public class Defphase extends javaBaseListener {
    ParseTreeProperty<Scope> Scopes = new ParseTreeProperty<Scope>();
    ParseTreeProperty<VariableSymbol.TYPE> Types = new ParseTreeProperty<VariableSymbol.TYPE>();
    Map<Symbol, String> refrences = new LinkedHashMap<Symbol, String>();
    GlobalScope globals;
    String s;
    Scope currentscope;
    String m;
 //   public javaParser.MethodModifierContext methodmodifier;
  VariableSymbol.TYPE methodresult;
    Scope myscope;
    public String packagename="";
    public ArrayList<Symbol.AccessModifier> accessmod=new ArrayList<Symbol.AccessModifier>();
    public ArrayList<Symbol.AccessModifier> interfacemodifier=new ArrayList<Symbol.AccessModifier>();
    public ArrayList<Symbol.AccessModifier> fielsmodifier=new ArrayList<Symbol.AccessModifier>();
    public ArrayList<Symbol.AccessModifier> variablemodifier=new ArrayList<Symbol.AccessModifier>();
    ParseTreeProperty<Symbol.AccessModifier>methodmodifier1=new ParseTreeProperty<Symbol.AccessModifier>();
    public ArrayList<Symbol.AccessModifier> methodmodifier=new ArrayList<Symbol.AccessModifier>();



    void saveScope(ParserRuleContext ctx, Scope s) {
        Scopes.put(ctx, s);
    }

    public void setValue(ParseTree ctx, VariableSymbol.TYPE value) {
        Types.put(ctx, value);
    }

    public VariableSymbol.TYPE getValue(ParseTree ctx) {
        return Types.get(ctx);
    }


    @Override
    public void enterCompilationUnit(javaParser.CompilationUnitContext ctx) {
        globals = new GlobalScope(null);
        currentscope = globals;
        saveScope(ctx, currentscope);
    }

    @Override
    public void exitCompilationUnit(javaParser.CompilationUnitContext ctx) {
        currentscope=currentscope.getEnclosingScope();

        System.out.println("coupling is from this symbols:"+refrences);

        //System.out.println(StaticList.staticlist);

        Iterator<Symbol> it=StaticList.staticlist.values().iterator();
        while (it.hasNext()){
            Symbol s=it.next();

           // System.out.println(s.name+"   "+s.accessmodifier+"   "+s.type+"   "+s.packagename);
        }




    }

    // --------------------------------------------------------------

    @Override public void enterPackageDeclaration(javaParser.PackageDeclarationContext ctx) {

        for(int i=1;i<ctx.getChildCount()-1;i++){
             packagename=packagename+ctx.getChild(i).getText();
        }
        packagename=packagename+".";

       // System.out.println("mypackname:"+packagename);
    }

    @Override public void exitPackageDeclaration(javaParser.PackageDeclarationContext ctx) {



    }
    //--------------------------------------------------------------------------------------------






    //--------------------------------------------------------------------------------------


    public void enterClassDeclaration(javaParser.ClassDeclarationContext ctx) {
    }

    @Override
    public void exitClassDeclaration(javaParser.ClassDeclarationContext ctx) {
    }

    //------------------------------------------------------------------

    @Override
    public void enterNormalClassDeclaration(javaParser.NormalClassDeclarationContext ctx) {


        currentscope = new ClassScope(currentscope);// push scope

    }




    @Override
    public void exitNormalClassDeclaration(javaParser.NormalClassDeclarationContext ctx) {


        saveScope(ctx, currentscope);
        System.out.println(currentscope);


        currentscope = currentscope.getEnclosingScope();
        s = ctx.Identifier().getText().toString();
            boolean a=true;
        for(int i=0;i<accessmod.size();i++)
        {
            Symbol.AccessModifier access=accessmod.get(i);
            if ((access== Symbol.AccessModifier.tpublic)|| (access== Symbol.AccessModifier.tprivate)||(access== Symbol          .AccessModifier.tprotected)) a=false;

        }
        if(a){
            accessmod.add(Symbol.AccessModifier.tpublic);}

        Symbol C = new Symbol(s, accessmod,Symbol.Type.tCLASS,packagename);
            //System.out.println("Accessmodofier of this class is:"+C.accessmodifier);
        currentscope.define(C);
        StaticList.insert(C);

        //currentscope = new ClassScope(currentscope);// push scope
        //saveScope(ctx, currentscope);




        System.out.println(currentscope);

        accessmod.clear();



    }
    //---------------------------------------------------------------

    @Override public void enterInterfaceDeclaration(javaParser.InterfaceDeclarationContext ctx) {

        }


    @Override public void exitInterfaceDeclaration(javaParser.InterfaceDeclarationContext ctx) { }


//---------------------------------------------------------------------------------------------
@Override public void enterNormalInterfaceDeclaration(javaParser.NormalInterfaceDeclarationContext ctx) {

}
    //System.out.println(StaticList.staticlist);


    @Override public void exitNormalInterfaceDeclaration(javaParser.NormalInterfaceDeclarationContext ctx) {

        String s1 = ctx.Identifier().getText().toString();


            boolean a=true;
        for(int i=0;i<interfacemodifier.size();i++)
        {
            Symbol.AccessModifier access=interfacemodifier.get(i);
            if ((access== Symbol.AccessModifier.tpublic)|| (access== Symbol.AccessModifier.tprivate)||(access== Symbol          .AccessModifier.tprotected)) a=false;

        }
        if(a){
            interfacemodifier.add(Symbol.AccessModifier.tpublic);}

        Symbol C = new Symbol(s1, interfacemodifier,Symbol.Type.tINTERFACE,packagename);
       //System.out.println("Accessmodofier of this interface is:"+C.accessmodifier);
        currentscope.define(C);
        StaticList.insert(C);
        interfacemodifier.clear();

        }

   //-----------------------------------------------------------------------------------------------------
    public void enterMethodDeclaration(javaParser.MethodDeclarationContext ctx) {



    }


    @Override
    public void exitMethodDeclaration(javaParser.MethodDeclarationContext ctx) {

        System.out.println(currentscope);
        saveScope(ctx,currentscope);
        currentscope = currentscope.getEnclosingScope();
        methodmodifier.clear();




    }

    //---------------------------------------------------------------

    @Override public void enterMethodDeclarator(javaParser.MethodDeclaratorContext ctx) {


        String methodname = ctx.getChild(0).getText();


        boolean a=true;
        if(!(methodmodifier.equals(null))) {
            for (int i = 0; i < methodmodifier.size(); i++) {
                Symbol.AccessModifier access = methodmodifier.get(i);
                if ((access == Symbol.AccessModifier.tpublic) || (access == Symbol.AccessModifier.tprivate) || (access == Symbol.AccessModifier.tprotected))
                    a = false;
            }

            if (a) {
                methodmodifier.add(Symbol.AccessModifier.tpublic);
            }
        }
        else if (methodmodifier.equals(null))
        {
            methodmodifier.add(Symbol.AccessModifier.tpublic);
        }




        MethodSymbol ms = new MethodSymbol(methodname, methodmodifier,methodresult, currentscope);

        if(methodresult.equals(VariableSymbol.TYPE.TREFRENCE)){


            refrences.put(ms,methodresulttype);
            //System.out.println(s);


        }



        currentscope.define(ms);

        saveScope(ctx, ms);
        currentscope = ms;






    }
    String methodresulttype;

    @Override public void exitMethodDeclarator(javaParser.MethodDeclaratorContext ctx) {
       //System.out.println(currentscope);
        //saveScope(ctx, currentscope);
        //currentscope = currentscope.getEnclosingScope();
       // methodmodifier.clear();



    }


    //----------------------------------------------------------------

    @Override public void enterMethodHeader(javaParser.MethodHeaderContext ctx) { }

    @Override public void exitMethodHeader(javaParser.MethodHeaderContext ctx) {










    }
    //------------------------------------------------------------
    @Override public void enterResultunannType(javaParser.ResultunannTypeContext ctx) { }

    @Override public void exitResultunannType(javaParser.ResultunannTypeContext ctx) {
        methodresult=getValue(ctx.unannType());
        if(methodresult.equals(VariableSymbol.TYPE.TREFRENCE)){
        methodresulttype=ctx.unannType().unannReferenceType().unannClassOrInterfaceType().unannClassType_lfno_unannClassOrInterfaceType().getText();}

    }

//----------------------------------------------------------------------------------
@Override public void enterResultVoidType(javaParser.ResultVoidTypeContext ctx) { }

    @Override public void exitResultVoidType(javaParser.ResultVoidTypeContext ctx) {
        int m =ctx.start.getType();

        methodresult=CheckVariableSymbol.getType(m);

    }
    //---------------------------------------------------------------------

    @Override public void enterMethodtest2(javaParser.Methodtest2Context ctx) { }

    @Override public void exitMethodtest2(javaParser.Methodtest2Context ctx) {





    }

    //--------------------------------------------------------------------------



   //...............................................................

    @Override
    public void enterFormalParameters(javaParser.FormalParametersContext ctx) {
        //String[] par = new String[4];

    }


    @Override
    public void exitFormalParameters(javaParser.FormalParametersContext ctx) {



    }
    //----------------------------------------------------------------------

    @Override public void enterFormalParameter(javaParser.FormalParameterContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitFormalParameter(javaParser.FormalParameterContext ctx) {
        Symbol.AccessModifier accesstype = null;

            m = ctx.variableDeclaratorId().getText();
            VariableSymbol.TYPE type = getValue(ctx.getChild(0));

            VariableSymbol v = new VariableSymbol(m, variablemodifier, type);


        if(type.equals(VariableSymbol.TYPE.TREFRENCE)){

            String s=ctx.unannType().unannReferenceType().unannClassOrInterfaceType().unannClassType_lfno_unannClassOrInterfaceType().getText();
            refrences.put(v,s);
            //System.out.println(s);


        }
       // System.out.println(currentscope+"kojaaaaaaaaaaaim");
            currentscope.define(v);
            // System.out.println(type);

            // System.out.println(currentscope);
            //currentscope = currentscope.getEnclosingScope();

        }

    //----------------------------------------------------------------
    @Override public void enterLastFormalParameter(javaParser.LastFormalParameterContext ctx) { }

    @Override public void exitLastFormalParameter(javaParser.LastFormalParameterContext ctx) {
        String m=ctx.formalParameter().variableDeclaratorId().getText();
        VariableSymbol.TYPE type = getValue(ctx.formalParameter().unannType());
        VariableSymbol v = new VariableSymbol(m, variablemodifier, type);

        if(type.equals(VariableSymbol.TYPE.TREFRENCE)){

           String s=ctx.formalParameter().unannType().unannReferenceType().unannClassOrInterfaceType().unannClassType_lfno_unannClassOrInterfaceType().getText();

            refrences.put(v,s);
            //System.out.println(s);


        }
        currentscope.define(v);
       //System.out.println(type);
    }
    //------------------------------------------------------------------

    @Override public void enterUnannArrayType(javaParser.UnannArrayTypeContext ctx) {

    }

    @Override public void exitUnannArrayType(javaParser.UnannArrayTypeContext ctx) {
        VariableSymbol.TYPE type = getValue(ctx.getChild(0));
        setValue(ctx,type);



    }

    @Override
    public void enterBlock(javaParser.BlockContext ctx) {
        saveScope(ctx, currentscope);
        currentscope = new BlockScope(currentscope);

    }

    @Override
    public void exitBlock(javaParser.BlockContext ctx) {
        myscope=currentscope;
        saveScope(ctx, currentscope);
        System.out.println(currentscope);
        currentscope = currentscope.getEnclosingScope();

    }

    //-------------------------------------------------------------------

    @Override
    //public void enterFieldDeclaration(javaParser.FieldDeclarationContext ctx) {
       // String varname;
        //****javaParser.UnannPrimitiveTypeContext type1 = ctx.unannType().unannPrimitiveType();
        //javaParser.UnannClassType_lfno_unannClassOrInterfaceTypeContext type2=ctx.unannType().unannReferenceType().unannClassOrInterfaceType().unannClassType_lfno_unannClassOrInterfaceType();
       //*** int t1 = type1.start.getType();
        // int t2=type2.start.getType();
      //  VariableSymbol.TYPE type = CheckVariableSymbol.getType(t1);
        //**** VariableSymbol.TYPE type=CheckVariableSymbol.getType(t2);

       // javaParser.FieldModifierContext c = ctx.fieldModifier().get(0);
        //int m = c.start.getType();
        //VariableSymbol.AccessModifier rettype = CheckSymbols.getAccessmodifierType(m);


        //varname = ctx.variableDeclaratorList().variableDeclarator(0).variableDeclaratorId().getText();
       //** VariableSymbol var = new VariableSymbol(varname, rettype, type);
        //**currentscope.define(var);
       //** saveScope(ctx, currentscope);

   // }




    public void enterFielddeclaration1(javaParser.Fielddeclaration1Context ctx) { }

    @Override public void exitFielddeclaration1(javaParser.Fielddeclaration1Context ctx) {
        String name=ctx.variableDeclaratorList().variableDeclarator(0).variableDeclaratorId().getText();
        VariableSymbol.TYPE type = getValue(ctx.unannType());


        fielsmodifier.add(Symbol.AccessModifier.tpublic);
        VariableSymbol var = new VariableSymbol(name, fielsmodifier, type);
        if(type.equals(VariableSymbol.TYPE.TREFRENCE)  ){
            String s=ctx.unannType().unannReferenceType().unannClassOrInterfaceType().unannClassType_lfno_unannClassOrInterfaceType().getText();
            refrences.put(var,s);

        }
        currentscope.define(var);
        saveScope(ctx, currentscope);
        fielsmodifier.clear();

    }



    //---------------------------------------------------------------------

    @Override public void enterFielddeclaration2(javaParser.Fielddeclaration2Context ctx) { }

    @Override public void exitFielddeclaration2(javaParser.Fielddeclaration2Context ctx) {

        String varname;



        varname = ctx.variableDeclaratorList().variableDeclarator(0).variableDeclaratorId().getText();
        VariableSymbol.TYPE type = getValue(ctx.unannType());

        boolean a=true;
        for(int i=0;i<fielsmodifier.size();i++)
        {
            Symbol.AccessModifier fieldmodifier=fielsmodifier.get(i);
            if ((fieldmodifier== Symbol.AccessModifier.tpublic)|| (fieldmodifier== Symbol.AccessModifier.tprivate)||(fieldmodifier== Symbol.AccessModifier.tprotected)) a=false;

        }
        if(a){
            fielsmodifier.add(Symbol.AccessModifier.tpublic);}

        VariableSymbol var = new VariableSymbol(varname, fielsmodifier, type);

        if(type==VariableSymbol.TYPE.TREFRENCE){
            String s=ctx.unannType().unannReferenceType().unannClassOrInterfaceType().unannClassType_lfno_unannClassOrInterfaceType().getText();
            refrences.put(var,s);
            //System.out.println(s);
        }
        currentscope.define(var);
        saveScope(ctx, currentscope);
        fielsmodifier.clear();

    }


    //---------------------------------------------------------------------

    @Override
    public void enterLocalVariableDeclaration(javaParser.LocalVariableDeclarationContext ctx) {
       // String varname;
        //Symbol.AccessModifier rettype = null;
        //varname = ctx.variableDeclaratorList().variableDeclarator(0).variableDeclaratorId().getText();
        //VariableSymbol var = new VariableSymbol(varname, rettype, null);
        //currentscope.define(var);
       // saveScope(ctx, currentscope);

    }

    @Override
    public void exitLocalVariableDeclaration(javaParser.LocalVariableDeclarationContext ctx) {
        String varname;
         varname=ctx.variableDeclaratorList().variableDeclarator(0).variableDeclaratorId().getText();
        //Symbol.AccessModifier rettype = variablemodifier.get(0);
        VariableSymbol.TYPE type = getValue(ctx.getChild(0));

        //System.out.println(varname);
        VariableSymbol var = new VariableSymbol(varname, variablemodifier, type);
        if(type.equals(VariableSymbol.TYPE.TREFRENCE)){

            String s=ctx.unannType().unannReferenceType().unannClassOrInterfaceType().unannClassType_lfno_unannClassOrInterfaceType().getText();
            refrences.put(var,s);
            //System.out.println(s);


        }

        // System.out.println(currentscope+"alan kojaiim");
        currentscope.define(var);
        saveScope(ctx, currentscope);
            variablemodifier.clear();

       // javaParser.ctx.getChild(0).getText();
        //javaParser.Loca


        //javaParser.UnannClassOrInterfaceTypeContext vartype =ctx.unannType().unannReferenceType().unannClassOrInterfaceType().unannClassType_lfno_unannClassOrInterfaceType();
        // System.out.println(currentscope);
        //currentscope = currentscope.getEnclosingScope();

    }

    //-------------------------------------------------------------------------

    @Override
    public void enterUnannType(javaParser.UnannTypeContext ctx) {


    }

    @Override
    public void exitUnannType(javaParser.UnannTypeContext ctx) {
        VariableSymbol.TYPE type = getValue(ctx.getChild(0));
        setValue(ctx,type);

    }

    //-------------------------------------------------------------------------
    @Override
    public void enterUnannPrimitiveType(javaParser.UnannPrimitiveTypeContext ctx) {
    }

    @Override
    public void exitUnannPrimitiveType(javaParser.UnannPrimitiveTypeContext ctx) {
        int t =ctx.start.getType();

        VariableSymbol.TYPE type=CheckVariableSymbol.getType(t);
        setValue(ctx,type);
       // System.out.println(type);

    }
    //-----------------------------------------------------------------------------
    @Override public void enterUnannReferenceType(javaParser.UnannReferenceTypeContext ctx) {

    }

    @Override public void exitUnannReferenceType(javaParser.UnannReferenceTypeContext ctx) {
        VariableSymbol.TYPE type = getValue(ctx.getChild(0));
        setValue(ctx,type);
        //System.out.println(type);
    }

    //------------------------------------------------------------------------------
    @Override public void enterUnannClassOrInterfaceType(javaParser.UnannClassOrInterfaceTypeContext ctx) { }

    @Override public void exitUnannClassOrInterfaceType(javaParser.UnannClassOrInterfaceTypeContext ctx) {
        VariableSymbol.TYPE type = getValue(ctx.getChild(0));
        setValue(ctx,type);
        //System.out.println(type);

    }

    //------------------------------------------------------------------------------------

    @Override public void enterUnannClassType_lfno_unannClassOrInterfaceType(javaParser.UnannClassType_lfno_unannClassOrInterfaceTypeContext ctx) { }

    @Override public void exitUnannClassType_lfno_unannClassOrInterfaceType(javaParser.UnannClassType_lfno_unannClassOrInterfaceTypeContext ctx) {
        int t =ctx.start.getType();

        VariableSymbol.TYPE type=CheckVariableSymbol.getType(t);
        setValue(ctx,type);

    }
    //--------------------------------------------------------------------------------------

    @Override public void enterClassModifier(javaParser.ClassModifierContext ctx) { }

    @Override public void exitClassModifier(javaParser.ClassModifierContext ctx) {
        int m=ctx.start.getType();
        Symbol.AccessModifier accesstype=CheckSymbols.getAccessmodifierType(m);
        this.accessmod.add(accesstype);
    }
    //------------------------------------------------------------------------------------------
    @Override public void enterInterfaceModifier(javaParser.InterfaceModifierContext ctx) { }

    @Override public void exitInterfaceModifier(javaParser.InterfaceModifierContext ctx) {
        int m =ctx.start.getType();
        Symbol.AccessModifier interfacemodifier=CheckSymbols.getAccessmodifierType(m);
        this.interfacemodifier.add(interfacemodifier);
    }
    //-----------------------------------------------------------------------------------------

    @Override public void enterFieldModifier(javaParser.FieldModifierContext ctx) { }

    @Override public void exitFieldModifier(javaParser.FieldModifierContext ctx) {
        int m=ctx.start.getType();
        Symbol.AccessModifier fieldmodifier=CheckSymbols.getAccessmodifierType(m);
        fielsmodifier.add(fieldmodifier);
    }
    //---------------------------------------------------------------------------------------------
    @Override public void enterVariableModifier(javaParser.VariableModifierContext ctx) { }

    @Override public void exitVariableModifier(javaParser.VariableModifierContext ctx) {
        int m=ctx.start.getType();
        Symbol.AccessModifier varmodifier=CheckSymbols.getAccessmodifierType(m);
        variablemodifier.add(varmodifier);

    }
    //-----------------------------------------------------------------------------------------------
    @Override public void enterMethodModifier(javaParser.MethodModifierContext ctx) {


    }

    @Override public void exitMethodModifier(javaParser.MethodModifierContext ctx) {
        //String name = ctx.getText();


          int m=ctx.start.getType();
          Symbol.AccessModifier methodmod=CheckSymbols.getAccessmodifierType(m);
       //methodmodifier1.put(ctx,methodmod);
            methodmodifier.add(methodmod);



    }
    //----------------------------------------------------------------------------------------------------

    @Override public void enterConstructorDeclaration(javaParser.ConstructorDeclarationContext ctx) {

    }

    @Override public void exitConstructorDeclaration(javaParser.ConstructorDeclarationContext ctx) {
        System.out.println(currentscope);
        currentscope = currentscope.getEnclosingScope();
        saveScope(ctx,currentscope);
        methodmodifier.clear();
    }
    //------------------------------------------------------------------------------------------------------

    @Override public void enterConstructorDeclarator(javaParser.ConstructorDeclaratorContext ctx) {
        String methodname = ctx.simpleTypeName().getText();

        //System.out.println(methodmodifier+"hhhhhhhhhhhhhhhhhhhhh");

        if(methodmodifier.equals(null)){
            methodmodifier.add(Symbol.AccessModifier.tprotected);
        }
        MethodSymbol ms = new MethodSymbol(methodname, methodmodifier,null, currentscope);

        currentscope.define(ms);
        saveScope(ctx, ms);
        currentscope = ms;
        methodresult=null;

    }

    @Override public void exitConstructorDeclarator(javaParser.ConstructorDeclaratorContext ctx) { }
//----------------------------------------------------------------------------------------------------------

    @Override public void enterConstructorModifier(javaParser.ConstructorModifierContext ctx) { }

    @Override public void exitConstructorModifier(javaParser.ConstructorModifierContext ctx) {
        int m =ctx.start.getType();
        Symbol.AccessModifier access=CheckSymbols.getAccessmodifierType(m);
        methodmodifier.add(access);
    }

    //---------------------------------------------------------------------------------
    @Override public void enterConstructorBlock(javaParser.ConstructorBlockContext ctx) {
        saveScope(ctx, currentscope);
        currentscope = new BlockScope(currentscope);

    }

    @Override public void exitConstructorBlock(javaParser.ConstructorBlockContext ctx) {
        myscope=currentscope;
        saveScope(ctx, currentscope);
       // System.out.println(currentscope+"whaaaaaaaaaaaaaaat");
        currentscope = currentscope.getEnclosingScope();
    }




}

