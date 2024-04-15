package ifmo.log;


import ifmo.MathFunction;

import static java.lang.Double.NaN;

public abstract class LogFunction extends MathFunction {
    @Override
    public Double validateInput(double x) {
        x = super.validateInput(x);
        if (x <= 0) {
            return NaN;
        }
        return x;
    }
}