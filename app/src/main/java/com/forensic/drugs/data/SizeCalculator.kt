package com.forensic.drugs.data

enum class SizeCategory {
    NONE, SIGNIFICANT, LARGE, EXTRA_LARGE
}

data class SizeResult(
    val category: SizeCategory,
    val categoryText: String,
    val article: String,
    val sanction: String,
    val explanation: String
)

object SizeCalculator {

    fun calculate(substance: Substance, massGrams: Double): SizeResult {
        return when {
            massGrams < substance.significant -> SizeResult(
                category = SizeCategory.NONE,
                categoryText = "Размер не установлен",
                article = "Ст. 6.8 КоАП РФ",
                sanction = "Административный штраф от 4 000 до 5 000 рублей или административный арест до 15 суток",
                explanation = "Масса меньше значительного размера. Уголовная ответственность по ст. 228 УК РФ не наступает. Возможна административная ответственность по ст. 6.8 КоАП РФ."
            )

            massGrams < substance.large -> SizeResult(
                category = SizeCategory.SIGNIFICANT,
                categoryText = "Значительный размер",
                article = "Ст. 228 ч. 1 УК РФ",
                sanction = "Штраф до 40 000 руб. или обязательные работы до 480 часов, либо исправительные работы до 2 лет, либо ограничение свободы до 3 лет, либо лишение свободы до 3 лет",
                explanation = "Масса в пределах от значительного до крупного размера. Квалификация по ч. 1 ст. 228 УК РФ."
            )

            massGrams < substance.extraLarge -> SizeResult(
                category = SizeCategory.LARGE,
                categoryText = "Крупный размер",
                article = "Ст. 228 ч. 2 УК РФ",
                sanction = "Лишение свободы от 3 до 10 лет со штрафом до 500 000 руб. или без такового, с ограничением свободы до 1 года или без такового",
                explanation = "Масса в пределах от крупного до особо крупного размера. Квалификация по ч. 2 ст. 228 УК РФ."
            )

            else -> SizeResult(
                category = SizeCategory.EXTRA_LARGE,
                categoryText = "Особо крупный размер",
                article = "Ст. 228 ч. 3 УК РФ",
                sanction = "Лишение свободы от 10 до 15 лет со штрафом до 500 000 руб. или без такового, с ограничением свободы до 1,5 лет или без такового",
                explanation = "Масса превышает особо крупный размер. Квалификация по ч. 3 ст. 228 УК РФ."
            )
        }
    }
}
