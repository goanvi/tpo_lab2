package ifmo.log;

import ifmo.MathFunction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogarithmEquation extends MathFunction {
    private final Ln ln;
    private final Log2 log2;
    private final Log5 log5;
    private final Log10 log10;

    @Override
    public Double evaluate(Double x, Double epsilon) {
        Double ln = this.ln.evaluate(x, epsilon);
        Double log2 = this.log2.evaluate(x, epsilon);
        Double log5 = this.log5.evaluate(x, epsilon);
        Double log10 = this.log10.evaluate(x, epsilon);
        return (((((log5 * log10) / log5) / log2) + Math.pow(log5, 3)) - ln);
    }

}
