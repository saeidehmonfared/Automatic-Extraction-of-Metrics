package ASTGenerator;

import DirectoryWalker.DirectoryWalker;
import MetricExtraction.CouplingExtraction.CouplingMetrics;
import Symbols.Symbol;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import ANTLRParser.*;
import MetricExtraction.*;

/**
 * Created by saeideh on 11/30/16.
 */
public class  ASTGenerator {
    InputOutputMnipulator iom;
    LinkedList<String> compilationUnits;


    public ASTGenerator(String inDir, String outDir) throws Exception {
        compilationUnits = new LinkedList<String>();
        iom = new InputOutputMnipulator(inDir, outDir);
        DirectoryWalker dw = new DirectoryWalker(inDir);
        compilationUnits = dw.getFiles();

    }


    public void Parse() throws Exception {

        Iterator<String> it = compilationUnits.iterator();

        while (it.hasNext()) {
            try {
                String compUnit = it.next();
                String myfile = readFile(compUnit);

                String cuOutDir = iom.prepareOutdir(compUnit);
                parseFile(myfile, cuOutDir);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
       //**##** PrintStaticList();
        System.out.println("\n");

        Iterator<String> it1 = compilationUnits.iterator();

        while (it1.hasNext()) {
            try {
                String compUnit = it1.next();
                String myfile = readFile(compUnit);

                String cuOutDir = iom.prepareOutdir(compUnit);
                Map<String, Symbol> importlist = ExtractImportlist(myfile);
                ANTLRInputStream input = new ANTLRInputStream(myfile);
                javaLexer lexer = new javaLexer(input);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                javaParser parser = new javaParser(tokens);
                javaParser.CompilationUnitContext tree = parser.compilationUnit();
                Inheritancephase inheritancelistExtractor = new Inheritancephase(importlist);
                ParseTreeWalker.DEFAULT.walk(inheritancelistExtractor, tree);




            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Iterator<String> it5 = compilationUnits.iterator();

        while (it5.hasNext()) {
            try {
                String compUnit = it5.next();
                String myfile = readFile(compUnit);



                ANTLRInputStream input = new ANTLRInputStream(myfile);
                javaLexer lexer = new javaLexer(input);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                javaParser parser = new javaParser(tokens);
                javaParser.CompilationUnitContext tree = parser.compilationUnit();
                InheritanceMetrics InheritanceExtractor=new InheritanceMetrics();
                ParseTreeWalker.DEFAULT.walk(InheritanceExtractor,tree);
                Defphase symboltableextractor = new Defphase();
                ParseTreeWalker.DEFAULT.walk(symboltableextractor, tree);
                ClassLevelMetrics classmetrics=new ClassLevelMetrics(symboltableextractor.globals,symboltableextractor                  .Scopes);
                ParseTreeWalker.DEFAULT.walk(classmetrics,tree);

                Map<String, Symbol> importlist = ExtractImportlist(myfile);
                //**##**System.out.println("importlistofclass is:"+importlist);
                CouplingMetrics couplingextractor=new CouplingMetrics(symboltableextractor.globals,symboltableextractor.Scopes,symboltableextractor.refrences,importlist,InheritanceExtractor.Inheritancelistofclass,symboltableextractor.objectinstances);
                ParseTreeWalker.DEFAULT.walk(couplingextractor,tree);
                CohesionMetrics cohesinextractor=new CohesionMetrics(symboltableextractor.globals,symboltableextractor.Scopes,couplingextractor.objectinstances,importlist);
                ParseTreeWalker.DEFAULT.walk(cohesinextractor,tree);



            } catch (Exception e) {
                e.printStackTrace();
            }

        }



       /* Iterator<String> it1 = compilationUnits.iterator();

        while (it1.hasNext()) {
            try {
                String compUnit = it1.next();
                String myfile = readFile(compUnit);

                String cuOutDir = iom.prepareOutdir(compUnit);
                Map<String, Symbol> importlist = ExtractImportlist(myfile);

               // System.out.println("\n\nimport list is:" + importlist);


                ANTLRInputStream input = new ANTLRInputStream(myfile);
                javaLexer lexer = new javaLexer(input);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                javaParser parser = new javaParser(tokens);
                javaParser.CompilationUnitContext tree = parser.compilationUnit();

                Defphase symboltableextractor = new Defphase();
                ParseTreeWalker.DEFAULT.walk(symboltableextractor, tree);
                ClassLevelMetrics classmetrics=new ClassLevelMetrics(symboltableextractor.globals,symboltableextractor                  .Scopes);
                ParseTreeWalker.DEFAULT.walk(classmetrics,tree);

               /// InheritanceMetrics InheritanceExtractor=new InheritanceMetrics();
                //ParseTreeWalker.DEFAULT.walk(InheritanceExtractor,tree);


                CouplingMetrics couplingextractor=new CouplingMetrics(symboltableextractor.globals,symboltableextractor.Scopes,symboltableextractor.refrences,importlist,InheritanceExtractor.Inheritancelistofclass);
                ParseTreeWalker.DEFAULT.walk(couplingextractor,tree);




            } catch (Exception e) {
                e.printStackTrace();
            }

        }



        Iterator<String> it5 = compilationUnits.iterator();

        while (it5.hasNext()) {
            try {
                String compUnit = it5.next();
                String myfile = readFile(compUnit);



                ANTLRInputStream input = new ANTLRInputStream(myfile);
                javaLexer lexer = new javaLexer(input);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                javaParser parser = new javaParser(tokens);
                javaParser.CompilationUnitContext tree = parser.compilationUnit();
                InheritanceMetrics InheritanceExtractor=new InheritanceMetrics();
                ParseTreeWalker.DEFAULT.walk(InheritanceExtractor,tree);



            } catch (Exception e) {
                e.printStackTrace();
            }

        }*/



        //**##**System.out.println("my inheritance list is:" + Inheritancelist.inheritanclist);
        Iterator<Symbol> it3 = Inheritancelist.inheritanclist.values().iterator();

        while (it3.hasNext()) {
            try {
                Symbol s = it3.next();
                System.out.println("parent of "+s.name+" is:" + s.Parent + "\nlist of implemented interface of this class is:" + s.Implementlist);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        PackageLevelMetrics p=new PackageLevelMetrics();
        p.ListofPackages();
        p.ListofClasses();
        p.Packagemetrics();



    }
    public void parseFile(String compUnit, String outDir) {

        ANTLRInputStream input = new ANTLRInputStream(compUnit);
        javaLexer lexer = new javaLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        javaParser parser = new javaParser(tokens);
        javaParser.CompilationUnitContext tree = parser.compilationUnit();


        //**##**System.out.println(tree.toStringTree(parser));
        ExtractStaticlist(tree);


    }



    private String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader (file));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }

    public void ExtractStaticlist(javaParser.CompilationUnitContext tree){

        StaticListphase extractor=new StaticListphase();
        ParseTreeWalker.DEFAULT.walk(extractor,tree);
            }

    public Map<String,Symbol> ExtractImportlist(String compUnit){
        ANTLRInputStream input = new ANTLRInputStream(compUnit);
        javaLexer lexer = new javaLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        javaParser parser = new javaParser(tokens);
        javaParser.CompilationUnitContext tree = parser.compilationUnit();


        Importphase extractor=new Importphase();
        ParseTreeWalker.DEFAULT.walk(extractor,tree);
        //System.out.println("my importlist is::"+extractor.importlist.importlist);
        //return extractor.getImportlist();
        return extractor.importlist.importlist;
    }
    public void ExtractSymbolTables(String compUnit){
        ANTLRInputStream input = new ANTLRInputStream(compUnit);
        javaLexer lexer = new javaLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        javaParser parser = new javaParser(tokens);
        javaParser.CompilationUnitContext tree = parser.compilationUnit();


        Defphase extractor=new Defphase();
        ParseTreeWalker.DEFAULT.walk(extractor,tree);



    }
    public void PrintStaticList(){
        System.out.println("\n\n\n Staticlist is:\n");
        Iterator<Symbol> im=StaticList.staticlist.values().iterator();
        while (im.hasNext()){
            Symbol s=im.next();

            System.out.println(s.name+"   "+s.accessmodifier+"   "+s.type+"   "+s.packagename);
        }
    }

}




