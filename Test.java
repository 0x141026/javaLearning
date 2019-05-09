//package package00;
import java.util.*;
public class Test{
        //冒泡插入函数
        static void BubbleSort(int[] shuzi, int num){
            int[] shuzu = new int[num];
            shuzu = shuzi;
			boolean didSwap = false;
            for(int i = 0; i < num-1;i++){
                for(int j = 0; j < num-i-1; j++){
                    if(shuzu[j]>shuzu[j+1]){
                        int temp = shuzu[j];
                        shuzu[j] = shuzu[j+1];
                        shuzu[j+1] = temp;
						didSwap = true;
                    }
                }
			    if (didSwap == false){
					return;
				}
            }
            System.out.print("排序后：");
            for (int k = 0;k < num; k++){
                System.out.print(shuzu[k]+" ");
            }
 
        }
        //直接插入排序函数
        public static void DirectInsertSort(int[] shuzi, int num){
            int[] shuzu = new int[num];
            shuzu = shuzi;
            int temp = 0;
            for(int i = 1;i < num;i++){
                temp = shuzu[i];
                int j=0;
                for(j = i-1; j>=0; j--){
                    if(shuzu[j]>temp){
                        shuzu[j+1]=shuzu[j];
                    }else{
                        break;
                    }
                }
                shuzu[j+1] = temp;
            }
            System.out.print("排序后：");
            for (int k = 0;k < num; k++){
                System.out.print(shuzu[k]+" ");
            }
        }
        public static void main(String[] args){
            System.out.print("please input the sum of numbers:");
            Scanner input = new Scanner(System.in);
            int sum = input.nextInt();
            int[] numbles = new int[sum];
            System.out.println("plz input all numbles you wanna sort:");
            for(int i = 0; i < sum; i++){
                numbles[i] = input.nextInt();
            }
            System.out.println("plz choose the sorts below u wanna sort:\n"+"===1.BubbleSort\n===2.DirectInsertSort");
            int shuzi = input.nextInt();
            switch(shuzi){
            case 1: BubbleSort(numbles, sum);
            break;
            case 2: DirectInsertSort(numbles, sum);
            break;
            default:return;
            }
        }
}


