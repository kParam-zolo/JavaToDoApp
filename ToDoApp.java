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
            System.out.println("Here is the List of All tasks");

            for(int i = 0;i<allTask.size();i++){
                // System.out.println("TaskId : " + (i + 1));
                // System.out.println("Description : " +allTask.get(i).description);
                // System.out.println("Created on : " + allTask.get(i).date);
                System.out.println(String.format("Taskid : %d , Description: %s ,created on %s",i,allTask.get(i).description,allTask.get(i).date));
                System.out.println();
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

    static void retrieveTasks(){

    }

    static void deleteTask(int id){
        if(invalidId(id)) return;
        id--;
        for(int i = id;i<allTask.size()-1;i++){
            allTask.set(i,allTask.get(i+1));
        }
        allTask.remove(allTask.size()-1);
    }

    static void markTask(int id){
        if(invalidId(id)) return;
        id--;
        Task tmp = allTask.get(id);
        tmp.marked = true;
        allTask.set(id,tmp);
    }

    static void searchTask(String word){
        for(Task tt: allTask){
            boolean flag = true;
            for(int i = 0;i<Math.min((int)word.length(),(int)tt.description.length());i++){
                if(word.charAt(i) != tt.description.charAt(i)){
                    flag = false;
                    break;
                }
            }
            if(flag){
                System.out.println("Found a match:\n" + tt.description);
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("      Welcome to TODO App ");
        System.out.println();

        Boolean completed = false;
        LisAlltask();


        while(!completed){
            
            System.out.println("Here is the List of Options Available");
            System.out.println("Press 'A' To add a new task");
            System.out.println("Press 'B' to list of task");
            System.out.println("Press 'C' to delete a task");
            System.out.println("Press 'D' to mark a task completed");
            System.out.println("Press 'E' to search a task that start with particular word");
            System.out.println("Press 'L' to Exit");
            System.out.println();
            

            Scanner scan = new Scanner(System.in);
            char Options = scan.nextLine().charAt(0);


            switch (Options) {
                case 'A':
                    System.out.print("Enter the Task Description : ");
                    scan = new Scanner(System.in);
                    String d = scan.nextLine();
                    AddTask(d);
                    System.out.println();
                    break;
                    
                case 'B':
                    LisAlltask();
                    break;

                case 'C':
                    System.out.println("Enter the Task Id to delete : ");
                    scan = new Scanner(System.in);
                    int dd = scan.nextInt();
                    deleteTask(dd);
                    break;
                case 'D':
                    System.out.println("Enter the Task Id to Mark : ");
                    scan = new Scanner(System.in);
                    dd = scan.nextInt();
                    markTask(dd);
                    break;
                case 'E':
                    System.out.println("Enter the Word to match : ");
                    scan = new Scanner(System.in);
                    String word = scan.next();
                    searchTask(word);
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
