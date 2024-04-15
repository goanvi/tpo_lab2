package ifmo.trig;

import static java.lang.Double.NaN;

public class Sec extends TrigFunction {

    private final Cos cos;

    public Sec(Cos cos) {
        this.cos = cos;
    }

    @Override
    public Double validateInput(double x) {
        return super.validateInput(x);
    }

    @Override
    public Double evaluate(Double x, Double eps) {
        x = validateInput(x);

        double result = cos.evaluate(x, eps);
        if (result == 0) return NaN;
        return 1.0 / result;
    }
}
