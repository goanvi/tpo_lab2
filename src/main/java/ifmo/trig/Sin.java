package ifmo.trig;

import static java.lang.Double.NaN;
import static java.lang.Math.*;

public class Sin extends TrigFunction {

    private final Cos cos;

    public Sin(Cos cos) {
        this.cos = cos;
    }

    @Override
    public Double evaluate(Double x, Double eps) {
        x = validateInput(x);
        if (x.equals(NaN)) return NaN;

        if (x == 0 || x == -PI) {
            return 0.0;
        }

        double resultCos = cos.evaluate(x, eps);
        double resultSin = sqrt(1 - pow(resultCos, 2));

        if (x < 0 && x > -PI || x > PI && x < 2 * PI) {
            resultSin *= -1;
        }

        return resultSin;
    }

}