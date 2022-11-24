public class MulUnit implements CalcUnit{
    @Override
    public boolean fit(String operator) {
        return operator.contains("*");
    }

    @Override
    public double calc(double x, double y) {
        return x * y;
    }

    @Override
    public String toString() {
        return "*";
    }
}
