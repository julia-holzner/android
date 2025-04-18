fun main() {
    // 27.0 degrees Celsius is 80.60 degrees Fahrenheit.
    printFinalTemperature(
        initialMeasurement = 27.0,
        initialUnit = "Celsius",
        finalUnit = "Fahrenheit",
        conversionFormula = { 9.0/5.0*it + 32.0}
    )

    // 350.0 degrees Kelvin is 76.85 degrees Celsius.
    printFinalTemperature(
        initialMeasurement = 350.0,
        initialUnit = "Kelvin",
        finalUnit = "Celsius",
        conversionFormula = { it - 273.15}
    )

    // 10.0 degrees Fahrenheit is 260.93 degrees Kelvin.
    printFinalTemperature(
        initialMeasurement = 10.0,
        initialUnit = "Fahrenheit",
        finalUnit = "Kelvin",
        conversionFormula = { 5.0/9.0*(it-32.0) + 273.15}
    )
}


fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}