import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class CalcUnitProxy {
    private int counter = 0;
    private PrintWriter writer;
    private CalcUnit calcUnit;

    private AddUnit addUnit = new AddUnit();
    private SubUnit subUnit = new SubUnit();
    private MulUnit mulUnit = new MulUnit();
    private DivUnit divUnit = new DivUnit();


    public CalcUnit getProxyObject() {
        switch (this.calcUnit.toString()){
            case "+":
                CalcUnit calcUnit = (CalcUnit) Proxy.newProxyInstance(
                        addUnit.getClass().getClassLoader(),
                        addUnit.getClass().getInterfaces(),
                        new InvocationHandler() {
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                String currTime = String.valueOf(GregorianCalendar.getInstance().getTime());
                                if (counter == 1){
                                    writer.printf("[%s] 计算结果\n", currTime);
                                    counter = 0;
                                }else {
                                    writer.printf("[%s] 判断操作符%s是否符合当前计算单元：\n", currTime, "+");
                                    counter++;
                                }

                                return method.invoke(addUnit,args);
                            }
                        }
                );
                return calcUnit;
            case "-":
                CalcUnit calcUnit2 = (CalcUnit) Proxy.newProxyInstance(
                        subUnit.getClass().getClassLoader(),
                        subUnit.getClass().getInterfaces(),
                        new InvocationHandler() {
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                String currTime = String.valueOf(GregorianCalendar.getInstance().getTime());
                                if (counter == 1){
                                    writer.printf("[%s] 计算结果\n", currTime);
                                    counter = 0;
                                }else {
                                    writer.printf("[%s] 判断操作符%s是否符合当前计算单元：\n", currTime, "-");
                                    counter++;
                                }

                                return method.invoke(subUnit,args);
                            }
                        }
                );
                return calcUnit2;
            case "*":
                CalcUnit calcUnit3 = (CalcUnit) Proxy.newProxyInstance(
                        mulUnit.getClass().getClassLoader(),
                        mulUnit.getClass().getInterfaces(),
                        new InvocationHandler() {
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                String currTime = String.valueOf(GregorianCalendar.getInstance().getTime());
                                if (counter == 1){
                                    writer.printf("[%s] 计算结果\n", currTime);
                                    counter = 0;
                                }else {
                                    writer.printf("[%s] 判断操作符%s是否符合当前计算单元：\n", currTime, "*");
                                    counter++;
                                }

                                return method.invoke(mulUnit,args);
                            }
                        }
                );
                return calcUnit3;
            case "/":
                CalcUnit calcUnit4 = (CalcUnit) Proxy.newProxyInstance(
                        divUnit.getClass().getClassLoader(),
                        divUnit.getClass().getInterfaces(),
                        new InvocationHandler() {
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                String currTime = String.valueOf(GregorianCalendar.getInstance().getTime());
                                if (counter == 1){
                                    writer.printf("[%s] 计算结果\n", currTime);
                                    counter = 0;
                                }else {
                                    writer.printf("[%s] 判断操作符%s是否符合当前计算单元：\n", currTime, "/");
                                    counter++;
                                }

                                return method.invoke(divUnit, args);
                            }
                        }
                );
                return calcUnit4;
        }

        return null;
    }


    public void setLogWriter(PrintWriter pw) {
        this.writer = pw;
    }

    public void setCalcUnit(CalcUnit cu) {
        this.calcUnit = cu;
    }
}
