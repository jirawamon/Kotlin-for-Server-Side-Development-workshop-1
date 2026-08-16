package org.example

// จำนวนหลักของเลขบัตรประชาชนไทย
private const val CITIZEN_ID_LENGTH = 13

/**
 * ตรวจสอบความถูกต้องของเลขบัตรประชาชนไทย 13 หลัก
 *
 * กติกา:
 * 1. ต้องยาว 13 หลักพอดี
 * 2. ต้องเป็นตัวเลข 0-9 ทั้งหมด
 * 3. หลักที่ 13 เป็น check digit ที่คำนวณจาก 12 หลักแรก
 *    - คูณหลักที่ i (เริ่มที่ 0) ด้วยน้ำหนัก (13 - i) แล้วรวมกัน
 *    - check digit = (11 - (ผลรวม % 11)) % 10
 */
fun validateCitizenId(id: String): Boolean {
    if (id.length != CITIZEN_ID_LENGTH) return false

    // ต้องเป็นเลขอารบิก 0-9 เท่านั้น
    // ห้ามใช้ isDigit() เพราะมันรับเลขทุกภาษาใน Unicode รวมถึงเลขไทย ๐-๙ ด้วย
    if (!id.all { it in '0'..'9' }) return false

    // ผลรวมถ่วงน้ำหนักของ 12 หลักแรก
    val weightedSum = (0 until CITIZEN_ID_LENGTH - 1).sumOf { i ->
        (id[i] - '0') * (CITIZEN_ID_LENGTH - i)
    }

    val expectedCheckDigit = (11 - (weightedSum % 11)) % 10
    val actualCheckDigit = id[CITIZEN_ID_LENGTH - 1] - '0'

    return expectedCheckDigit == actualCheckDigit
}
