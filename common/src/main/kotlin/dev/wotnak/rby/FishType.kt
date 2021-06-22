package dev.wotnak.rby

class FishType(
    val name: String,
    val description: List<String>,
    val length: FishLengthRange,
    val conditions: List<FishingCondition>,
    val effects: List<FishEffect>,
) {

}
