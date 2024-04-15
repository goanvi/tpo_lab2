package ifmo.log;

public class Log10 extends LogFunction {
    private static final Double ln10 = 2.30258509299;
    private final Ln ln;

    public Log10(Ln ln) {
        this.ln = ln;
    }

    @Override
    public Double evaluate(Double x, Double eps) {
        return ln.evaluate(x, eps) / ln10;
    }
}
