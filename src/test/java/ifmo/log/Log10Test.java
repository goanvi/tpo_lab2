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

class Log10Test {
    private static final Ln lnWithMock = mock(Ln.class);
    private static final Ln ln = new Ln();
    private static final Log10 log10 = new Log10(ln);
    private static final Log10 log10WithMock = new Log10(lnWithMock);
    private static final double eps = 0.000000000001;
    private final double accuracy = 0.1;
    private final CsvLogger csvLogger = new CsvLogger();

    @BeforeAll
    public static void setup() {
        fillMock(lnWithMock, "src/test/resources/InputLog/lnData.csv");
    }

    private static void fillMock(LogFunction tf, String tableName) {
        try (CSVReader csvReader = new CSVReader(new FileReader(tableName))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                final double x = Double.parseDouble(record[0]);
                final double y = Double.parseDouble(record[1]);
                when(tf.evaluate(x, Log10Test.eps)).thenReturn(y);
            }
        } catch (IOException | CsvException ignored) {
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/InputLog/log10Data.csv")
    @DisplayName("log10(x) test")
    void log10Test(Double input, Double trueResult) {
        csvLogger.setFilePath("src/test/resources/results/log/log10.csv");
        double result = log10.evaluate(input, eps);
        csvLogger.logger(input, result);
        assertEquals(trueResult, result, accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/InputLog/log10Data.csv")
    @DisplayName("log10(x) test with mock")
    void log10TestWithMock(Double input, Double trueResult) {
        csvLogger.setFilePath("src/test/resources/results/log/log10.csv");
        double result = log10WithMock.evaluate(input, eps);
        csvLogger.logger(input, result);
        assertEquals(trueResult, result, accuracy);
    }
}