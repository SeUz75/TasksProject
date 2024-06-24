import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        // I will create a list in which I will be adding and removing tasks
        // To-Do List !

        List<Task> tasks = new ArrayList<>();

        // LOAD TASKS FROM FILE
        loadTasksFromFile("data.txt", tasks);

        String answer = "yes";
        do{
            System.out.println("The tasks are :");
            printTasks(tasks);

            System.out.println("What kind of action do you wanna do ?");
            System.out.println("1. Add Task");
            System.out.println("2. Delete Task");
            System.out.println("3. View description of Task");
            System.out.println("4. Quit");
            answer = scan.nextLine();

            switch(answer){
                case "1":
                     addTask(tasks);
                     break;
                case "2":
                removeTask(tasks);
                    break;
                case "3":
                 inspectTask(tasks);
                 break;
                case "4":
                break;

                default:
                System.out.println("Invalid option. Please choose 1, 2, or 3.");
                 break;

            }
            System.out.println("Wanna continue the program ? ");
            answer = scan.nextLine();

        }while(answer.equals("yes"));

        tasks = sorttingList(tasks); // sorting list by month date 
         //SORTED LIST BY MONTH
         System.out.println("SORTED LIST BY MONTH DATE");
         printTasks(tasks);

        scan.close();
    }



    // LOADING TASKS FROM FILE METHOD
    public static List<Task> loadTasksFromFile(String filename, List<Task> listTasks){
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                if(values.length == 4){
                    String name = values[0];
                    String description = values[1];
                    int month = Integer.parseInt(values[2]);
                    boolean done =  Boolean.parseBoolean(values[3]);
                    Task task = new Task(name, description, month, done);
                    listTasks.add(task);
                }
                
            }

        } 
        catch (IOException e){
            System.err.println("Error reading file : " + filename);
        }

        return listTasks;
    }


    // ADDING TASKS IN THE LIST
    public static List<Task> addTask(List<Task> tasks){

        System.out.println("Please enter name of task");
        String name = scan.nextLine();

        System.out.println("Please enter name of description of the task");
        String description = scan.nextLine();

        System.out.println("Please enter name of month of the task");
        int month = scan.nextInt();

        scan.nextLine();
        boolean done = false;

        Task tasktoAdd = new Task(name,description,month,done);
        tasks.add(tasktoAdd);

        return tasks;
    }

    // REMOVING TASKS FROM LIST
    public static List<Task> removeTask(List<Task> tasks){
        printTasks(tasks);
        String answer = "no";
        int i;
        do{
            printTasks(tasks);
            System.out.println("Which task do you want to remove ? Type the number ");
             i = scan.nextInt();
            scan.nextLine();
        if(i>=0 && i< tasks.size()){
            if(tasks.get(i).getdone() == true){
                System.out.println("Tasks is still not done are you sure ? yes or no  ?");
                String choice = scan.nextLine();
                if(choice.equals("yes")){
                    tasks.remove(i);
                    System.out.println("Task REMOVED");
                }
                else{
                    System.out.println("Task NOT REMOVED ");
                }
            }
            else{
                tasks.remove(i);
                    System.out.println("Task REMOVED");
            }
        }
        else{
            System.out.println("Invalid task number ");
        }
            System.out.println("Wanna quit deleting tasks [y/n]");
             answer = scan.nextLine();

        }while(answer.equals("n"));
        return tasks;
    }


    // INSPECTING TASKS 
    public static void inspectTask(List<Task> tasks){
        System.out.println("Which task do you wanna inspect ? type the number");
        int i = scan.nextInt();
        scan.nextLine();
        System.out.println(tasks.get(i).getdescription());
    }




    

    public static List<Task> sorttingList(List<Task> listTask){
        int n = listTask.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (listTask.get(j).getmonthdate() > listTask.get(j + 1).getmonthdate() ) {
                    // Swap tasks[j] and tasks[j+1]
                    Task temp = listTask.get(j);
                    listTask.set(j, listTask.get(j + 1));
                    listTask.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        return listTask;    
    }






    // PRINTING MY LIST 
    public static void printTasks(List<Task> tasks){
        int i =0;
        for (Task task : tasks) {
            System.out.println(i + " Task:");
            System.out.println("Task name : " + task.getName());
            System.out.println("Task description : " + task.getdescription());
            System.out.println("Task monthdate : " + task.getmonthdate());
            System.out.println("Is the job done : " + task.getdone());
            i++;
        }
    }

    // CLASS TASK WITH 3 ATTRIBUTES
    public static class  Task{
        private String name;
        private String description;
        private int monthdate;
        private boolean done;

        // CONSTRUCTOR
        public Task (String name, String description, int monthdate, boolean done){
            this.name = name;
            this.description = description;
            this.monthdate = monthdate;
            this.done = done;
        }

        public void setname(String name ){
            this.name = name;
        }

        public String getName(){
            return name;
        }

        public void setdescription(String description){
            this.description = description;
        }

        public String getdescription(){
            return description;
        }

        public void setmonthdate(int monthdate){
            this.monthdate = monthdate;
        }

        public int getmonthdate(){
            return monthdate;
        }

        public void setdone(boolean done){
            this.done = done;
        }

        public boolean getdone(){
            return done;
        }
    }
}
