package dev.wotnak.rby

import kotlin.math.pow
import kotlin.math.sqrt

typealias LabColor = Array<Double>

fun rgbToLab(hex : String): LabColor {
    val originalR = hex.substring( 1, 3 ).toInt(16)
    val originalG = hex.substring( 3, 5 ).toInt(16)
    val originalB = hex.substring( 5, 7 ).toInt(16)

    // D65/2°
    val illuminant = mapOf(
        "Xr" to 95.047,
        "Yr" to 100.0,
        "Zr" to 108.883
    )

    // --------- RGB to XYZ ---------//
    var r: Double = originalR / 255.0
    var g: Double = originalG / 255.0
    var b: Double = originalB / 255.0
    r = if (r > 0.04045) ((r + 0.055) / 1.055).pow(2.4) else r / 12.92
    g = if (g > 0.04045) ((g + 0.055) / 1.055).pow(2.4) else g / 12.92
    b = if (b > 0.04045) ((b + 0.055) / 1.055).pow(2.4) else b / 12.92
    r *= 100.0
    g *= 100.0
    b *= 100.0
    val x : Double = 0.4124 * r + 0.3576 * g + 0.1805 * b
    val y : Double = 0.2126 * r + 0.7152 * g + 0.0722 * b
    val z : Double = 0.0193 * r + 0.1192 * g + 0.9505 * b

    // --------- XYZ to Lab --------- //
    var xr: Double = x / illuminant["Xr"]!!
    var yr: Double = y / illuminant["Yr"]!!
    var zr: Double = z / illuminant["Zr"]!!
    xr = if (xr > 0.008856) xr.pow(1 / 3.0) else (7.787 * xr) + 16 / 116.0
    yr = if (yr > 0.008856) yr.pow(1 / 3.0) else (7.787 * yr) + 16 / 116.0
    zr = if (zr > 0.008856) zr.pow(1 / 3.0) else (7.787 * zr) + 16 / 116.0
    return arrayOf(
        116 * yr - 16,
        500 * (xr - yr),
        200 * (yr - zr)
    )
}

fun deltaE(labA : LabColor, labB: LabColor) : Double {
    val deltaL = labA[0] - labB[0]
    val deltaA = labA[1] - labB[1]
    val deltaB = labA[2] - labB[2]
    val c1 = sqrt(labA[1] * labA[1] + labA[2] * labA[2])
    val c2 = sqrt(labB[1] * labB[1] + labB[2] * labB[2])
    val deltaC = c1 - c2;
    var deltaH = deltaA * deltaA + deltaB * deltaB - deltaC * deltaC
    deltaH = if (deltaH < 0) 0.0 else sqrt(deltaH)
    val sc = 1.0 + 0.045 * c1
    val sh = 1.0 + 0.015 * c1
    val deltaLKlsl = deltaL / (1.0)
    val deltaCkcsc = deltaC / (sc)
    val deltaHkhsh = deltaH / (sh)
    val i = deltaLKlsl * deltaLKlsl + deltaCkcsc * deltaCkcsc + deltaHkhsh * deltaHkhsh
    return if (i < 0) 0.0 else sqrt(i)
}
