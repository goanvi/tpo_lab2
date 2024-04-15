package ifmo.trig;

import static java.lang.Double.NaN;
import static java.lang.Math.PI;

public class Cot extends TrigFunction {

    private final Sin sin;
    private final Cos cos;

    public Cot(Sin sin, Cos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    @Override
    public Double validateInput(double x) {
        if (x % PI == 0) return NaN;
        return super.validateInput(x);
    }

    @Override
    public Double evaluate(Double x, Double eps) {
        x = validateInput(x);
        double resultSin = sin.evaluate(x, eps);
        double resultCos = cos.evaluate(x, eps);


        return resultCos / resultSin;
    }
}