package MetricExtraction;

import ANTLRParser.*;
import Symbols.Symbol;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by saeideh on 1/25/17.t
 */
public class classlevelmetrics2 extends javaBaseListener {
    ArrayList<Symbol> inheritancelist = new ArrayList<Symbol>();
    String packagename = "";
    Map<String, ArrayList<String>> metriclist1 = new LinkedHashMap<String, ArrayList<String>>();
    Map<String, ArrayList<String>> metriclist2 = new LinkedHashMap<String, ArrayList<String>>();
    int overridedmethod;
    ArrayList<String>overridedmethods=new ArrayList<String>();



    public classlevelmetrics2(ArrayList<Symbol> inheritance) {
        this.inheritancelist.addAll(inheritance);
    }

    //----------------------------------------------
    @Override
    public void enterPackageDeclaration(javaParser.PackageDeclarationContext ctx) {

        for (int i = 1; i < ctx.getChildCount() - 1; i++) {
            packagename = packagename + ctx.getChild(i).getText();
        }
        packagename = packagename + ".";

        // System.out.println("mypackname:"+packagename);
    }

    @Override
    public void exitPackageDeclaration(javaParser.PackageDeclarationContext ctx) {


    }

    //-------------------------------------------------------
    @Override
    public void enterNormalClassDeclaration1(javaParser.NormalClassDeclaration1Context ctx) {

    }


    @Override

    public void enterNormalClassdeclaration2(javaParser.NormalClassdeclaration2Context ctx) {
        ArrayList<String> allmethodmetrics = new ArrayList<String>();
        String classname = ctx.Identifier().getText();
        boolean m1 = false;
        boolean inherite = true;
        int count = 0;

        String methodname = null;

        for (Symbol value : Staticlistclasslevelmetrics.lisofclasses.keySet()) {
            Symbol classname1 = value;

            if (classname1.name.equals(classname) && (classname1.packagename.equals(packagename))) {
                metriclist1 = Staticlistclasslevelmetrics.lisofclasses.get(classname1);
            }

        }
        if(!(metriclist1.get("publicmethods")==null)){
        allmethodmetrics.addAll(metriclist1.get("publicmethods"));}
        if(!(metriclist1.get("protectedmethods")==null)){
        allmethodmetrics.addAll(metriclist1.get("protectedmethods"));}
        if(!(metriclist1.get("privatemethods")==null))
        {
        allmethodmetrics.addAll(metriclist1.get("privatemethods"));}
        //----------------------------------------------------------------
        // number of overrideded methods
        // az ebteda ta entehay in for baray mohasebey override ast


        for (int i = 0; i < inheritancelist.size(); i++) {

            Symbol parentname = inheritancelist.get(i);
            for (Symbol value1 : Staticlistclasslevelmetrics.lisofclasses.keySet()) {
                Symbol myclass = value1;
                if (myclass.name.equals(parentname.name) && (myclass.packagename.equals(parentname.packagename))) {
                    metriclist2 = Staticlistclasslevelmetrics.lisofclasses.get(myclass);
                    break;
                }
            }


            boolean oveeride=true;
            int count1=0;
            if(!metriclist2.isEmpty()) {
                Iterator<String> it = metriclist2.get("publicmethods").iterator();


                while (it.hasNext()) {
                    try {
                        String s = it.next();
                        for (int m = 0; m < allmethodmetrics.size(); m++) {

                            if (s.equals(allmethodmetrics.get(m))) {
                                if (overridedmethods.isEmpty()) {
                                    overridedmethods.add(s);
                                    overridedmethod++;

                                }
                                //m1=true;
                                for (int j = 0; j < overridedmethods.size(); j++) {
                                    if (s.equals(overridedmethods.get(j))) {
                                        count1 = 1;
                                        break;


                                    }
                                }
                                if (count1 == 0)
                                    oveeride = false;
                                if (!oveeride) {
                                    overridedmethod++;
                                    overridedmethods.add(s);

                                }
                                oveeride = true;
                                count1 = 0;

                                break;
                            }
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }



            if(!metriclist2.isEmpty()) {

                Iterator<String> it7 = metriclist2.get("protectedmethods").iterator();


                while (it7.hasNext()) {
                    try {
                        String s = it7.next();
                        for (int m = 0; m < allmethodmetrics.size(); m++) {

                            if (s.equals(allmethodmetrics.get(m))) {
                                if (overridedmethods.isEmpty()) {
                                    overridedmethods.add(s);
                                    overridedmethod++;
                                }
                                //m1=true;
                                for (int j = 0; j < overridedmethods.size(); j++) {
                                    if (s.equals(overridedmethods.get(j))) {
                                        count1 = 1;
                                        break;


                                    }
                                }
                                if (count1 == 0)
                                    oveeride = false;
                                if (!oveeride) {
                                    overridedmethod++;
                                    overridedmethods.add(s);

                                }
                                oveeride = true;

                                count1 = 0;

                                break;
                            }
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }











        //------------------------------------------------------------------------
        //az ebteda ta entehay in halghey for baray mohasebey method hay ers borde shode az class digar ast
        for (int i = 0; i < inheritancelist.size(); i++) {
            for (Symbol value : Staticlistclasslevelmetrics.lisofclasses.keySet()) {
                Symbol classname1 = value;

                if (classname1.name.equals(classname) && (classname1.packagename.equals(packagename))) {
                    metriclist1 = Staticlistclasslevelmetrics.lisofclasses.get(classname1);
                }

            }
            if(!(metriclist1.get("publicmethods")==null)){
                allmethodmetrics.addAll(metriclist1.get("publicmethods"));}
            if(!(metriclist1.get("protectedmethods")==null)){
                allmethodmetrics.addAll(metriclist1.get("protectedmethods"));}
            if(!(metriclist1.get("privatemethods")==null))
            {
                allmethodmetrics.addAll(metriclist1.get("privatemethods"));}

            Symbol parentname = inheritancelist.get(i);
            for (Symbol value1 : Staticlistclasslevelmetrics.lisofclasses.keySet()) {
                Symbol myclass = value1;
                if (myclass.name.equals(parentname.name) && (myclass.packagename.equals(parentname.packagename))) {
                    metriclist2 = Staticlistclasslevelmetrics.lisofclasses.get(myclass);
                    break;
                }
            }

            //for (String value2 : metriclist2.keySet()) {
            //  String name = value2;
            //   switch (name) {
            //case "publicmethods":
            if(!metriclist2.isEmpty()) {
                Iterator<String> it = metriclist2.get("publicmethods").iterator();


                while (it.hasNext()) {
                    try {
                        String s = it.next();
                        for (int m = 0; m < allmethodmetrics.size(); m++) {

                            if (s.equals(allmethodmetrics.get(m))) {
                                //m1=true;
                                count = 1;

                                break;
                            }


                        }
                        if (count == 0) {
                            m1 = true;
                        }
                        methodname = s;


                        if (m1) {
                            //  System.out.println("tttttttttttttttttttttttt" + methodname);
                            if (metriclist1.get("publicmethods") == null) {
                                metriclist1.put("publicmethods", new ArrayList<String>());
                            }
                            metriclist1.get("publicmethods").add(methodname);
                            // break;
                        }
                        m1 = false;
                        count = 0;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
            // case "protectedmethods":
            if(!metriclist2.isEmpty()) {

                Iterator<String> it7 = metriclist2.get("protectedmethods").iterator();


                while (it7.hasNext()) {
                    try {
                        String s = it7.next();
                        for (int m = 0; m < allmethodmetrics.size(); m++) {
                            if (s.equals(allmethodmetrics.get(m))) {
                                count = 1;

                                break;
                            }
                        }

                        if (count == 0) {
                            m1 = true;
                        }
                        methodname = s;

                        if (m1) {
                            // System.out.println("tttttttttttttttttttttttt" + methodname);
                            if (metriclist1.get("protectedmethods") == null) {
                                metriclist1.put("protectedmethods", new ArrayList<String>());
                            }
                            metriclist1.get("protectedmethods").add(methodname);
                            // break;

                        }
                        m1 = false;
                        count = 0;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }


    String variablenae;


    // calculate attribute inheritance
    for (Symbol value : Staticlistclasslevelmetrics.lisofclasses.keySet()) {
        Symbol classname1 = value;

        if (classname1.name.equals(classname) && (classname1.packagename.equals(packagename))) {
            metriclist1 = Staticlistclasslevelmetrics.lisofclasses.get(classname1);
        }

    }
        if(!(metriclist1.get("publicvariables")==null)){
        allmethodmetrics.addAll(metriclist1.get("publicvariables"));}
        if(!(metriclist1.get("protectedvariables")==null)){
        allmethodmetrics.addAll(metriclist1.get("protectedvariables"));}
        if(!(metriclist1.get("privatevariables")==null))
    {
        allmethodmetrics.addAll(metriclist1.get("privatevariables"));}

    // System.out.println(allmethodmetrics+"uhdgsfugudgfudsghfdfjdshfhskdfksdgfkdhfj");
        for (int i = 0; i < inheritancelist.size(); i++) {
           for (Symbol value : Staticlistclasslevelmetrics.lisofclasses.keySet()) {
            Symbol classname1 = value;

            if (classname1.name.equals(classname) && (classname1.packagename.equals(packagename))) {
                metriclist1 = Staticlistclasslevelmetrics.lisofclasses.get(classname1);
            }

          }
        if(!(metriclist1.get("publicvariables")==null)){
            allmethodmetrics.addAll(metriclist1.get("publicvariables"));}
        if(!(metriclist1.get("protectedvariables")==null)){
            allmethodmetrics.addAll(metriclist1.get("protectedvariables"));}
        if(!(metriclist1.get("privatevariables")==null))
        {
            allmethodmetrics.addAll(metriclist1.get("privatevariables"));}

        Symbol parentname = inheritancelist.get(i);
        for (Symbol value1 : Staticlistclasslevelmetrics.lisofclasses.keySet()) {
            Symbol myclass = value1;
            if (myclass.name.equals(parentname.name) && (myclass.packagename.equals(parentname.packagename))) {
                metriclist2 = Staticlistclasslevelmetrics.lisofclasses.get(myclass);
                break;
            }
        }

        //for (String value2 : metriclist2.keySet()) {
        //  String name = value2;
        //   switch (name) {
        //case "publicmethods":
            if(!metriclist2.isEmpty()) {
                Iterator<String> it8 = metriclist2.get("publicvariables").iterator();

                while (it8.hasNext()) {
                    try {
                        String s = it8.next();
                        for (int m = 0; m < allmethodmetrics.size(); m++) {

                            if (s.equals(allmethodmetrics.get(m))) {
                                //m1=true;
                                count = 1;
                                break;
                            }


                        }
                        if (count == 0) {
                            m1 = true;
                        }
                        methodname = s;


                        if (m1) {
                            //  System.out.println("tttttttttttttttttttttttt" + methodname);
                            if (metriclist1.get("publicvariables") == null) {
                                metriclist1.put("publicvariables", new ArrayList<String>());
                            }
                            metriclist1.get("publicvariables").add(methodname);
                            // break;
                        }
                        m1 = false;
                        count = 0;

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }

        // case "protectedmethods":
            if(!metriclist2.isEmpty()) {

                Iterator<String> it9 = metriclist2.get("protectedvariables").iterator();


                while (it9.hasNext()) {
                    try {
                        String s = it9.next();
                        for (int m = 0; m < allmethodmetrics.size(); m++) {
                            if (s.equals(allmethodmetrics.get(m))) {
                                count = 1;
                                break;
                            }
                        }

                        if (count == 0) {
                            m1 = true;
                        }
                        methodname = s;

                        if (m1) {
                            // System.out.println("tttttttttttttttttttttttt" + methodname);
                            if (metriclist1.get("protectedvariables") == null) {
                                metriclist1.put("protectedvariables", new ArrayList<String>());
                            }
                            metriclist1.get("protectedvariables").add(methodname);
                            // break;

                        }
                        m1 = false;
                        count = 0;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

            }
    }
}

    @Override public void exitNormalClassdeclaration2(javaParser.NormalClassdeclaration2Context ctx) {

        System.out.println("number of overrided methods is:"+overridedmethod);
        System.out.println("list of overrided methods is:"+overridedmethods);
        overridedmethod=0;
        overridedmethods.clear();
    }



}



