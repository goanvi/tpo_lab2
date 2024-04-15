package ifmo.trig;

import static java.lang.Double.NaN;
import static java.lang.Math.PI;

public class Cos extends TrigFunction {

    @Override
    public Double evaluate(Double x, Double eps) {
        x = validateInput(x);
        if (x.equals(NaN)) return NaN;

        if (x == -PI / 2 || x == -3 * PI / 2) {
            return 0.0;
        }
        int n = 0;
        double resultCos = 0;
        double xx = x * x;
        double pow = 1;
        double fact = 1;
        int sign = 1;

        while (true) {
            double term = sign * pow / fact;
            if (Math.abs(term) <= eps) {
                break;
            }
            resultCos += term;

            sign = -sign;
            fact *= (n + 1) * (n + 2);
            pow *= xx;
            n += 2;
        }
        return resultCos;
    }
}