import org.example.Product
import org.example.calculateTotalElectronicsPriceOver500
import org.example.celsiusToFahrenheit
import org.example.countElectronicsOver500
import org.example.kilometersToMiles
import kotlin.test.Test
import kotlin.test.assertEquals

class WorkshopTest {

    // --- Tests for Workshop #1: Unit Converter ---

    // celsius input: 20.0
    // expected output: 68.0
    @Test
    fun `test celsiusToFahrenheit with positive value`() {
        // Arrange: ตั้งค่า input และผลลัพธ์ที่คาดหวัง
        val celsiusInput = 20.0
        val expectedFahrenheit = 68.0

        // Act: เรียกใช้ฟังก์ชันที่ต้องการทดสอบ
        val actualFahrenheit = celsiusToFahrenheit(celsiusInput)

        // Assert: ตรวจสอบว่าผลลัพธ์ที่ได้ตรงกับที่คาดหวัง
        assertEquals(expectedFahrenheit, actualFahrenheit, 0.001, "20°C should be 68°F")
    }

    // celsius input: 0.0
    // expected output: 32.0
    @Test
    fun `test celsiusToFahrenheit with zero`() {
        //Arrange
        val celsiusInput = 0.0
        val expectedFahrenheit = 32.0

        //Act
        val actualFahrenheit = celsiusToFahrenheit(celsiusInput)

        //Assert
        assertEquals(
            expected = expectedFahrenheit,
            actual = actualFahrenheit,
            absoluteTolerance = .001,
        )
    }

    // celsius input: -10.0
    // expected output: 14.0
    @Test
    fun `test celsiusToFahrenheit with negative value`() {
        val celciusInput = -10.0
        val expectedFahrenheit = 14.0

        val actualFarenheit = celsiusToFahrenheit(celciusInput)

        assertEquals(
            expected = expectedFahrenheit,
            actual = actualFarenheit,
            absoluteTolerance = .001,
        )
    }

    // test for kilometersToMiles function
    // kilometers input: 1.0
    // expected output: 0.621371
    @Test
    fun `test kilometersToMiles with one kilometer`() {
        val kilometersInput = 1.0
        val expectedKilometers = 0.621371

        val actualKilometers = kilometersToMiles(kilometersInput)

        assertEquals(
            expected = expectedKilometers,
            actual = actualKilometers,
            absoluteTolerance = .001,
        )
    }

// --- Tests for Workshop #2: Data Analysis Pipeline ---

    // 1. เทสเช็คผลรวมราคาสินค้า Electronics ที่ราคา > 500 บาท
    @Test
    fun `test calculateTotalElectronicsPriceOver500 returns correct sum`() {
        // Arrange
        val products = listOf(
            Product("Monitor", 1500.0, "Electronics"),   // นับ
            Product("Keyboard", 450.0, "Electronics"),   // ไม่นับ (ราคาไม่ถึง 500)
            Product("Mouse", 800.0, "Electronics"),      // นับ
            Product("Shoes", 2000.0, "Apparel")          // ไม่นับ (คนละหมวด)
        )
        // ผลรวมที่คาดหวัง: 1500.0 + 800.0 = 2300.0
        val expectedTotal = 2300.0

        // Act
        val actualTotal = calculateTotalElectronicsPriceOver500(products)

        // Assert
        assertEquals(
            expected = expectedTotal,
            actual = actualTotal,
            absoluteTolerance = 0.001,
            message = "Total price of Electronics > 500 should be 2300.0"
        )
    }

    // 2. เทสเช็คจำนวนสินค้า Electronics ที่ราคา > 500 บาท
    @Test
    fun `test countElectronicsOver500 returns correct count`() {
        // Arrange
        val products = listOf(
            Product("Monitor", 6000.0, "Electronics"),   // นับ (1)
            Product("Keyboard", 450.0, "Electronics"),   // ไม่นับ
            Product("Smartwatch", 7500.0, "Electronics"),// นับ (2)
            Product("Headphones", 1800.0, "Electronics"),// นับ (3)
            Product("Shoes", 2000.0, "Apparel")          // ไม่นับ
        )
        // จำนวนที่คาดหวังคือ 3 ชิ้น
        val expectedCount = 3

        // Act
        val actualCount = countElectronicsOver500(products)

        // Assert
        assertEquals(
            expected = expectedCount,
            actual = actualCount,
            message = "Count of Electronics > 500 should be 3"
        )
    }

    // --- Tests for Workshop #2: Data Analysis Pipeline End ---
}