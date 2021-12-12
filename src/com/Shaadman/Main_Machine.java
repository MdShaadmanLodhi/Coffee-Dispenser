package com.Shaadman;
import java.util.*;
public class Main_Machine {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        float MoneyInserted;
        boolean isSuccessful=false;
        boolean haveResource;
        int x=0;
        String userInput="";
        CoffeeMachineResource.milk=CoffeeMachineResource.milk-10;
        System.out.printf(" \n%d %d %d dsfd\n",CoffeeMachineResource.coffeePowder,CoffeeMachineResource.water,CoffeeMachineResource.milk);


        while(x==0){

            System.out.println("What would you like? \n1:Cappuccino\t$2.30\n2:espresso\t    $0.95\n3:latte\t        $1.85");
            userInput= in.nextLine();



            switch(userInput){
                case "1" :{
                    haveResource =checkResource(80,115,10);
                    if(haveResource) {
                        MoneyInserted= askForMoneyAndReturnAmount();
                        isSuccessful = validateTransaction(MoneyInserted, 2.30);
                    }
                   if(isSuccessful && haveResource){
                        makeCoffee(80,115,10,"Cappuccino");
                        CoffeeMachineResource.profit+=+2.30f;
                    }
                    break;
                }
                case "3" :{
                    haveResource =checkResource(60,125,15);
                    if(haveResource) {
                        MoneyInserted= askForMoneyAndReturnAmount();
                        isSuccessful = validateTransaction(MoneyInserted, 1.85);
                    }
                    if(isSuccessful && haveResource){
                        makeCoffee(60,125,15,"Latte");
                        CoffeeMachineResource.profit+=+1.85f;
                    }break;
                }
                case "2" :{
                    haveResource =checkResource(40,85,5);
                    if(haveResource){
                        MoneyInserted= askForMoneyAndReturnAmount();
                        isSuccessful =validateTransaction(MoneyInserted,0.95);
                    }
                    if(isSuccessful && haveResource){
                        makeCoffee(40,85,5,"Espresso");
                        CoffeeMachineResource.profit+=+0.95f;
                    }break;
                }
                case "report" :{

                    System.out.printf("Report\nWater: %d \tmilk: %d \tcoffee:%d \t money: %1.2f \n",
                            CoffeeMachineResource.water,CoffeeMachineResource.milk,CoffeeMachineResource.coffeePowder,CoffeeMachineResource.profit);

                }break;
                case "fill":{
                    System.out.println("Welcome to the Storage Compartment\nHere's our Storage status");
                    System.out.printf("\nWater: %d \tmilk: %d \tcoffee:%d \n",
                            CoffeeMachineResource.water,CoffeeMachineResource.milk,CoffeeMachineResource.coffeePowder);
                    System.out.println("What will you like to add\n'1':Water\t'2':Milk\t'3':Coffee Powder");
                   int fillUserInput=in.nextInt();
                   int addResource=0;
                    for(;;){
                        if(fillUserInput==2){
                            System.out.println("How Much Milk Do You Want To Add?\t\t[Enter in ml]");
                            CoffeeMachineResource.milk+=+in.nextInt();
                        }
                        if(fillUserInput==1){
                            System.out.println("How Much Water Do You Want To Add?\t\t[Enter in ml]");
                            CoffeeMachineResource.water+=+in.nextInt();
                        }
                        if(fillUserInput==3){
                            System.out.println("How Much Coffee Powder Do You Want To Add?\t\t[Enter in grams]");
                            CoffeeMachineResource.coffeePowder+=+in.nextInt();
                        }
                        System.out.println("Do You Still Want To Some Resource?\t['y' or 'n']");
                        if(in.nextLine()=="n")break;
                    }
                }

                case "off" :{
                    x=1;
                    break;

                }
                default  :{
                    System.out.println("Sorry Wrong Input\t Please Try Again ");break;
                }
            }


        }

    }

    /* private static void example() {
         CoffeeMachineResource.coffeePowder=CoffeeMachineResource.coffeePowder-10;
         System.out.printf(" %d %d %d ",CoffeeMachineResource.coffeePowder,CoffeeMachineResource.water,CoffeeMachineResource.milk);
     }
 */
    private static boolean checkResource(int water,int milk, int coffeePowder) {
       // System.out.println(" under check resource\nwater "+ water+ " CoffeeMachineResource.water "+CoffeeMachineResource.water );
        if(water<CoffeeMachineResource.water && milk<CoffeeMachineResource.milk && coffeePowder<CoffeeMachineResource.coffeePowder){
            return true;
        }
        System.out.println("Not Enough Resource!\nType 'fill' to add resources");
        return  false;
    }

    private static void makeCoffee(int water,int milk, int coffeePowder,String v) {
        CoffeeMachineResource.water=CoffeeMachineResource.water-water;
        CoffeeMachineResource.milk=CoffeeMachineResource.milk-milk;
        CoffeeMachineResource.coffeePowder=CoffeeMachineResource.coffeePowder-coffeePowder;
        System.out.println("Here's Your "+v +"! \tHave A Nice Day\n" );

    }

    private static boolean validateTransaction(float moneyInserted, double v) {
        if(moneyInserted==v ){
            return true;
        }
        else if (moneyInserted<v){
            System.out.println("Not enough Money");
            return false;
        }
        float returnBalance = (float) (moneyInserted-v);
        System.out.printf("And Here's your change $%.2f \n",returnBalance);
        return true;
    }
    private static float countCoin(int quarter, int dime, int nickle, int penny) {
        float moneyInserted = (float) (quarter*0.25+dime*0.1+nickle*0.05+penny*0.01);
        return moneyInserted;
    }

    private static float askForMoneyAndReturnAmount() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please Insert the coins \t " +
                "example: quarter[$0.25] dime[$0.10] nickle[$0.05] penny[$0.01] \n In this format with single space \t 2 0 4 7");
        String userCoinInput = in.nextLine();
        String StrSplitArray[] = userCoinInput.split(" ");
        int coinArray[] = new  int[StrSplitArray.length];
        int quarter,dime,nickle,penny;
        try {
             quarter = Integer.parseInt(StrSplitArray[0]);
             dime = Integer.parseInt(StrSplitArray[1]);
             nickle = Integer.parseInt(StrSplitArray[2]);
             penny = Integer.parseInt(StrSplitArray[3]);
            //float moneyInserted = (float) (quarter*0.25+dime*0.1+nickle*0.05+penny*0.01);
            return countCoin(quarter,dime,nickle,penny);
        }catch (NumberFormatException e ){
            System.out.println("Please Enter Numbers As Instructed\nOr It Will Show this error: "+e);
        }
        catch (ArrayIndexOutOfBoundsException w ){
            System.out.println("Please Enter Numbers As Instructed\nOr It Will Show this error: "+w);
        }
        return 0;

    }

}
    class CoffeeMachineResource {

        //CoffeeMachineResource coffeeResource = new CoffeeMachineResource();
        static int water=450;
        static int milk = 600;
        static int coffeePowder=100;
        static float profit=0;

    }


