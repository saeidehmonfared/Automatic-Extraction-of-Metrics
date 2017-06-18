package MetricExtraction.CouplingExtraction;
import ANTLRParser.*;
import ASTGenerator.Inheritancelist;
import ASTGenerator.StaticList;
import Scopes.ClassScope;
import Scopes.GlobalScope;
import Scopes.Scope;
import Symbols.MethodSymbol;
import Symbols.Symbol;
import java.lang.String;

import Symbols.VariableSymbol;
import com.sun.org.apache.xpath.internal.SourceTree;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Couplingmetric2 extends javaBaseListener{
    public static Map<String,ArrayList<Invoc>>allcouplinglistofclass=new LinkedHashMap<>();
    public ArrayList<Object> objectinstances = new ArrayList<Object>();
    Map<String, Symbol> importlistofclass = new LinkedHashMap<String, Symbol>();
    public ArrayList<Symbol> Inheritancelistofclass = new ArrayList<Symbol>();
    ParseTreeProperty<Scope> scopes;
    GlobalScope globals;
    Scope Currentscope;
    String packagename="";
    Map<String,Map<String,ArrayList<String>>>couplinglistofclass=new LinkedHashMap<>();

    public Couplingmetric2(GlobalScope globals, ParseTreeProperty<Scope> Scopes, Map<String, Symbol> importlist, ArrayList<Symbol> inheritancelistofclass, ArrayList<Object> objectinstances) {
        this.globals = globals;
        this.scopes = Scopes;
        this.importlistofclass = importlist;
        this.Inheritancelistofclass = inheritancelistofclass;
        this.objectinstances=objectinstances;

    }

    public void enterCompilationUnit(javaParser.CompilationUnitContext ctx) {
        Currentscope = globals;



    }
    public void exitCompilationUnit(javaParser.CompilationUnitContext ctx) {
        for(String classname: couplinglistofclass.keySet()){
            System.out.println("class name:"+classname);
            System.out.println("association relations:");
            Iterator<String> it0=couplinglistofclass.get(classname).get("associationrelation").iterator();
            while ((it0.hasNext())){
                String objectclassname=it0.next();
                System.out.println(objectclassname);
                Iterator<Invoc>it1=allcouplinglistofclass.get("associationrelation").iterator();
                while (it1.hasNext()){
                    Invoc n=it1.next();
                    if(n.classname.equals(objectclassname)){
                        System.out.println(n.name+n.invoctype+n.relationType);
                    }
                }
            }
            System.out.println("dependency relations:");
            Iterator<String> it1=couplinglistofclass.get(classname).get("dependencyrelation").iterator();
            while ((it1.hasNext())){
                String objectclassname=it1.next();
                System.out.println(objectclassname);
                Iterator<Invoc>it2=allcouplinglistofclass.get("dependencyrelation").iterator();
                while (it2.hasNext()){
                    Invoc n=it2.next();
                    if(n.classname.equals(objectclassname)){
                        System.out.println(n.name+n.invoctype+n.relationType);
                    }
                }
            }
        }

    }


    public void enterPackageDeclaration(javaParser.PackageDeclarationContext ctx) {

        for (int i = 1; i < ctx.getChildCount() - 1; i++) {
                      packagename = packagename + ctx.getChild(i).getText();
                }
        packagename = packagename + ".";
    }


    String classname;
    @Override public void enterNormalClassDeclaration1(javaParser.NormalClassDeclaration1Context ctx) {
        Currentscope = scopes.get(ctx);
        classname = ctx.Identifier().getText();
        allcouplinglistofclass.put("associationrelation",new ArrayList<>());
        allcouplinglistofclass.put("dependencyrelation",new ArrayList<>());
        couplinglistofclass.put(packagename + classname, new LinkedHashMap<>());
        couplinglistofclass.get(packagename + classname).put("associationrelation", new ArrayList<>());
        couplinglistofclass.get(packagename + classname).put("dependencyrelation", new ArrayList<>());
        boolean isinheritancedclass=false;
        Iterator<Object> it0 = objectinstances.iterator();
        while (it0.hasNext()) {
            Object objectname = it0.next();

            //Inheritance checking
                Iterator<Symbol> it1 = Inheritancelistofclass.iterator();
                while (it1.hasNext()) {
                    Symbol inheritanceclass= it1.next();
                    if ((objectname.classname.equals(inheritanceclass))) {

                        isinheritancedclass=true;
                        break;
                    }
                }
                boolean isimortedclass=false;
               Symbol s1=null;
            for (Symbol value1 : importlistofclass.values()) {
                s1 = value1;
                if (s1.name.equals(objectname.classname)) {
                    isimortedclass = true;
                }
            }

                if(!isinheritancedclass && isimortedclass) {
                    if (objectname.currentscope.getScopeName().equals("Class")) {
                        boolean duplication=false;
                        Iterator<String> it2 = couplinglistofclass.get(packagename + classname).get("associationrelation").iterator();
                        while (it2.hasNext()) {
                            String n=it2.next();
                            if(objectname.classname.equals(n))
                            {
                                duplication=true;
                            }
                        }
                        if(!duplication)
                        couplinglistofclass.get(packagename + classname).get("associationrelation").add(objectname.classname);

                    } else if (((MethodSymbol) objectname.currentscope).returntype.equals(VariableSymbol.TYPE.TCONSTRUCTOR)) {
                        boolean duplication=false;
                        Iterator<String> it2 = couplinglistofclass.get(packagename + classname).get("associationrelation").iterator();
                        while (it2.hasNext()) {
                            String n=it2.next();
                            if(objectname.classname.equals(n))
                            {
                                duplication=true;
                            }
                        }
                        if(!duplication){
                        couplinglistofclass.get(packagename + classname).get("associationrelation").add(objectname.classname);}
                    } else {
                        boolean isdependency = true;
                        for (int i = 0; i < couplinglistofclass.get(packagename + classname).get("associationrelation").size(); i++) {
                            String objclassname = couplinglistofclass.get(packagename + classname).get("associationrelation").get(i);
                            if (objectname.classname.equals(objclassname)) {
                                isdependency = false;
                            }
                        }
                        if (isdependency) {
                            boolean duplication=false;
                            Iterator<String> it2 = couplinglistofclass.get(packagename + classname).get("dependencyrelation").iterator();
                            while (it2.hasNext()) {
                                String n=it2.next();
                                if(objectname.classname.equals(n))
                                {
                                    duplication=true;
                                }
                            }
                            if(!duplication)
                            couplinglistofclass.get(packagename + classname).get("dependencyrelation").add(objectname.classname);
                        }
                    }
                }


        }

    }
    @Override public void enterNormalClassdeclaration2(javaParser.NormalClassdeclaration2Context ctx) {
        Currentscope = scopes.get(ctx);
        classname = ctx.Identifier().getText();
        allcouplinglistofclass.put("associationrelation",new ArrayList<>());
        allcouplinglistofclass.put("dependencyrelation",new ArrayList<>());
        couplinglistofclass.put(packagename + classname, new LinkedHashMap<>());
        couplinglistofclass.get(packagename + classname).put("associationrelation", new ArrayList<>());
        couplinglistofclass.get(packagename + classname).put("dependencyrelation", new ArrayList<>());
        boolean isinheritancedclass=false;
        Iterator<Object> it0 = objectinstances.iterator();
        while (it0.hasNext()) {
            Object objectname = it0.next();

            //Inheritance checking
            Iterator<Symbol> it1 = Inheritancelistofclass.iterator();
            while (it1.hasNext()) {
                Symbol inheritanceclass= it1.next();
                if ((objectname.classname.equals(inheritanceclass))) {

                    isinheritancedclass=true;
                    break;
                }
            }
            boolean isimortedclass=false;
            Symbol s1=null;
            for (Symbol value1 : importlistofclass.values()) {
                s1 = value1;
                if (s1.name.equals(objectname.classname)) {
                    isimortedclass = true;
                }
            }

            if(!isinheritancedclass && isimortedclass) {
                if (objectname.currentscope.getScopeName().equals("Class")) {
                    boolean duplication=false;
                    Iterator<String> it2 = couplinglistofclass.get(packagename + classname).get("associationrelation").iterator();
                    while (it2.hasNext()) {
                      String n=it2.next();
                        if(objectname.classname.equals(n))
                        {
                            duplication=true;
                        }
                    }
                    if(!duplication)
                        couplinglistofclass.get(packagename + classname).get("associationrelation").add(objectname.classname);

                } else if (((MethodSymbol) objectname.currentscope).returntype.equals(VariableSymbol.TYPE.TCONSTRUCTOR)) {
                    boolean duplication=false;
                    Iterator<String> it2 = couplinglistofclass.get(packagename + classname).get("associationrelation").iterator();
                    while (it2.hasNext()) {
                        String n=it2.next();
                        if(objectname.classname.equals(n))
                        {
                            duplication=true;
                        }
                    }

                    if(!duplication){
                        couplinglistofclass.get(packagename + classname).get("associationrelation").add(objectname.classname);}
                } else {
                    boolean isdependency = true;
                    for (int i = 0; i < couplinglistofclass.get(packagename + classname).get("associationrelation").size(); i++) {
                        String objclassname = couplinglistofclass.get(packagename + classname).get("associationrelation").get(i);
                        if (objectname.classname.equals(objclassname)) {
                            isdependency = false;
                        }
                    }
                    if (isdependency) {
                        boolean duplication=false;
                        Iterator<String> it2 = couplinglistofclass.get(packagename + classname).get("dependencyrelation").iterator();
                        while (it2.hasNext()) {
                            String n=it2.next();
                            if(objectname.classname.equals(n))
                            {
                                duplication=true;
                            }
                        }
                        if(!duplication)
                            couplinglistofclass.get(packagename + classname).get("dependencyrelation").add(objectname.classname);
                    }
                }
            }


        }

    }

    public void enterMethodDeclaration(javaParser.MethodDeclarationContext ctx) {
        Currentscope = scopes.get(ctx);

    }
    public void exitMethodDeclaration(javaParser.MethodDeclarationContext ctx) {

        Currentscope = Currentscope.getEnclosingScope();
    }



    public void enterExpertionName2(javaParser.ExpertionName2Context ctx) {
        String objectname = ctx.ambiguousName().Identifier().toString();
        String classname = null;
        boolean coupling = true;
        Invoc.RelationType relation = Invoc.RelationType.INVALID;
        ArrayList<Object> candidatelist = new ArrayList<>();
        Iterator<Object> it0 = objectinstances.iterator();
        while ((it0.hasNext())) {
            Object obj = it0.next();
            if (objectname.equals(obj.symbol.name)) {
                candidatelist.add(obj);
            }
        }
        Object primaryobject = null;
        Iterator<Object> it1 = candidatelist.iterator();
        while (it1.hasNext()) {
            Object candidateobject = it1.next();
            if (candidateobject.currentscope.getClass().getName().equals("Symbols.MethodSymbol")) {
                primaryobject = candidateobject;

            }

        }
        if (primaryobject == null && (!candidatelist.isEmpty())) {
            primaryobject = candidatelist.get(0);
        }

        boolean associationrelation=false;
        if(!(primaryobject==null)) {
            for (String name : couplinglistofclass.keySet()) {

                Iterator<String> it2 = couplinglistofclass.get(name).get("associationrelation").iterator();
                while (it2.hasNext()) {
                    String name1 = it2.next();
                    if (primaryobject.classname.equals(name1)) {
                        associationrelation = true;
                        relation = Invoc.RelationType.ASSOSIATION;
                        Invoc inv = new Invoc(objectname, ctx.Identifier().getText(), Invoc.InvocType.ATTRIBUTEINVOC, relation, Currentscope,primaryobject.classname);
                        allcouplinglistofclass.get("associationrelation").add(inv);

                    }

                }
                break;
            }

            if (!associationrelation) {
                relation = Invoc.RelationType.DEPENDENCY;
                Invoc inv = new Invoc(objectname, ctx.Identifier().getText(), Invoc.InvocType.ATTRIBUTEINVOC, relation, Currentscope,primaryobject.classname);
                allcouplinglistofclass.get("dependencyrelation").add(inv);

            }
        }
    }

    public void enterMethodInvoc2(javaParser.MethodInvoc2Context ctx) {
        String objectname = ctx.typeName().getText();
        String classname = null;
        boolean coupling = true;
        Invoc.RelationType relation = Invoc.RelationType.INVALID;
        ArrayList<Object> candidatelist = new ArrayList<>();
        Iterator<Object> it0 = objectinstances.iterator();
        while ((it0.hasNext())) {
            Object obj = it0.next();
            if (objectname.equals(obj.symbol.name)) {
                candidatelist.add(obj);
            }
        }
        Object primaryobject = null;
        Iterator<Object> it1 = candidatelist.iterator();
        while (it1.hasNext()) {
            Object candidateobject = it1.next();
            if (candidateobject.currentscope.getClass().getName().equals("Symbols.MethodSymbol")) {
                primaryobject = candidateobject;

            }

        }
        if (primaryobject == null && (!candidatelist.isEmpty())) {
            primaryobject = candidatelist.get(0);
        }

        boolean associationrelation = false;
        if (!(primaryobject == null)) {
            for (String name : couplinglistofclass.keySet()) {

                Iterator<String> it2 = couplinglistofclass.get(name).get("associationrelation").iterator();
                while (it2.hasNext()) {
                    String name1 = it2.next();
                    if (primaryobject.classname.equals(name1)) {
                        associationrelation = true;
                        relation = Invoc.RelationType.ASSOSIATION;
                        Invoc inv = new Invoc(objectname, ctx.Identifier().getText(), Invoc.InvocType.METHODINVOC, relation, Currentscope, primaryobject.classname);
                        allcouplinglistofclass.get("associationrelation").add(inv);

                    }

                }
                break;
            }

            if (!associationrelation) {
                relation = Invoc.RelationType.DEPENDENCY;
                Invoc inv = new Invoc(objectname, ctx.Identifier().getText(), Invoc.InvocType.METHODINVOC, relation, Currentscope, primaryobject.classname);
                allcouplinglistofclass.get("dependencyrelation").add(inv);

            }
        }
    }

    @Override public void enterMethodinvocation_lfno_primary2(javaParser.Methodinvocation_lfno_primary2Context ctx) {
        String objectname = ctx.typeName().getText();
        String classname = null;
        boolean coupling = true;
        Invoc.RelationType relation = Invoc.RelationType.INVALID;
        ArrayList<Object> candidatelist = new ArrayList<>();
        Iterator<Object> it0 = objectinstances.iterator();
        while ((it0.hasNext())) {
            Object obj = it0.next();
            if (objectname.equals(obj.symbol.name)) {
                candidatelist.add(obj);
            }
        }
        Object primaryobject = null;
        Iterator<Object> it1 = candidatelist.iterator();
        while (it1.hasNext()) {
            Object candidateobject = it1.next();
            if (candidateobject.currentscope.getClass().getName().equals("Symbols.MethodSymbol")) {
                primaryobject = candidateobject;

            }

        }
        if (primaryobject == null && (!candidatelist.isEmpty())) {
            primaryobject = candidatelist.get(0);
        }

        boolean associationrelation = false;
        if (!(primaryobject == null)) {
            for (String name : couplinglistofclass.keySet()) {

                Iterator<String> it2 = couplinglistofclass.get(name).get("associationrelation").iterator();
                while (it2.hasNext()) {
                    String name1 = it2.next();
                    if (primaryobject.classname.equals(name1)) {
                        associationrelation = true;
                        relation = Invoc.RelationType.ASSOSIATION;
                        Invoc inv = new Invoc(objectname, ctx.Identifier().getText(), Invoc.InvocType.METHODINVOC, relation, Currentscope, primaryobject.classname);
                        allcouplinglistofclass.get("associationrelation").add(inv);

                    }

                }
                break;
            }

            if (!associationrelation) {
                relation = Invoc.RelationType.DEPENDENCY;
                Invoc inv = new Invoc(objectname, ctx.Identifier().getText(), Invoc.InvocType.METHODINVOC, relation, Currentscope, primaryobject.classname);
                allcouplinglistofclass.get("dependencyrelation").add(inv);

            }
        }
        }
    }


