package project_tests.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class UtilMethods {


    public BigDecimal findDiscountForCode(String fileName, String targetCode) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            // Skip header
            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String code = parts[0].trim();

                if (code.equalsIgnoreCase(targetCode)) {
                    return new BigDecimal(parts[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // If not found or error occurs
        return null;
    }
}
