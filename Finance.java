import java.util.Scanner;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class Finance{
  static ArrayList<Double> debt = new ArrayList<>();
  static Scanner input = new Scanner(System.in);
   public static void main(String[] args){ 
     
         System.out.println("What's the number of debt that you have? ");
         if(input.hasNextInt()){
         int size = input.nextInt();
         if(size < 0 || size >= 30 || size  == 0){
            System.out.println("Invalid size!");
            return;
         }else {
            checkNegativeDebt(size);
           }
        System.out.println("Show all debt: ");
         
         for(int i = 0; i < size; i++){
            System.out.println("$" + debt.get(i));
         }
        double deposits = 0;
     
        System.out.println("Deposit Money: ");        
        deposits = input.nextDouble();
        if(deposits <= 0){
         System.out.println("Error! Please enter a positive number");
        }else {
        
        System.out.print("Choose what debt to deposit money in from the list: (0 to " + (debt.size() - 1) + "): ");
        
        int index = input.nextInt();
        double selected = 0.0;
        double result = 0.0;
        double remainingBalanceAbs = 0.0;
        double selectedOther = 0.0;
        String yesOrNo;

        // Basic bounds check to avoid IndexOutOfBoundsException
        if (index >= 0 && index < debt.size()) {
             selected = debt.get(index);
            System.out.println("You selected: " + selected);
            result = getMoneyLeft(selected, deposits);
            remainingBalanceAbs = Math.abs(result);

            
            
           if(result == 0.0){//if the applied money pays off the debt completely and there is no leftover money
            
            debt.remove(index);
             System.out.println("Debt of $" + selected + " paid off!");
           
            }else if(result < 0){//this is if there is leftover money, hence if the remainingBalance is negative, the result in this case

            while(remainingBalanceAbs > 0 && !debt.isEmpty()){
            
            System.out.println("Debt of $" + selected + " paid off!");
            debt.remove(index);
            System.out.println("Remaining Funds: $" + Math.abs(BigDecimal.valueOf(remainingBalanceAbs).setScale(2, RoundingMode.HALF_EVEN).doubleValue()));
            if(debt.isEmpty()){
              break;
            }else{
            System.out.println("With remaining balance of " + remainingBalanceAbs + " would you like to pay another debt?");
            input.nextLine();
            yesOrNo = input.nextLine();
            if(yesOrNo.equalsIgnoreCase("yes")){
               System.out.println("Choose what debt to deposit money in from the list: (0 to " + (debt.size() - 1) + "): ");
            index = input.nextInt();
            selected = debt.get(index);
            System.out.println("You selected: $" + selected);

            remainingBalanceAbs = getMoneyLeft(selected, remainingBalanceAbs);
            if(remainingBalanceAbs < 0){
            //debt.remove(index);
            //System.out.println("Remaining Funds: $" + Math.abs(remainingBalanceAbs));
            remainingBalanceAbs = Math.abs(remainingBalanceAbs);
            }else{
               updateList(index, remainingBalanceAbs, selected);
               break;
            }
                        
               
            }else if(yesOrNo.equalsIgnoreCase("no")){
              //debt.remove(index);
              System.out.println("Thank you have a great day!");
              break;
                     
            }else{
               updateList(index, result, selected);
            }
           }
            }
           }else if(result > 0){
            updateList(index, result, selected);
           }
           
         }
        
     
         else {
            System.out.println("Invalid index!");
        }
       if(debt.isEmpty()){
         System.out.println("Congrats! No more debt.");
       }else {
       System.out.println("Remaining debt: ");
       for(Double updatedList : debt){
         System.out.printf("$%.2f%n", updatedList);
       }  
      }       
      }
       } else{
         System.out.println("Error! Invalid Index");
       }
      }
      
      
        public static double getMoneyLeft(double choice, double moneyIn){
         return choice - moneyIn;
         
        }
        
        public static void updateList(int index1, double totalAmount, double amountSelected){
            if(totalAmount == 0.0){
               debt.remove(index1);
               System.out.println("Debt of $" + amountSelected + " paid off!");
            }else{
              
               debt.set(index1, BigDecimal.valueOf(totalAmount).setScale(2, RoundingMode.HALF_EVEN).doubleValue());
               }
           

        }
        
        public static void checkNegativeDebt(int numOfSize){
         for(int i = 0; i < numOfSize; i++){
            System.out.println("Enter debt " + (i + 1) + ": ");
            double addDebt = input.nextDouble();
            if(addDebt <= 0){
               System.out.println("Invalid number. Try again");
               i--;
            }else{
            debt.add(addDebt);
            }
         }

         
        } 
        
              }
     
