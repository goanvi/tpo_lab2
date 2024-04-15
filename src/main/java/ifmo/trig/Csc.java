package ifmo.trig;

import static java.lang.Double.NaN;
import static java.lang.Math.PI;

public class Csc extends TrigFunction {

    private final Sin sin;

    public Csc(Sin sin) {
        this.sin = sin;
    }

    @Override
    public Double validateInput(double x) {
        if (x % PI == 0) return NaN;
        return super.validateInput(x);
    }

    @Override
    public Double evaluate(Double x, Double eps) {
        x = validateInput(x);
        double result = sin.evaluate(x, eps);
        return 1.0 / result;
    }
}
