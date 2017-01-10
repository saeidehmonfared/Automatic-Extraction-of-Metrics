package ASTGenerator;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import ANTLRParser.*;
import Symbols.*;

/**
 * Created by saeideh on 12/31/16.
 */
public class Importphase extends javaBaseListener {
    public String importname = "";
    public String importnameasl;
    ImportList importlist = new ImportList();
    public String packagename="";

    public Map<String, Symbol> getImportlist(){
        return importlist.importlist;

    }
    @Override public void enterCompilationUnit(javaParser.CompilationUnitContext ctx) { }

    @Override public void exitCompilationUnit(javaParser.CompilationUnitContext ctx) {
        Iterator<Symbol> it = StaticList.staticlist.values().iterator();
        while (it.hasNext()) {

            Symbol s2 = it.next();
            // System.out.println(s2.packagename );
            Symbol s = StaticList.Resolve(packagename, s2);
            if (s != null) {



                // System.out.println(s.name );

                importlist.set(s);
            }
        }


    }
    //----------------------------------------------------------------------------------
    @Override public void enterPackageDeclaration(javaParser.PackageDeclarationContext ctx) {

        for(int i=1;i<ctx.getChildCount()-1;i++){
            packagename=packagename+ctx.getChild(i).getText();
        }
        packagename=packagename+".";

        // System.out.println("mypackname:"+packagename);
    }

    @Override public void exitPackageDeclaration(javaParser.PackageDeclarationContext ctx) {

    }

    @Override
    public void enterT1(javaParser.T1Context ctx) {
    }

    @Override
    public void exitT1(javaParser.T1Context ctx) {
        importname = importname + ctx.getText() + ".";
    }

    //----------------------------------------------------------------------------------------
    @Override public void enterT0(javaParser.T0Context ctx) { }

    @Override public void exitT0(javaParser.T0Context ctx) {importname = importname + ctx.getText() + "."; }


    @Override
    public void enterT2(javaParser.T2Context ctx) {
    }

    @Override
    public void exitT2(javaParser.T2Context ctx) {
        importname = importname + ctx.getChild(2).getText() + ".";

    }


    @Override
    public void enterSingleTypeImportDeclaration(javaParser.SingleTypeImportDeclarationContext ctx) {


    }

    @Override
    public void exitSingleTypeImportDeclaration(javaParser.SingleTypeImportDeclarationContext ctx) {
        importnameasl = importname;
        //System.out.println(importnameasl);
        importname = "";
        String name = ctx.typeName().getChild(2).getText();
        String name2;

        Iterator<Symbol> it = StaticList.staticlist.values().iterator();
        while (it.hasNext()) {

            Symbol s2 = it.next();
            // System.out.println(s2.packagename );
            Symbol s = StaticList.Resolve(importnameasl, s2);
            if (s != null) {


                // System.out.println(s.name );
                if (s.name.equals(name))
                importlist.set(s);
            }
        }

       //Symbol C = new Symbol(name, Symbol.AccessModifier.TNULL, null, importnameasl);
       //importlist.set(C);

    }

    //-------------------------------------------------------------------
    @Override
    public void enterTypeImportOnDemandDeclaration(javaParser.TypeImportOnDemandDeclarationContext ctx) {
    }

    @Override
    public void exitTypeImportOnDemandDeclaration(javaParser.TypeImportOnDemandDeclarationContext ctx) {
        importnameasl = importname;
        //System.out.println(importnameasl );
        importname = "";

        Iterator<Symbol> it = StaticList.staticlist.values().iterator();
        while (it.hasNext()) {

            Symbol s2 = it.next();
           // System.out.println(s2.packagename );
            Symbol s = StaticList.Resolve(importnameasl, s2);
            if (s != null) {
               // System.out.println(s.name );
                importlist.set(s);
            }

        }


    }


    //-------------------------------------------------------------------

    @Override
    public void enterSingleStaticImportDeclaration(javaParser.SingleStaticImportDeclarationContext ctx) {
    }

    @Override
    public void exitSingleStaticImportDeclaration(javaParser.SingleStaticImportDeclarationContext ctx) {
        importnameasl = importname;
        ///System.out.println(importnameasl );
        importname = "";
        String name = ctx.typeName().getChild(2).getText();
        String name2;
        Iterator<Symbol> it = StaticList.staticlist.values().iterator();
        while (it.hasNext()) {

            Symbol s2 = it.next();
            // System.out.println(s2.packagename );
            Symbol s = StaticList.Resolve(importnameasl, s2);
            if (s != null) {


                // System.out.println(s.name );
                if (s.name.equals(name))
                s.accessmodifier.add(Symbol.AccessModifier.TSTATICIMPORT);
                importlist.set(s);
            }

            // Symbol C = new Symbol(name, Symbol.AccessModifier.TSTATIC, null, importnameasl);
            //importlist.set(C);

        }
    }

//--------------------------------------------------------------------------------------------------------------------

    @Override
    public void enterStaticImportOnDemandDeclaration(javaParser.StaticImportOnDemandDeclarationContext ctx) {
    }

    @Override
    public void exitStaticImportOnDemandDeclaration(javaParser.StaticImportOnDemandDeclarationContext ctx) {

        importnameasl = importname;
       // System.out.println(importnameasl );
        importname = "";

        Iterator<Symbol> it = StaticList.staticlist.values().iterator();
        while (it.hasNext()) {

            Symbol s2 = it.next();
           // System.out.println(s2.packagename );
            Symbol s = StaticList.Resolve(importnameasl, s2);
            if (s != null) {
                s.accessmodifier.add(Symbol.AccessModifier.TSTATICIMPORT);
               // System.out.println(s.name );
                importlist.set(s);
            }


        }
    }

//-----------------------------------------------------------------------------------------------------------------------




    }
//-------------------------------------------------------------
