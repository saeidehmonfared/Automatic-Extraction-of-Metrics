package DirectoryWalker;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
//import util.FileFind;
import java.lang.String.*;

public class DirectoryWalker {
    // private String[] extensionFilters;
    private LinkedList<String> cachedFiles;

    public DirectoryWalker(String rootPath) {
        this.cachedFiles = new LinkedList();
        this.walk(rootPath);
    }

    //public DirectoryWalker(String rootPath) {
    //this.extensionFilters = new String[extFiltersList.size()];

    //for(int i = 0; i < extFiltersList.size(); ++i) {
    //  this.extensionFilters[i] = (String)extFiltersList.get(i);
    //}

    // this.cachedFiles = new LinkedList();
    // this.walk(rootPath);
    // }

    /*private boolean checkFilter(String fileName) {
       for(int i = 0; i < this.extensionFilters.length; ++i) {
            if(fileName.toLowerCase().endsWith(this.extensionFilters[i].toLowerCase())) {
               return true;
           }
        }

        return false;
    }*/

    private void walk(String path) {
        File dir = new File(path);
        String[] files = dir.list();

        for (int i = 0; i < files.length; ++i) {
            String currentFileId = path + "/" + files[i];
            File f = new File(currentFileId);
            if (f.isDirectory()) {
                this.walk(currentFileId);
                }
                else{
                 //if(this.checkFilter(files[i])) {
                  String fullPath = f.getAbsolutePath();
                  this.cachedFiles.add(fullPath);
              //  }
            }

        }
    }

    public LinkedList<String> getFiles() {
        return this.cachedFiles;
    }
}

       /* public LinkedList<String> getFiles (String name){
            if (!this.cachedFiles.isEmpty()) {
                Iterator i = this.cachedFiles.iterator();

                do {
                    String filePath = i.toString();
                    String fileName = FileFind.findFileName(filePath);
                    if (fileName.compareToIgnoreCase(name) != 0) {
                        this.cachedFiles.remove(i);
                    }

                    i.next();
                } while (i.hasNext());
            }

            return this.cachedFiles;
        }
    }*/

