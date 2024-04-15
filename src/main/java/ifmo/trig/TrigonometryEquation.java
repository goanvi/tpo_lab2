package ifmo.trig;

import ifmo.MathFunction;
import lombok.RequiredArgsConstructor;

import static java.lang.Math.pow;

@RequiredArgsConstructor
public class TrigonometryEquation extends MathFunction {
    private final Cos cos;
    private final Sin sin;
    private final Cot cot;
    private final Sec sec;
    private final Tan tan;
    private final Csc csc;

    @Override
    public Double evaluate(Double x, Double epsilon) {
        Double cos = this.cos.evaluate(x, epsilon);
        Double sin = this.sin.evaluate(x, epsilon);
        Double cot = this.cot.evaluate(x, epsilon);
        Double sec = this.sec.evaluate(x, epsilon);
        Double tan = this.tan.evaluate(x, epsilon);
        Double csc = this.csc.evaluate(x, epsilon);
        return ((pow((((((((((((((((((cos * cot) / sin) + sin) - (tan / cot)) - sec) - sin) - tan) - pow(cot, 3)) + (sec * cos)) * cot) / (sin - sec)) * (cot / csc)) * ((cos + tan) * (cos / pow((cos - csc), 2)))) + (tan * sec)) - (sec + (sin / cos))) * (((((((cot * sec) * sin) + csc) + ((sec - csc) * cos)) / (((sin - cos) * (((sin / csc) + tan) + cot)) - ((sec / (tan * (sec / pow(sec, 3)))) * csc))) / sec) + pow((((((csc * pow(csc, 3)) + (((tan + sec) + ((cot / cos) - cos)) + csc)) - cot) + (sin * cot)) - pow(cot, 3)), 3))) * ((((sin + sin) * cot) - ((cot / cot) * cos)) + (pow(pow(((tan + sin) / cot), 3), 3) + cos))), 3) * csc) - ((pow(tan, 2) - ((pow(tan, 3) * (pow((sin * pow((pow(cot, 3) + cos), 2)), 2) * ((pow(pow(csc, 3), 2) / cot) * cos))) / (pow((tan / ((pow(sec, 2) + csc) * (cos - cot))), 2) + (cos - ((sin - (pow((sec + (cot + tan)), 2) * (sec - (tan + (tan - sin))))) + sin))))) - (sec / (((((csc / (csc - (((cot * cot) * pow(pow((csc + ((cos - tan) - pow(tan, 2))), 3), 2)) + cos))) - csc) - (tan / sin)) + (csc + pow((sec / tan), 2))) - pow((pow(csc, 3) * pow(((tan / (sec - tan)) * cos), 3)), 3)))));
    }
}
