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
    String packagename="";
    Map<String, ArrayList<String>> metriclist1 = new LinkedHashMap<String, ArrayList<String>>();
    Map<String, ArrayList<String>> metriclist2 = new LinkedHashMap<String, ArrayList<String>>();


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
        ArrayList<String> allmethodmetrics = new ArrayList<String>();
        String classname = ctx.Identifier().getText();
        boolean m1 = false;
        boolean m2 = false;
        boolean m3 = false;
        boolean v1 = false;
        boolean v2 = false;
        boolean v3 = false;
        String methodname = null;
        for (Symbol value : Staticlistclasslevelmetrics.lisofclasses.keySet()) {
            Symbol classname1 = value;

            if (classname1.name.equals(classname) && (classname1.packagename.equals(packagename))) {
                metriclist1 = Staticlistclasslevelmetrics.lisofclasses.get(classname1);
                allmethodmetrics.addAll(metriclist1.get("publicmethods"));
                allmethodmetrics.addAll(metriclist1.get("protectedmethods"));
                allmethodmetrics.addAll(metriclist1.get("privatemethods"));
                // System.out.println(allmethodmetrics+"uhdgsfugudgfudsghfdfjdshfhskdfksdgfkdhfj");
                for (int i = 0; i < inheritancelist.size(); i++) {
                    Symbol parentname = inheritancelist.get(i);
                    for (Symbol value1 : Staticlistclasslevelmetrics.lisofclasses.keySet()) {
                        Symbol myclass = value1;
                        if (myclass.name.equals(parentname.name) && (myclass.packagename.equals(parentname.packagename))) {
                            metriclist2 = Staticlistclasslevelmetrics.lisofclasses.get(myclass);
                        }
                    }

                  /*  for (String value2 : metriclist2.keySet()) {
                        String name = value2;
                        switch (name) {
                            case "publicmethods":
                                Iterator<String> it = metriclist2.get("publicmethods").iterator();


                                while (it.hasNext()) {
                                    try {
                                        String s = it.next();
                                        Iterator<String> it1 = metriclist1.get("publicmethods").iterator();


                                        while (it1.hasNext()) {
                                            try {
                                                String s2 = it.next();

                                                if (s.equals(s2)) {
                                                    m1 = true;
                                                }
                                                methodname = s2;
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            if (!m1) {
                                                System.out.println("tttttttttttttttttttttttt" + methodname);
                                                metriclist1.get("publicmethods").add(methodname);
                                                m1 = false;
                                            }


                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                Iterator<String> it3 = metriclist2.get("publicmethods").iterator();


                                while (it3.hasNext()) {
                                    try {
                                        String s = it3.next();
                                        Iterator<String> it4 = metriclist1.get("protectedmethods").iterator();


                                        while (it4.hasNext()) {
                                            try {
                                                String s2 = it4.next();

                                                if (s.equals(s2)) {
                                                    m1 = true;
                                                }
                                                methodname = s2;
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            if (!m1) {
                                                System.out.println("tttttttttttttttttttttttt" + methodname);
                                                metriclist1.get("protectedmethods").add(methodname);
                                                m1 = false;
                                            }


                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                Iterator<String> it5 = metriclist2.get("publicmethods").iterator();


                                while (it5.hasNext()) {
                                    try {
                                        String s = it5.next();
                                        Iterator<String> it6 = metriclist1.get("privatemethods").iterator();


                                        while (it6.hasNext()) {
                                            try {
                                                String s2 = it6.next();

                                                if (s.equals(s2)) {
                                                    m1 = true;
                                                }
                                                methodname = s2;
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            if (!m1) {
                                                System.out.println("tttttttttttttttttttttttt" + methodname);
                                                metriclist1.get("protectedmethods").add(methodname);
                                                m1 = false;
                                            }


                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }


                            case "protectedmethods":

                                Iterator<String> it7 = metriclist2.get("protectedmethods").iterator();


                                while (it7.hasNext()) {
                                    try {
                                        String s = it7.next();
                                        Iterator<String> it8 = metriclist1.get("publicmethods").iterator();


                                        while (it8.hasNext()) {
                                            try {
                                                String s2 = it8.next();

                                                if (s.equals(s2)) {
                                                    m1 = true;
                                                }
                                                methodname = s2;
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            if (!m1) {
                                                System.out.println("tttttttttttttttttttttttt" + methodname);
                                                metriclist1.get("publicmethods").add(methodname);
                                                m1 = false;
                                            }


                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                Iterator<String> it9 = metriclist2.get("protectedmethods").iterator();


                                while (it9.hasNext()) {
                                    try {
                                        String s = it9.next();
                                        Iterator<String> it10 = metriclist1.get("protectedmethods").iterator();


                                        while (it10.hasNext()) {
                                            try {
                                                String s2 = it10.next();

                                                if (s.equals(s2)) {
                                                    m1 = true;
                                                }
                                                methodname = s2;
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            if (!m1) {
                                                System.out.println("tttttttttttttttttttttttt" + methodname);
                                                metriclist1.get("protectedmethods").add(methodname);
                                                m1 = false;
                                            }


                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                Iterator<String> it11 = metriclist2.get("protectedmethods").iterator();


                                while (it11.hasNext()) {
                                    try {
                                        String s = it11.next();
                                        Iterator<String> it12 = metriclist1.get("privatemethods").iterator();


                                        while (it12.hasNext()) {
                                            try {
                                                String s2 = it12.next();

                                                if (s.equals(s2)) {
                                                    m1 = true;
                                                }
                                                methodname = s2;
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            if (!m1) {
                                                System.out.println("tttttttttttttttttttttttt" + methodname);
                                                metriclist1.get("protectedmethods").add(methodname);
                                                m1 = false;
                                            }


                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                        }
                    }

                }


            }


        }*/

                }
            }
        }
    }


    @Override
    public void enterNormalClassdeclaration2(javaParser.NormalClassdeclaration2Context ctx) {
        ArrayList<String> allmethodmetrics = new ArrayList<String>();
        String classname = ctx.Identifier().getText();
        boolean m1 = false;
        boolean inherite=true;

        String methodname = null;
        for (Symbol value : Staticlistclasslevelmetrics.lisofclasses.keySet()) {
            Symbol classname1 = value;

            if (classname1.name.equals(classname) && (classname1.packagename.equals(packagename))) {
                metriclist1 = Staticlistclasslevelmetrics.lisofclasses.get(classname1);
            }

        }       allmethodmetrics.addAll(metriclist1.get("publicmethods"));
                allmethodmetrics.addAll(metriclist1.get("protectedmethods"));
                allmethodmetrics.addAll(metriclist1.get("privatemethods"));

                // System.out.println(allmethodmetrics+"uhdgsfugudgfudsghfdfjdshfhskdfksdgfkdhfj");
                for (int i = 0; i < inheritancelist.size(); i++) {
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
                                Iterator<String> it = metriclist2.get("publicmethods").iterator();


                                while (it.hasNext()) {
                                    try {
                                        String s = it.next();
                                        for(int m=0;m<allmethodmetrics.size();m++) {
                                            if (s.equals(allmethodmetrics.get(m))) {
                                                m1 = true;
                                                inherite=false;
                                                break;

                                            }
                                            methodname=s;
                                            if (!m1&&inherite) {
                                                System.out.println("tttttttttttttttttttttttt" + methodname);
                                                metriclist1.get("publicmethods").add(methodname);
                                                    break;
                                            }
                                        }


                                            m1 = false;
                                        inherite=true;


                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }



                           // case "protectedmethods":

                                Iterator<String> it7 = metriclist2.get("protectedmethods").iterator();


                                while (it7.hasNext()) {
                                    try {
                                        String s = it7.next();
                                        for (int m = 0; m < allmethodmetrics.size(); m++) {
                                            if (s.equals(allmethodmetrics.get(m))) {
                                                m1 = true;
                                                inherite=false;
                                                break;
                                            }
                                            methodname = s;
                                            if (!m1&&inherite) {
                                                System.out.println("tttttttttttttttttttttttt" + methodname);
                                                metriclist1.get("protectedmethods").add(methodname);
                                                break;

                                            }

                                        }

                                        m1 = false;
                                        inherite=true;


                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }




                                m1 = false;
                                inherite=true;

                               }
                        }
                    }


