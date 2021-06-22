package dev.wotnak.rby

import kotlin.random.Random

class FishLengthRange(
    val min : Float,
    val max : Float,
) {
    constructor(fixedWidth: Float) : this(fixedWidth, fixedWidth)

    fun getLength() : Float = Random.nextDouble(min.toDouble(), max.toDouble()).toFloat()

}
