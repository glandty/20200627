/**
 * 小易经常沉迷于网络游戏.有一次,他在玩一个打怪升级的游戏,他的角色的初始能力值为 a.在接下来的一段时间内,他将会依次遇见n个怪物,每个怪物的防御力为b1,b2,b3...bn. 如果遇到的怪物防御力bi小于等于小易的当前能力值c,那么他就能轻松打败怪物,并 且使得自己的能力值增加bi;如果bi大于c,那他也能打败怪物,但他的能力值只能增加bi 与c的最大公约数.那么问题来了,在一系列的锻炼后,小易的最终能力值为多少?
 *
 * 输入描述:
 * 对于每组数据,第一行是两个整数n(1≤n<100000)表示怪物的数量和a表示小易的初始能力值.
 * 第二行n个整数,b1,b2...bn(1≤bi≤n)表示每个怪物的防御力
 *
 *
 * 输出描述:
 * 对于每组数据,输出一行.每行仅包含一个整数,表示小易的最终能力值
 * 示例1
 * 输入
 * <pre>3 50
 * 50 105 200
 * 5 20
 * 30 20 15 40 100</pre>
 * 输出
 * 110<br/>205<br/>
 */

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int a=sc.nextInt(); //a表示怪物的数量
            int b=sc.nextInt(); //b表示小易的初始化能力
            int[] arr=new int[a];
            for(int i=0;i<a;i++){
                arr[i]=sc.nextInt(); //用数组arr把每个怪物的防御力记录下来
            }
            for(int i=0;i<arr.length;i++){
                if(b>=arr[i]){//如果能力大于等于怪物防御力，则直接加
                    b+=arr[i];
                }else {
                    b+=maxCommon1(arr[i],b);//如果能力小于怪物防御力，则直接加上它们的最大公约数
                    // b+=maxCommon2(arr[i],b);
                    // b+=maxCommon3(arr[i],b);
                }
            }
            System.out.println(b);
        }
    }
    //求最大公约数方法1：穷举法
    private static int maxCommon1(int a,int b){
        if(a<b){ //如果a<b，先把ab交换，方便以后操作
            int tmp=a;
            a=b;
            b=tmp;
        }
        //如果b能直接整除a，说明b是它们的最大公约数
        if(a%b==0){
            return b;
        }
        //否则从小的开始依次整除，当a和b同时能整除那个数的时候说明就是它们的最大公约数
        for(int i=b-1;i>1;i--){
            if(a%i==0&&b%i==0){
                return i;
            }
        }
        return 1; //说明除完还没有找到，只能返回1
    }
    //求最大公约数方法2：辗转相减法
    /**两个数，相等时，最大公约数为他们其中任意一个。
     不相等时，用大数减小数。得到的差和之前的那个小数再次相减，直到两个数相等，这两个中，任意一个都是最大公约数。
     */
    private static int maxCommon2(int a,int b){
        while((a-b)!=0){
            if(a>b){
                a=a-b;
            }else {
                b=b-a;
            }
        }
        return b;
    }
    //求最大公约数方法3：辗转相除法

    /**
     * 用大数对小数求余，若余数为0，则除数为最大公约数。
     * 若余数不为0，将此余数作为除数，小数作为被除数，重新求余，直到余数为0为止。
     * 此时的最大公约数为余数。例如：27和6.  27%6=3,6%3=0.所以最大公约数为3.
     */
    private static int maxCommon3(int a,int b){
        if(a<b){ //如果a<b，先把ab交换，方便以后操作
            int tmp=a;
            a=b;
            b=tmp;
        }
        int n=a%b;
        while(a%b!=0){
            a=b;
            b=n;
            n=a%b;
        }
        return b;
    }
}
