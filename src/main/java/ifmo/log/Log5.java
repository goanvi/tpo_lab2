package ifmo.log;

public class Log5 extends LogFunction {
    private static final Double ln5 = 1.60943791243;
    private final Ln ln;

    public Log5(Ln ln) {
        this.ln = ln;
    }

    @Override
    public Double evaluate(Double x, Double eps) {
        return ln.evaluate(x, eps) / ln5;
    }
}
