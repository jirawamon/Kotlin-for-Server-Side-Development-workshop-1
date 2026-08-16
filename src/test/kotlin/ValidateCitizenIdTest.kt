import org.example.validateCitizenId
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ValidateCitizenIdTest {

    @Test
    fun `valid 13 digit id returns true`() {
        // Arrange
        val id = "1101700185206" // เลขสมมติ checksum ถูกต้อง

        // Act
        val result = validateCitizenId(id)

        // Assert
        assertTrue(actual = result)
    }

    @Test
    fun `id with wrong length returns false`() {
        assertFalse(actual = validateCitizenId(id = "12345"))          // สั้นไป
        assertFalse(actual = validateCitizenId(id = "11017001852066")) // ยาวไป (14 หลัก)
        assertFalse(actual = validateCitizenId(id = ""))               // ว่างเปล่า
    }

    @Test
    fun `id containing non digit characters returns false`() {
        assertFalse(actual = validateCitizenId(id = "1101700A85206"))
        assertFalse(actual = validateCitizenId(id = "abcdefghijklm"))
        assertFalse(actual = validateCitizenId(id = "1101700 85206")) // มีช่องว่าง
    }

    @Test
    fun `id with wrong check digit returns false`() {
        // Arrange: 12 หลักแรกเหมือนเลขที่ถูกต้อง แต่หลักสุดท้ายผิด (ควรเป็น 6)
        val id = "1101700185207"

        // Act
        val result = validateCitizenId(id)

        // Assert
        assertFalse(actual = result, message = "Check digit 7 should not be accepted")
    }

    @Test
    fun `id with thai numerals returns false`() {
        // ยาว 14 ตัว และมี 'ฐ' ปน -> ตกตั้งแต่ด่านความยาว
        assertFalse(validateCitizenId(id = "๑๒๑๒๒๓๖๗๗๕๔๖ฐ๙"))

        // เลขไทย 13 ตัวพอดี checksum ตรง -> ต้องไม่ผ่าน เพราะบัตรประชาชนใช้เลขอารบิกเท่านั้น
        assertFalse(validateCitizenId(id = "๑๑๐๑๗๐๐๑๘๕๒๐๖"))
    }
}
