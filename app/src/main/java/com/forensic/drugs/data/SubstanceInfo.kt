package com.forensic.drugs.data

data class SubstanceInfo(
    val substance: Substance,
    val slang: List<String>,
    val listCategory: String,
    val legalStatus: String
)

object SubstanceInfoRepository {

    val items: List<SubstanceInfo> = listOf(
        SubstanceInfo(
            SubstanceRepository.substances[0],
            listOf("гера", "герыч", "белый", "снег", "Гер", "H"),
            "Список I",
            "Оборот в РФ запрещён. Наркотическое средство."
        ),
        SubstanceInfo(
            SubstanceRepository.substances[1],
            listOf("кокс", "кока", "снежок", "кокос", "C"),
            "Список II",
            "Оборот в РФ ограничен. Наркотическое средство."
        ),
        SubstanceInfo(
            SubstanceRepository.substances[2],
            listOf("марихуана", "травка", "шмаль", "ганжубас", "дурь", "weed", "MJ"),
            "Список I",
            "Оборот в РФ запрещён. Наркотическое средство."
        ),
        SubstanceInfo(
            SubstanceRepository.substances[3],
            listOf("анаша", "план", "гаш", "пластилин", "смола"),
            "Список I",
            "Оборот в РФ запрещён. Наркотическое средство."
        ),
        SubstanceInfo(
            SubstanceRepository.substances[4],
            listOf("гашишное масло", "масло", "жидкость", "oil"),
            "Список I",
            "Оборот в РФ запрещён. Наркотическое средство."
        ),
        SubstanceInfo(
            SubstanceRepository.substances[5],
            listOf("амф", "фен", "скорость", "спид", "фенок"),
            "Список I",
            "Оборот в РФ запрещён. Психотропное вещество."
        ),
        SubstanceInfo(
            SubstanceRepository.substances[6],
            listOf("метамф", "винт", "первитин", "мет", "лёд", "ice"),
            "Список I",
            "Оборот в РФ запрещён. Наркотическое средство."
        ),
        SubstanceInfo(
            SubstanceRepository.substances[7],
            listOf("экстази", "колёса", "таблетки", "мдма", "E", "XTC"),
            "Список I",
            "Оборот в РФ запрещён. Наркотическое средство."
        ),
        SubstanceInfo(
            SubstanceRepository.substances[8],
            listOf("меф", "мяу", "соль", "мефедрон", "4-MMC"),
            "Список I",
            "Оборот в РФ запрещён. Наркотическое средство."
        ),
        SubstanceInfo(
            SubstanceRepository.substances[9],
            listOf("соль", "скорость", "мука", "кристалл", "Alpha-PVP"),
            "Список I",
            "Оборот в РФ запрещён. Наркотическое средство."
        ),
        SubstanceInfo(
            SubstanceRepository.substances[10],
            listOf("фентанил", "фент", "белый китаец"),
            "Список I",
            "Оборот в РФ запрещён. Наркотическое средство."
        ),
        SubstanceInfo(
            SubstanceRepository.substances[11],
            listOf("кока", "листья коки", "куст"),
            "Список I",
            "Оборот в РФ запрещён. Наркотическое средство."
        ),
        SubstanceInfo(
            SubstanceRepository.substances[12],
            listOf("солома", "маковая солома", "соломка"),
            "Список I",
            "Оборот в РФ запрещён. Наркотическое средство."
        ),
        SubstanceInfo(
            SubstanceRepository.substances[13],
            listOf("опий", "опиум", "ханка", "черный"),
            "Список I",
            "Оборот в РФ запрещён. Наркотическое средство."
        ),
        SubstanceInfo(
            SubstanceRepository.substances[14],
            listOf("ЛСД", "кислота", "марки", "трип", "acid", "LSD"),
            "Список I",
            "Оборот в РФ запрещён. Наркотическое средство."
        ),
        SubstanceInfo(
            SubstanceRepository.substances[15],
            listOf("псилоцибин", "грибы", "мухомор", "псилоцибе", "shrooms"),
            "Список I",
            "Оборот в РФ запрещён. Наркотическое средство."
        ),
        SubstanceInfo(
            SubstanceRepository.substances[16],
            listOf("метадон", "фенадон", "долофин", "мед"),
            "Список I",
            "Оборот в РФ запрещён. Наркотическое средство."
        )
    )

    fun search(query: String): List<SubstanceInfo> {
        if (query.isBlank()) return emptyList()
        val q = query.trim().lowercase()
        return items.filter { info ->
            info.substance.name.lowercase().contains(q) ||
            info.substance.latinName.lowercase().contains(q) ||
            info.slang.any { it.lowercase().contains(q) }
        }
    }
}
