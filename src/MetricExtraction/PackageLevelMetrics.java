package MetricExtraction;
import ASTGenerator.*;
import Symbols.Symbol;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by saeideh on 1/9/17.
 */
public class PackageLevelMetrics {
    public static Map<String,Map<String,ArrayList<String>>>returnvalue=new LinkedHashMap<String,Map<String, ArrayList<String>>>();
        protected static int numberofpackages;



        int numberoffinalclasses;
    ArrayList<String> listofpackages=new ArrayList<String>();
    ArrayList<String> listofAbstractclasses=new ArrayList<String>();
    ArrayList<String> listofStaticClasses=new ArrayList<String>();
    ArrayList<String> listofFinalClasses=new ArrayList<String>();
    ArrayList<Symbol> listofInterfaces=new ArrayList<Symbol>();
    ArrayList<Symbol> listofClasses=new ArrayList<Symbol>();
    ArrayList<String> listofpublicclass=new ArrayList<String>();
    ArrayList<String> listofprotectedclass=new ArrayList<String>();
    ArrayList<String> listofprivateclass=new ArrayList<String>();
    ArrayList<String> listofclasses1=new ArrayList<>();
    ArrayList<String> listofinterfaces1=new ArrayList<>();


    public void ListofPackages(){


        Iterator<Symbol> it = StaticList.staticlist.values().iterator();
        //Symbol s1=it.next();
        //listofpackages.add(s1.packagename);
        boolean b=true;
        while (it.hasNext()) {
            try {
                Symbol s=it.next();

                for(int i=0;i<listofpackages.size();i++) {
                    if ((s.packagename.equals(listofpackages.get(i)))) {
                        b=false;
                        break;

                    }



                }
                if(b){
                    numberofpackages=numberofpackages+1;
                    listofpackages.add(s.packagename);
                }

                b=true;



            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("number of package of this project is:"+numberofpackages+ "\n\nlist of packages of this project is:"+listofpackages);


    }
   public static int numberofclasses=0;
   public static int numberofinterfaces=0;
   public static int numberofabstractclasses=0;
    public void ListofClasses(){


        Iterator<Symbol> it = StaticList.staticlist.values().iterator();


        while (it.hasNext()) {
            try {
                Symbol s=it.next();
                 if(s.type.equals(Symbol.Type.tCLASS)){
                     numberofclasses=numberofclasses+1;

                 }
                 else if(s.type.equals(Symbol.Type.tINTERFACE)) {
                     numberofinterfaces=numberofinterfaces+1;
                 }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        System.out.println("number of class of this project is:"+numberofclasses+"\n\nnumber of interface of this project if:"+numberofinterfaces);
        Iterator<Symbol> it0 = StaticList.staticlist.values().iterator();


        while (it0.hasNext()) {
            try {
                Symbol s=it0.next();
                for(int i=0;i<s.accessmodifier.size();i++){
                    if(s.accessmodifier.get(i).equals(Symbol.AccessModifier.TABSTRACT)){
                        numberofabstractclasses++;
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        System.out.println("number of abstract class of this project is:"+numberofabstractclasses);

    }

    public void Packagemetrics(){
        for(int i0=0;i0<listofpackages.size();i0++){
            String packname=listofpackages.get(i0);
            returnvalue.put(packname,new LinkedHashMap<>());

        }

        for(int i=0;i<listofpackages.size();i++)
        {
            String packname=listofpackages.get(i);
       //     returnvalue.put(packname,new LinkedHashMap<>());


            Iterator<Symbol> it = StaticList.staticlist.values().iterator();


            while (it.hasNext()) {
                try {
                    Symbol sym=it.next();
                    if(sym.packagename.equals(packname) ){
                        //for(int j=0;j<sym.accessmodifier.size();j++){
                            Symbol.Type access=sym.type;
                            if(access.equals(Symbol.Type.tCLASS))
                            {
                               listofClasses.add(sym);
                                listofclasses1.add(sym.name);
                            }
                            else if(access.equals(Symbol.Type.tINTERFACE))
                            {
                                listofInterfaces.add(sym);
                                listofinterfaces1.add(sym.name);
                            }
                        //}

                    }




                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            System.out.println("\npackage name is:"+packname+"\nnumber of classes:"+listofClasses.size()+"\nnumber of interfacec:"+listofInterfaces.size()+"\nlist of classes:"+listofClasses+"\nlist of interfaces:"+listofInterfaces);
            int Abstractclasscount=0;
            int Publicclasscount=0;
            int Protectedclasscount=0;
            int privateclasscount=0;
            for(int j=0;j<listofClasses.size();j++) {


                for(int k=0;k<listofClasses.get(j).accessmodifier.size();k++){
                    if(listofClasses.get(j).accessmodifier.get(k).equals(Symbol.AccessModifier.TABSTRACT)){
                        Abstractclasscount=Abstractclasscount+1;
                        listofAbstractclasses.add(listofClasses.get(j).name);
                    }
                    else if(listofClasses.get(j).accessmodifier.get(k).equals(Symbol.AccessModifier.tpublic))
                    {
                        Publicclasscount=Publicclasscount+1;
                        listofpublicclass.add(listofClasses.get(j).name);

                    }
                    else if(listofClasses.get(j).accessmodifier.get(k).equals(Symbol.AccessModifier.tprivate)){
                        privateclasscount=privateclasscount+1;
                        listofprivateclass.add(listofClasses.get(j).name);
                    }
                    else if(listofClasses.get(j).accessmodifier.get(k).equals(Symbol.AccessModifier.tprotected)){
                        Protectedclasscount=Protectedclasscount+1;
                        listofprotectedclass.add(listofClasses.get(j).name);

                    }
                    else if(listofClasses.get(j).accessmodifier.get(k).equals(Symbol.AccessModifier.TSTATIC)){
                        listofStaticClasses.add(listofClasses.get(j).name);
                    }
                    else if(listofClasses.get(j).accessmodifier.get(k).equals(Symbol.AccessModifier.TFINAL)){
                        listofFinalClasses.add(listofClasses.get(j).name);
                    }

                }


            }
            System.out.println("\nnumber of Abstract class:"+Abstractclasscount+"\nlist of Abstrcact classes"+listofAbstractclasses+"\nnumber of public classes:"+Publicclasscount+"\nnumber of private classes:"+privateclasscount+"\nnumber of protected classes:"+Protectedclasscount);
            returnvalue.get(packname).put("list of class",new ArrayList<>());
            returnvalue.get(packname).get("list of class").addAll(listofclasses1);
            returnvalue.get(packname).put("list of interface",new ArrayList<>());
            returnvalue.get(packname).get("list of interface").addAll(listofinterfaces1);
            returnvalue.get(packname).put("list of public class",new ArrayList<>());
            returnvalue.get(packname).get("list of public class").addAll(listofpublicclass);
            returnvalue.get(packname).put("list of protected class",new ArrayList<>());
            returnvalue.get(packname).get("list of protected class").addAll(listofprotectedclass);
            returnvalue.get(packname).put("list of private class",new ArrayList<>());
            returnvalue.get(packname).get("list of private class").addAll(listofprivateclass);
            returnvalue.get(packname).put("list of abstract class",new ArrayList<>());
            returnvalue.get(packname).get("list of abstract class").addAll(listofAbstractclasses);
            returnvalue.get(packname).put("list of static class",new ArrayList<>());
            returnvalue.get(packname).get("list of static class").addAll(listofStaticClasses);
            returnvalue.get(packname).put("list of final class",new ArrayList<>());
            returnvalue.get(packname).get("list of final class").addAll(listofFinalClasses);


            listofAbstractclasses.clear();
            listofClasses.clear();
            listofInterfaces.clear();
            listofclasses1.clear();
            listofinterfaces1.clear();
            listofprivateclass.clear();
            listofFinalClasses.clear();
            listofpublicclass.clear();
            listofprotectedclass.clear();
            listofStaticClasses.clear();
        }


    }



}
