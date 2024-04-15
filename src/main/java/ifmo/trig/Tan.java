package ifmo.trig;

import static java.lang.Double.NaN;
import static java.lang.Math.PI;

public class Tan extends TrigFunction {
    private final Sin sin;
    private final Cos cos;

    public Tan(Sin sin, Cos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    @Override
    public Double validateInput(double x) {
        if (Double.compare(Math.abs(x % PI),  PI / 2 ) == 0) return NaN;
        return super.validateInput(x);
    }

    @Override
    public Double evaluate(Double x, Double eps) {
        x = validateInput(x);
        double resultSin = sin.evaluate(x,eps);
        double resultCos = cos.evaluate(x,eps);


        return resultSin / resultCos;
    }
}
