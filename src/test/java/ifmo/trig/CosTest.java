package ifmo.trig;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ifmo.utils.CsvLogger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CosTest {

    private final CsvLogger csvLogger = new CsvLogger();
    private final double accuracy = 0.001;
    private final double eps = 0.0000000001;
    private final Cos cos = new Cos();


    @ParameterizedTest
    @CsvFileSource(resources = "/unit/cosData.csv")
    @DisplayName("cos(x) test")
    void cosTest(Double x,  Double trueResult) {
        csvLogger.setFilePath("src/test/resources/results/trig/cos.csv");
        double result = cos.evaluate(x, eps);
        csvLogger.logger(x, result);
        assertEquals(trueResult, result, accuracy);
    }
}