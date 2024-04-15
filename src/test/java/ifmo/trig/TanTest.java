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

class TanTest {
    public static Sin sinMock = mock(Sin.class);
    public static Cos cosMock = mock(Cos.class);
    public static Cos cos = new Cos();
    public static Sin sin = new Sin(cos);
    private static final double eps = 0.0000001;
    private final double accuracy = 0.01;
    private final CsvLogger csvLogger = new CsvLogger();
    private final Tan tan = new Tan(sin, cos);
    private final Tan tanWithMock = new Tan(sinMock, cosMock);


    @BeforeAll
    public static void setup() {
        fillMock(sinMock, "src/test/resources/unit/sinData.csv");
        fillMock(cosMock, "src/test/resources/unit/cosData.csv");

    }

    private static void fillMock(TrigFunction tf, String tableName) {
        try (CSVReader csvReader = new CSVReader(new FileReader(tableName))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                final double x = Double.parseDouble(record[0]);
                final double y = Double.parseDouble(record[1]);
                when(tf.evaluate(x % (2 * PI), TanTest.eps)).thenReturn(y);
            }
        } catch (IOException | CsvException ignored) {
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/unit/tanData.csv")
    @DisplayName("tan(x) test")
    void tanTest(Double input, Double trueResult) {
        csvLogger.setFilePath("src/test/resources/results/trig/tan.csv");
        double result = tan.evaluate(input, eps);
        csvLogger.logger(input, result);
        assertEquals(trueResult, result, accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/unit/tanData.csv")
    @DisplayName("tan(x) test with mock")
    void tanTestWithMock(Double input, Double trueResult) {
        double result = tanWithMock.evaluate(input, eps);
        assertEquals(trueResult, result, accuracy);
    }
}