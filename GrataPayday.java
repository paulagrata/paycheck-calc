import java.util.Scanner;
public class GrataPayday {

    /**
     * deterimines gross pay
     * @param hrsWorked - how many hrs worked
     * @param hrlyPayrate - payrate per hour
     * @return total amt of pay for hrs worked and payrate per hour
     */
    
    public static double findGrosspay(double hrsWorked, double hrlyPayrate){
        double totalgrosspay;
        totalgrosspay = (hrsWorked*hrlyPayrate);
        return totalgrosspay;
    }

    /**
     * determines union dues
     * @param usrGrossPay - users total grosspay amt
     * @param uDues - the union dues string input of yes or no
     * @return if yes then multiple 5% to total gross pay, if no - then no dues are due
     *         i also could have addded in a flag to check for if Y or N was not entered
     *          (like the example given for temp converter), but i was lazy
     */

    public static double findUnionDues(double usrGrossPay, String uDues){
        double unionDueWithholdAmt = 0;
        if (uDues.equalsIgnoreCase("Y")){
            unionDueWithholdAmt = (.05*usrGrossPay);
        } else if (uDues.equalsIgnoreCase("N")){
            unionDueWithholdAmt = (0*usrGrossPay);
        }
        return unionDueWithholdAmt;
    }

    /**
     * determines medical witholding amount 
     * @param usrGrossPay - users total grosspay amt
     * @param medWitholdAmt the input user enters for the amt of medical withholding
     * @return converts amt user inputed into a percentage that is able to be multiplied to determine total amt
     */

    public static double findMedWithold(double usrGrossPay, double medWitholdAmt){
        double totalMedWithold;
        double usrGrossPayPercent;
        usrGrossPayPercent = usrGrossPay / 100;
        totalMedWithold = (usrGrossPayPercent*medWitholdAmt);
        return totalMedWithold;
    }   

    /**
     * determines how much tax should be applied for the total amt
     * @param usrGrossPay - users total grosspay amt
     * @param medWitholdAmt - users total med witholding amt
     * @param unWitholdAmt -users total union dues amt
     * @return finds total by subtracting gross pay, medwitholding, and union dues
     *         compares total to 2500, 1500, and 500 to determine how much to tax
     */

    public static double findTaxWithold(double usrGrossPay, double medWitholdAmt, double unWitholdAmt){
        double TaxWithholdAmt = 0;
        double total = 0;
        double x = 2500;
        double y = 1500;
        double z = 500;
        total = (usrGrossPay - medWitholdAmt - unWitholdAmt);
        if (total >= x){
            TaxWithholdAmt = (.25*total);
        } else if (total >= y){
            TaxWithholdAmt = (.15*total);
        } else if (total >= z){
            TaxWithholdAmt = (.10*total); 
        } else{
            TaxWithholdAmt = (.05*total); 
        } return TaxWithholdAmt;
    }
    
    /**
     * determines overall net pay 
     * @param usrGrossPay - users total grosspay amt
     * @param medWitholdAmt - users total med withhold amt
     * @param unWitholdAmt - users total union dues amt
     * @param taxWithholdAmt - users total tax amt
     * @return starting with users total gross pay - med withhold amt, union dues amt, and total tax amt
     *         are subtracted to get the users total net pay
     */

    public static double findNetPay(double usrGrossPay, double medWitholdAmt, double unWitholdAmt, double taxWithholdAmt){
        double totalNetPay;
        totalNetPay = (usrGrossPay - medWitholdAmt - unWitholdAmt - taxWithholdAmt );
        return totalNetPay;
    }

    /**
     * prints the overall paycheck 
     * @param usrGrossPay
     * @param medWitholdAmt
     * @param unWitholdAmt
     * @param taxWithholdAmt
     * @param totalNetPay
     * @param fullName
     * @return the paycheck - i could only print it by setting title to 0? is that how its done?
     */

    public static double payCheck(double usrGrossPay, double medWitholdAmt, double unWitholdAmt, double taxWithholdAmt, double totalNetPay, String fullName){
        double title =0;
        System.out.println();
        System.out.println("-----------PAYCHECK-----------");
        System.out.printf("%-15s%5s%10.2f\n","Grosspay","$", usrGrossPay);
        System.out.printf("%-15s%5s%10.2f\n","Union Dues","$", unWitholdAmt);
        System.out.printf("%-15s%5s%10.2f\n","Med Witholding","$", medWitholdAmt);
        System.out.printf("%-15s%5s%10.2f\n","Taxes","$", taxWithholdAmt);        
        System.out.printf("%-15s%5s%10.2f\n","Net Pay","$", totalNetPay);  
        System.out.println("------------------------------");
        System.out.printf("Prepared for %s.\n\n",fullName);
        System.out.println("Thank you for using this program.\n");
        return title;
    }
    

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String nameEntered;
        String unionDues;
        double hoursWorked;
        double payRate;
        double withholdPercent;
        double grossPay;
        double unionDuesWitheld;
        double medDuesWitheld;
        double taxDuesWitheld; 
        double netPay;
        double paycheckTitle;
        System.out.println("*************************************************");
        System.out.println("                  Payday V1.0                    ");
        System.out.println("*************************************************\n");
        System.out.print("Enter name: ");
        nameEntered=sc.nextLine();
        System.out.print("Enter hours worked: ");
        hoursWorked = sc.nextDouble();
        System.out.print("Enter hourly payrate: ");
        payRate = sc.nextDouble();
        System.out.print("Are you a union member (y or n)? ");
        unionDues = sc.next();
        System.out.print("What percentage do you want to withhold for your medical savings account? ");
        withholdPercent = sc.nextDouble();
        grossPay=findGrosspay(hoursWorked,payRate);
        unionDuesWitheld =  findUnionDues(grossPay,unionDues);
        medDuesWitheld = findMedWithold(grossPay, withholdPercent);
        taxDuesWitheld = findTaxWithold(grossPay, medDuesWitheld, unionDuesWitheld);
        netPay =  findNetPay(grossPay, medDuesWitheld, unionDuesWitheld, taxDuesWitheld);
        paycheckTitle = payCheck(grossPay, medDuesWitheld, unionDuesWitheld, taxDuesWitheld, netPay, nameEntered);

    }
    
}

// i struggle with functions so i tried to add one in every spot i could to practice