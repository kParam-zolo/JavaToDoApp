import java.util.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class ToDoApp {
    static ArrayList<Task> allTask = new ArrayList<Task>();
    
    public static class Task{
        boolean marked;
        LocalDate date;
        String description;
        Task(String data){
            marked = false;
            date = LocalDate.now();
            description = data;
        }
    }

    static void LisAlltask(){
        if(allTask.size() > 0){
            System.out.println('\t' + "Here is the List of All tasks");
            System.out.println();
            for(int i = 0;i<allTask.size();i++){
                System.out.println(String.format("Taskid : %d , Description: %s ,created on %s , marked status = %s",i+1,allTask.get(i).description,allTask.get(i).date , (allTask.get(i).marked ? "Marked" : "UnMarked")));
                System.out.println();
            }
        }else{
            System.out.println("No Task to show.");
            System.out.println();
        }
    }

    static void AddTask(String task){
        allTask.add(new Task(task));
    }

    static Boolean invalidId(int id){
        return (id > allTask.size() || id < 1 );
    }

    static Boolean deleteTask(int id){
        if(invalidId(id)) return false;
        id--;
        for(int i = id;i<allTask.size()-1;i++){
            allTask.set(i,allTask.get(i+1));
        }
        allTask.remove(allTask.size()-1);
        return true;
    }

    static Boolean markTask(int id){
        if(invalidId(id)) return false;
        id--;
        Task tmp = allTask.get(id);
        tmp.marked = true;
        allTask.set(id,tmp);
        return true;
    }

    static void searchTask(String word){
        boolean isFound = false;
        for(Task tt: allTask){
            boolean flag = true;
            for(int i = 0;i< (int)word.length();i++){
                if(i >= tt.description.length() || word.charAt(i) != tt.description.charAt(i)){
                    flag = false;
                    break;
                }
            }
            if(flag){
                isFound = true;
                System.out.println("Found a match:\n" + tt.description);
            }
        }
        if(!isFound){
            System.out.println("No matches found. Try Again");
            System.out.println();
        }
    }

    public static void main(String[] args) {

        System.out.println("      Welcome to TODO App ");
        System.out.println();

        Boolean completed = false;
        // LisAlltask();


        while(!completed){
            
            System.out.println("Here is the List of Options Available");
            System.out.println();
            System.out.print("Press 'A' To add a new task   ||| " + '\t');
            System.out.print("Press 'B' to list of task   ||| " + '\t');
            System.out.println("Press 'C' to delete a task");
            System.out.print("Press 'D' to mark a task completed   ||| " + '\t');
            System.out.println("Press 'E' to search a task that start with particular word   ||| " + '\t');
            System.out.println("Press 'L' to Exit");
            System.out.println();
            

            char Options = new Scanner(System.in).nextLine().charAt(0);
            Scanner scan;

            switch (Options) {
                case 'A':
                    System.out.print("Enter the Task Description : ");
                    scan = new Scanner(System.in);
                    String d = scan.nextLine();
                    AddTask(d);
                    System.out.println("Task Added Successfully");
                    System.out.println();
                    break;
                    
                case 'B':
                    LisAlltask();
                    break;

                case 'C':
                    System.out.print("Enter the Task Id to delete : ");
                    scan = new Scanner(System.in);
                    int dd = scan.nextInt();
                    if(deleteTask(dd)) System.out.println("Deleted Successfully");
                    else System.out.println("invalid Id given.. try again");
                    break;
                case 'D':
                    System.out.print("Enter the Task Id to Mark : ");
                    scan = new Scanner(System.in);
                    dd = scan.nextInt();
                    if(markTask(dd)){
                        System.out.println("Successfully Marked task " + dd);
                    }else System.out.println("Invalid id given.. try again");
                    break;

                case 'E':
                    System.out.print("Enter the Word to match : ");
                    scan = new Scanner(System.in);
                    String word = scan.next();
                    System.out.println();
                    searchTask(word);
                    System.out.println("Searching....");
                    System.out.println();
                    break;                 
                case 'L':
                    completed = true;
                    break;
                    
                default:
                    System.out.println("Invalid Option : ");
                    break;
            }
        }

    }


}
