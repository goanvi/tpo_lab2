package ifmo.log;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ifmo.utils.CsvLogger;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LogarithmEquationTest {
    private static final Ln lnWithMock = mock(Ln.class);
    private static final Log2 log2WithMock = mock(Log2.class);
    private static final Log5 log5WithMock = mock(Log5.class);
    private static final Log10 log10WithMock = mock(Log10.class);
    private static final Ln ln = new Ln();
    private final CsvLogger csvLogger = new CsvLogger();
    private static final Log2 log2 = new Log2(ln);
    private static final Log5 log5 = new Log5(ln);
    private static final Log10 log10 = new Log10(ln);
    private static final double eps = 0.000000000000000001;
    private final double accuracy = 0.1;


    @BeforeAll
    public static void setup() {
        fillMock(lnWithMock, "src/test/resources/InputLog/lnData.csv");
        fillMock(log2WithMock, "src/test/resources/InputLog/log2Data.csv");
        fillMock(log5WithMock, "src/test/resources/InputLog/log5Data.csv");
        fillMock(log10WithMock, "src/test/resources/InputLog/log10Data.csv");
    }

    private static void fillMock(LogFunction tf, String tableName) {
        try (CSVReader csvReader = new CSVReader(new FileReader(tableName))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                final double x = Double.parseDouble(record[0]);
                final double y = Double.parseDouble(record[1]);
                when(tf.evaluate(x, LogarithmEquationTest.eps)).thenReturn(y);
            }
        } catch (IOException | CsvException ignored) {
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/InputLog/logFuncData.csv")
    @DisplayName("logarithmic function test with full mocks")
    void testFunctionWithMocks(Double x, Double trueResult) {
        LogarithmEquation calculator = new LogarithmEquation(lnWithMock, log2WithMock, log5WithMock, log10WithMock);
        double result = calculator.evaluate(x, eps);
        assertEquals(trueResult, result, accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/InputLog/logFuncData.csv")
    @DisplayName("logarithmic function")
    void testFunction(Double x, Double trueResult) {
        csvLogger.setFilePath("src/test/resources/results/log/logarithmEquation.csv");
        LogarithmEquation calculator = new LogarithmEquation(ln, log2, log5, log10);
        double result = calculator.evaluate(x, eps);
        csvLogger.logger(x, result);
        assertEquals(trueResult, result, accuracy);
    }
}