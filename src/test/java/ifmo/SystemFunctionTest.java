package ifmo;

import com.opencsv.CSVReader;
import ifmo.log.*;
import ifmo.trig.*;
import ifmo.utils.CsvLogger;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.FileReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SystemFunctionTest {
    private static final double accuracy = 1;
    private static final double eps = 0.000000000000000000000001;
    private static final CsvLogger csvLogger = new CsvLogger();
    public static TrigonometryEquation trigCalculator = mock(TrigonometryEquation.class);
    public static LogarithmEquation logCalculator = mock(LogarithmEquation.class);
    private final Ln ln = new Ln();
    private final Log2 log2 = new Log2(ln);
    private final Log5 log5 = new Log5(ln);
    private final Log10 log10 = new Log10(ln);
    private final Cos cos = new Cos();
    private final Sin sin = new Sin(cos);
    private final Tan tan = new Tan(sin, cos);
    private final Cot cot = new Cot(sin, cos);
    private final Csc csc = new Csc(sin);
    private final Sec sec = new Sec(cos);

    @BeforeAll
    public static void setup() {
        fillMock(logCalculator);
        fillMock(trigCalculator);
    }

    @SneakyThrows
    private static void fillMock(TrigonometryEquation tf) {
        try (CSVReader csvReader1 = new CSVReader(new FileReader("src/test/resources/unit/trigFuncBigData.csv"))) {
            List<String[]> records = csvReader1.readAll();
            try (CSVReader csvReader2 = new CSVReader(new FileReader("src/test/resources/unit/trigFuncLowData.csv"))) {
                records.addAll(csvReader2.readAll());
                for (String[] record : records) {
                    final double x = Double.parseDouble(record[0]);
                    final double y = Double.parseDouble(record[1]);
                    when(tf.evaluate(x, eps)).thenReturn(y);
                }
            }
        }
    }

    @SneakyThrows
    private static void fillMock(LogarithmEquation tf) {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/InputLog/logFuncData.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                final double x = Double.parseDouble(record[0]);
                final double y = Double.parseDouble(record[1]);
                when(tf.evaluate(x, eps)).thenReturn(y);
            }
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/calcData.csv")
    @DisplayName("allMock")
    void allMock(Double x, Double trueResult) {
        SystemFunction calculator = new SystemFunction(logCalculator, trigCalculator);
        double result = calculator.evaluate(x, eps);
        assertEquals(trueResult, result, accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/calcData.csv")
    @DisplayName("all test without mock")
    void allTest(Double x, Double trueResult) {
        csvLogger.setFilePath("src/test/resources/results/mainFunction.csv");
        SystemFunction calculator = new SystemFunction(new LogarithmEquation(ln, log2, log5, log10), new TrigonometryEquation(cos, sin, cot, sec, tan, csc));
        double result = calculator.evaluate(x, eps);
        csvLogger.logger(x, result);
        assertEquals(trueResult, result, result > 1E10 ? 1E11 : accuracy);
    }
}