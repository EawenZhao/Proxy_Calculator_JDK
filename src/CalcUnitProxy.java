import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.GregorianCalendar;

public class CalcUnitProxy {
    private PrintWriter writer;
    private CalcUnit calcUnit;

    private AddUnit addUnit = new AddUnit();
    private SubUnit subUnit = new SubUnit();
    private MulUnit mulUnit = new MulUnit();
    private DivUnit divUnit = new DivUnit();


    public CalcUnit getProxyObject() {
        switch (this.calcUnit.toString()){
            case "+":
                return (CalcUnit) Proxy.newProxyInstance(
                        addUnit.getClass().getClassLoader(),
                        addUnit.getClass().getInterfaces(),
                        new InvocationHandler() {
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                String currTime = String.valueOf(GregorianCalendar.getInstance().getTime());
                                if (method.getName().equals("calc")){
                                    writer.printf("[%s] 做了加法运算！\n", currTime);
                                }else {
                                    writer.printf("[%s] 判断这个运算符+\n",currTime);
                                }
                                return method.invoke(addUnit,args);
                            }
                        }
                );
            case "-":
                return (CalcUnit) Proxy.newProxyInstance(
                        subUnit.getClass().getClassLoader(),
                        subUnit.getClass().getInterfaces(),
                        new InvocationHandler() {
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                String currTime = String.valueOf(GregorianCalendar.getInstance().getTime());
                                if (method.getName().equals("calc")){
                                    writer.printf("[%s] 做了减法运算！\n", currTime);
                                }else {
                                    writer.printf("[%s] 判断这个运算符-\n",currTime);
                                }
                                return method.invoke(subUnit,args);
                            }
                        }
                );
            case "*":
                return (CalcUnit) Proxy.newProxyInstance(
                        mulUnit.getClass().getClassLoader(),
                        mulUnit.getClass().getInterfaces(),
                        new InvocationHandler() {
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                String currTime = String.valueOf(GregorianCalendar.getInstance().getTime());
                                if (method.getName().equals("calc")){
                                    writer.printf("[%s] 做了乘法运算！\n", currTime);
                                }else {
                                    writer.printf("[%s] 判断这个运算符*\n",currTime);
                                }

                                return method.invoke(mulUnit,args);
                            }
                        }
                );
            case "/":
                return (CalcUnit) Proxy.newProxyInstance(
                        divUnit.getClass().getClassLoader(),
                        divUnit.getClass().getInterfaces(),
                        new InvocationHandler() {
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                String currTime = String.valueOf(GregorianCalendar.getInstance().getTime());
                                if (method.getName().equals("calc")){
                                    writer.printf("[%s] 做了除法运算！\n", currTime);
                                }else {
                                    writer.printf("[%s] 判断这个运算符/\n",currTime);
                                }

                                return method.invoke(divUnit, args);
                            }
                        }
                );
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
