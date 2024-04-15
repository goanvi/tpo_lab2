package ifmo;

import ifmo.log.LogarithmEquation;
import ifmo.trig.TrigonometryEquation;
import lombok.RequiredArgsConstructor;

import static java.lang.Double.NaN;

@RequiredArgsConstructor
public class SystemFunction extends MathFunction {
    private final LogarithmEquation logarithmEquation;
    private final TrigonometryEquation trigonometryEquation;

    @Override
    public Double evaluate(Double x, Double epsilon) {
        x = validateInput(x);
        if (x.equals(NaN)) return NaN;
        if (x > 0) {
            return logarithmEquation.evaluate(x, epsilon);
        } else {
            return trigonometryEquation.evaluate(x, epsilon);
        }
    }
}
