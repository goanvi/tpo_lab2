package ifmo.trig;

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

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SecTest {
    public static Cos cosMock = mock(Cos.class);
    public static Cos cos = new Cos();
    private static final double eps = 0.0000001;
    private final double accuracy = 0.01;
    private final CsvLogger csvLogger = new CsvLogger();
    private final Sec sec = new Sec(cos);
    private final Sec secWithMock = new Sec(cosMock);

    @BeforeAll
    public static void setup() {
        fillMock(cosMock, "src/test/resources/unit/cosData.csv");

    }

    private static void fillMock(TrigFunction tf, String tableName) {
        try (CSVReader csvReader = new CSVReader(new FileReader(tableName))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                final double x = Double.parseDouble(record[0]);
                final double y = Double.parseDouble(record[1]);
                when(tf.evaluate(x % (2 * PI), SecTest.eps)).thenReturn(y);
            }
        } catch (IOException | CsvException ignored) {
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/unit/secData.csv")
    @DisplayName("sec(x) test")
    void secTest(Double input, Double trueResult) {
        csvLogger.setFilePath("src/test/resources/results/trig/sec.csv");
        double result = sec.evaluate(input, eps);
        csvLogger.logger(input, result);
        assertEquals(trueResult, result, accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/unit/secData.csv")
    @DisplayName("sec(x) test with mock")
    void secTestWithMock(Double input, Double trueResult) {
        double result = secWithMock.evaluate(input, eps);
        assertEquals(trueResult, result, accuracy);
    }
}