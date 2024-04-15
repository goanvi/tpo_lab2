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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TrigonometryEquationTest {

    public static Sin sinMock = mock(Sin.class);
    public static Cos cosMock = mock(Cos.class);
    public static Tan tanMock = mock(Tan.class);
    public static Cot cotMock = mock(Cot.class);
    public static Sec secMock = mock(Sec.class);
    public static Csc cscMock = mock(Csc.class);
    private final CsvLogger csvLogger = new CsvLogger();
    public static Cos cos = new Cos();
    public static Sin sin = new Sin(cos);
    public static Tan tan = new Tan(sin, cos);
    public static Cot cot = new Cot(sin, cos);
    public static Sec sec = new Sec(cos);
    public static Csc csc = new Csc(sin);
    private static final double eps = 0.00001;


    @BeforeAll
    public static void setup() {
        fillMock(sinMock, "src/test/resources/unit/sinData.csv");
        fillMock(cosMock, "src/test/resources/unit/cosData.csv");
        fillMock(tanMock, "src/test/resources/unit/tanData.csv");
        fillMock(cotMock, "src/test/resources/unit/cotData.csv");
        fillMock(secMock, "src/test/resources/unit/secData.csv");
        fillMock(cscMock, "src/test/resources/unit/cscData.csv");

    }

    private static void fillMock(TrigFunction tf, String tableName) {
        try (CSVReader csvReader = new CSVReader(new FileReader(tableName))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                final double x = Double.parseDouble(record[0]);
                final double y = Double.parseDouble(record[1]);
                when(tf.evaluate(x, TrigonometryEquationTest.eps)).thenReturn(y);
            }
        } catch (IOException | CsvException ignored) {
        }
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/unit/trigFuncBigData.csv")
    @DisplayName("trigonometric function with big result test with full mocks")
    void trigFuncBigDataTestWithMock(Double input, Double trueResult) {
        TrigonometryEquation trigonometricCalculator = new TrigonometryEquation(cosMock, sinMock, cotMock, secMock, tanMock, cscMock);
        double result = trigonometricCalculator.evaluate(input, eps);
        assertEquals(trueResult, result, 1E11);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/unit/trigFuncBigData.csv")
    @DisplayName("trigonometric function with big result test without mocks")
    void trigFuncBigDataTest(Double input, Double trueResult) {
        csvLogger.setFilePath("src/test/resources/results/trig/trigFuncBigData.csv");
        TrigonometryEquation trigonometricCalculator = new TrigonometryEquation(cos, sin, cot, sec, tan, csc);
        double result = trigonometricCalculator.evaluate(input, eps);
        csvLogger.logger(input, result);
        assertEquals(trueResult, result, 1E10);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/unit/trigFuncLowData.csv")
    @DisplayName("trigonometric function with low result test with full mocks")
    void trigFuncLowDataTestWithMock(Double input, Double trueResult) {
        TrigonometryEquation trigonometricCalculator = new TrigonometryEquation(cosMock, sinMock, cotMock, secMock, tanMock, cscMock);
        double result = trigonometricCalculator.evaluate(input, eps);
        assertEquals(trueResult, result, 0.01);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/unit/trigFuncLowData.csv")
    @DisplayName("trigonometric function with low result test without mocks")
    void trigFuncLowDataTest(Double input, Double trueResult) {
        csvLogger.setFilePath("src/test/resources/results/trig/trigFuncLowData.csv");
        TrigonometryEquation trigonometricCalculator = new TrigonometryEquation(cos, sin, cot, sec, tan, csc);
        double result = trigonometricCalculator.evaluate(input, eps);
        csvLogger.logger(input, result);
        assertEquals(trueResult, result, 0.01);
    }
}