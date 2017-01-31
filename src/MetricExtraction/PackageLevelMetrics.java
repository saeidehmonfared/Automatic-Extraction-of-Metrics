package MetricExtraction;
import ASTGenerator.*;
import Symbols.Symbol;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by saeideh on 1/9/17.
 */
public class PackageLevelMetrics {
        protected int numberofpackages;
        int numberofabstractclasses;


        int numberoffinalclasses;
        ArrayList<String> listofpackages=new ArrayList<String>();
    ArrayList<String> listofAbstractclasses=new ArrayList<String>();
    ArrayList<String> listofStaticClasses=new ArrayList<String>();
    ArrayList<String> listofFinalClasses=new ArrayList<String>();
    ArrayList<Symbol> listofInterfaces=new ArrayList<Symbol>();
    ArrayList<Symbol> listofClasses=new ArrayList<Symbol>();


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
    public void ListofClasses(){

        int numberofclasses=0;
        int numberofinterfaces=0;
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

    }

    public void Packagemetrics(){
        for(int i=0;i<listofpackages.size();i++)
        {
            String packname=listofpackages.get(i);


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
                            }
                            else if(access.equals(Symbol.Type.tINTERFACE))
                            {
                                listofInterfaces.add(sym);
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

                    }
                    else if(listofClasses.get(j).accessmodifier.get(k).equals(Symbol.AccessModifier.tprivate)){
                        privateclasscount=privateclasscount+1;
                    }
                    else if(listofClasses.get(j).accessmodifier.get(k).equals(Symbol.AccessModifier.tprotected)){
                        Protectedclasscount=Protectedclasscount+1;

                    }

                }


            }
            System.out.println("\nnumber of Abstract class:"+Abstractclasscount+"\nlist of Abstrcact classes"+listofAbstractclasses+"\nnumber of public classes:"+Publicclasscount+"\nnumber of private classes:"+privateclasscount+"\nnumber of protected classes:"+Protectedclasscount);
            listofAbstractclasses.clear();
            listofClasses.clear();
            listofInterfaces.clear();

        }


    }



}
