import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        CalcUnit[] calculator = {new AddUnit(), new SubUnit(), new MulUnit(), new DivUnit()};
        //Calc box
        CalcUnitProxy proxy = new CalcUnitProxy();

        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File("D://log.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        proxy.setLogWriter(pw);


        while (true) {
            System.out.print("输入：");
            String line = input.nextLine();
            if (line.equals("exit"))
                break;
            String[] items = line.trim().split(" ");
            if (items.length != 3)
                continue;
            String operator = items[1];
            double x = Double.parseDouble(items[0]);
            double y = Double.parseDouble(items[2]);
            // 代理对象替代具体计算单元进行判断和计算
            for (CalcUnit cu : calculator) {
                proxy.setCalcUnit(cu);
                CalcUnit proxyObject = proxy.getProxyObject();
                if (proxyObject.fit(operator)) {
                    System.out.printf("结果%4.2f\n", proxyObject.calc(x, y));
                    break;
                }
            }
            pw.flush();
        }
        pw.close();
        input.close();
    }
}
