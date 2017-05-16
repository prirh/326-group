import java.util.*;

public class Test{

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int start = s.nextInt();
        int max= s.nextInt();
        int value =start;
        for(int i=start+1;i<=max;i++){
            if((max/2)==i){
                System.out.println(i);
                value-=(i-1);
                value+=(i*(i-1));
            }else{
                value+=i;
            }
        }
        System.out.println(value);

    }

}
