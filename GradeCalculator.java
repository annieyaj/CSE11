import java.util.Scanner;

public class GradeCalculator 
{
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);

        //store the divider and initialize sum
        int divisor = scan.nextInt();
        int sum = 0;
        int count = 0;
        boolean valid = true;
        
        //sum up all PA scores
        while (count < divisor)
        {
            int pa = scan.nextInt();
            if (pa < 0 || pa > 100)
            {
                valid = false;
            }
            sum += pa;
            count++;
        }

        //midterm and final
        int midterm = scan.nextInt();
        int finals = scan.nextInt();
        if (midterm < 0 || midterm > 100 || finals < 0 || finals > 100)
        {
            valid = false;
        }

        if (valid == false || divisor == 0)
        {
            System.out.println("invalid input");
            return;
        }
        else
        {
            //calculate pa average
            double paAvg = sum * 1.0 / divisor;
            //calculate final average
            double avg = paAvg * 0.5 + midterm * 0.125 + finals * 0.375;
            System.out.println(avg);
            //final letter grade
            if (avg >= 0 && avg < 60)
            {
                System.out.println("F");
            }
            else if (avg >= 60 && avg < 70) 
            {
                System.out.println("D");
            }
            else if (avg >= 70 && avg < 80)
            {
                System.out.println("C");
            }
            else if (avg >= 80 && avg < 90)
            {
                System.out.println("B");
            }
            else if (avg >= 90 && avg <= 100)
            {
                System.out.println("A");
            }
        }
        
    }
    
}
