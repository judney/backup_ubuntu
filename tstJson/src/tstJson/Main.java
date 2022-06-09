package tstJson;

//public class Main {

//}


import java.util.*;  
import com.google.gson.Gson;  //google GSON library

public class Main 
{

    public static void main(String args [])  {
        String examplejson =                          //example json
            "{"
                + "'Name': 'Bill-Gates',"
                + "'RollNumber' : 1,"
                + "'Gender' : 'Male',"
                + "'Books' : [{"
                    + "'Name' : 'ISBN',"
                    + "'ISBN' : 85394023,"
                    + "'Author' : 'Elon-Musk'"
                    
                    + "}]"  + "}";            
                Student s = new Gson().fromJson(examplejson, Student.class);  //conversion using Gson Library.
                s.Print();  //print student detail after conversion
    }
    public class Student 
    {

        private String Name;
        private String RollNumber;
        private String Gender;
        private  List<Book> Books ;

        Student()
        {

            //Default Constructor
        }

          public void Print(){
               System.out.println( "Name = "+Name);
               System.out.println( "RollNumber = "+RollNumber);
               System.out.println("Gender = "+ Gender);
               System.out.println("List Of BOOKS");
               for (int i = 0; i < Books.size(); i++) {
                   System.out.println("Name =" + Books.get(i).Name);
                   System.out.println( "ISBN =" + Books.get(i).ISBN);
                   System.out.println( "Author = " + Books.get(i).Author);
               
            }

          }



        }


        public class Book
        {

            
            private String Name;
            private String ISBN;
            private String Author;
           

        Book()
        {
        }
    }
}