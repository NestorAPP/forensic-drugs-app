package com.forensic.drugs.data

data class Plant(
    val name: String,
    val latinName: String,
    val significant: Double,
    val large: Double,
    val extraLarge: Double,
    val appearance: String,
    val hints: List<String>,
    val inOfficialList: Boolean = true,
    // Культивирование по Постановлению № 934
    val hasCultivationLimits: Boolean = false,
    val cultivationLarge: Int = 0,
    val cultivationExtraLarge: Int = 0,
    val cultivationUnit: String = "растений"
)

object PlantsRepository {

    val plants: List<Plant> = listOf(
        // === ОФИЦИАЛЬНЫЙ ПЕРЕЧЕНЬ (Постановление № 1002 + № 934) ===
        Plant(
            name = "Голубой лотос",
            latinName = "Nymphea caerulea",
            significant = 3.0,
            large = 30.0,
            extraLarge = 3000.0,
            appearance = "Водное растение с крупными плавающими листьями и голубыми цветками. Встречается в пресных водоёмах. Содержит алкалоиды, обладающие психоактивным действием.",
            hints = listOf(
                "Обнаружение — в водоёмах, аквариумах, оранжереях.",
                "Изъять: целые растения, листья, цветки, корневища.",
                "Фиксировать: место произрастания, количество экземпляров, стадию вегетации."
            ),
            hasCultivationLimits = true,
            cultivationLarge = 10,
            cultivationExtraLarge = 100,
            cultivationUnit = "растений"
        ),
        Plant(
            name = "Грибы, содержащие псилоцибин и (или) псилоцин",
            latinName = "Psilocybe, Panaeolus, Gymnoascus и др.",
            significant = 10.0,
            large = 100.0,
            extraLarge = 10000.0,
            appearance = "Небольшие грибы с характерной конической или колокольчатой шляпкой. Произрастают на пастбищах, в лесах, на гниющей древесине. При повреждении синеют.",
            hints = listOf(
                "Изъять: целые плодовые тела, срезы, мицелий.",
                "Назначить микологическую и химическую экспертизу.",
                "Фиксировать место сбора — GPS-координаты, фото."
            ),
            hasCultivationLimits = true,
            cultivationLarge = 20,
            cultivationExtraLarge = 200,
            cultivationUnit = "плодовых тел"
        ),
        Plant(
            name = "Кактус, содержащий мескалин",
            latinName = "Lophophora williamsii и другие виды кактусов",
            significant = 50.0,
            large = 250.0,
            extraLarge = 25000.0,
            appearance = "Небольшой кактус без колючек, серо-зелёного цвета, шаровидной или слегка вытянутой формы. Произрастает в засушливых регионах, часто выращивается в домашних условиях.",
            hints = listOf(
                "Изъять: целые растения, стебли, срезы.",
                "Фиксировать: место произрастания (горшок, грунт, теплица).",
                "Назначить ботаническую и химическую экспертизу."
            ),
            hasCultivationLimits = true,
            cultivationLarge = 2,
            cultivationExtraLarge = 10,
            cultivationUnit = "растений"
        ),
        Plant(
            name = "Кат",
            latinName = "Catha edulis",
            significant = 100.0,
            large = 1000.0,
            extraLarge = 100000.0,
            appearance = "Вечнозелёный кустарник или небольшое дерево с овальными листьями и мелкими белыми цветками. Листья содержат катинон.",
            hints = listOf(
                "Изъять: листья, стебли, корни.",
                "Фиксировать: количество кустов, высоту, наличие ухода.",
                "Назначить ботаническую и химическую экспертизу."
            ),
            hasCultivationLimits = true,
            cultivationLarge = 4,
            cultivationExtraLarge = 40,
            cultivationUnit = "растений"
        ),
        Plant(
            name = "Кокаиновый куст",
            latinName = "Erythroxylon",
            significant = 20.0,
            large = 250.0,
            extraLarge = 20000.0,
            appearance = "Кустарник или небольшое дерево с овальными листьями. Листья содержат кокаин. Произрастает в тропиках, иногда выращивается в оранжереях.",
            hints = listOf(
                "Изъять: листья, стебли, корни.",
                "Фиксировать: место произрастания, количество растений.",
                "Назначить ботаническую и химическую экспертизу."
            ),
            hasCultivationLimits = true,
            cultivationLarge = 4,
            cultivationExtraLarge = 20,
            cultivationUnit = "растений"
        ),
        Plant(
            name = "Конопля",
            latinName = "Cannabis",
            significant = 6.0,
            large = 100.0,
            extraLarge = 100000.0,
            appearance = "Однолетнее травянистое растение с характерными пальчато-рассечёнными листьями и плотными соцветиями. Произрастает повсеместно, часто культивируется в теплицах, на балконах, в жилых помещениях.",
            hints = listOf(
                "Изъять: растения целиком, соцветия, листья, стебли.",
                "Фиксировать: стадию роста, количество кустов, наличие оборудования (лампы, вентиляция, удобрения).",
                "Назначить ботаническую и химическую экспертизу.",
                "Учитывать: культивирование — это уход (полив, освещение, удобрения)."
            ),
            hasCultivationLimits = true,
            cultivationLarge = 20,
            cultivationExtraLarge = 330,
            cultivationUnit = "растений"
        ),
        Plant(
            name = "Мак снотворный и другие виды мака рода Papaver",
            latinName = "Papaver somniferum L и др.",
            significant = 20.0,
            large = 500.0,
            extraLarge = 100000.0,
            appearance = "Однолетнее травянистое растение с крупными цветками (белыми, розовыми, фиолетовыми) и шаровидными коробочками с семенами. Содержит алкалоиды опия.",
            hints = listOf(
                "Изъять: растения целиком, коробочки, стебли, солому.",
                "Фиксировать: стадию роста, количество растений, наличие надрезов на коробочках.",
                "Назначить ботаническую и химическую экспертизу.",
                "Учитывать: наличие надрезов — признак сбора опия."
            ),
            hasCultivationLimits = true,
            cultivationLarge = 10,
            cultivationExtraLarge = 200,
            cultivationUnit = "растений"
        ),
        Plant(
            name = "Мимоза хостилис",
            latinName = "Mimosa tenuiflora",
            significant = 0.0,
            large = 0.0,
            extraLarge = 0.0,
            appearance = "Кустарник или небольшое дерево с перистыми листьями и мелкими белыми цветками. Кора и корни содержат диметилтриптамин (ДМТ).",
            hints = listOf(
                "Включена в перечень Постановлением № 827 от 12.07.2017.",
                "Размеры культивирования — по Постановлению № 934.",
                "Изъять: кору, корни, целые растения.",
                "Назначить ботаническую и химическую экспертизу."
            ),
            hasCultivationLimits = true,
            cultivationLarge = 10,
            cultivationExtraLarge = 100,
            cultivationUnit = "растений"
        ),
        Plant(
            name = "Митрагина прекрасная (Кратом)",
            latinName = "Mitragyna speciosa",
            significant = 0.0,
            large = 0.0,
            extraLarge = 0.0,
            appearance = "Вечнозелёное дерево с крупными овальными листьями. Листья содержат митрагинин — психоактивное вещество.",
            hints = listOf(
                "Включена в перечень Постановлением № 1041 от 09.08.2019.",
                "Размеры культивирования — по Постановлению № 934.",
                "Изъять: листья, стебли, целые растения.",
                "Назначить ботаническую и химическую экспертизу."
            ),
            hasCultivationLimits = true,
            cultivationLarge = 10,
            cultivationExtraLarge = 100,
            cultivationUnit = "растений"
        ),
        Plant(
            name = "Роза гавайская",
            latinName = "Argyreia nervosa",
            significant = 3.0,
            large = 30.0,
            extraLarge = 3000.0,
            appearance = "Вьющееся растение с крупными сердцевидными листьями и воронковидными цветками. Семена содержат эргин (психоактивное вещество).",
            hints = listOf(
                "Изъять: семена, плоды, упаковку.",
                "Фиксировать: место обнаружения, количество.",
                "Назначить ботаническую и химическую экспертизу."
            ),
            hasCultivationLimits = true,
            cultivationLarge = 10,
            cultivationExtraLarge = 100,
            cultivationUnit = "растений"
        ),
        Plant(
            name = "Шалфей предсказателей",
            latinName = "Salvia divinorum",
            significant = 3.0,
            large = 30.0,
            extraLarge = 3000.0,
            appearance = "Многолетнее травянистое растение с крупными овальными листьями и полым стеблем. Содержит сальвинорин А.",
            hints = listOf(
                "Изъять: листья, стебли, целые растения.",
                "Фиксировать: место произрастания, количество.",
                "Назначить ботаническую и химическую экспертизу."
            ),
            hasCultivationLimits = true,
            cultivationLarge = 10,
            cultivationExtraLarge = 100,
            cultivationUnit = "растений"
        ),
        Plant(
            name = "Эфедра (хвойник)",
            latinName = "Ephedra L",
            significant = 0.0,
            large = 0.0,
            extraLarge = 0.0,
            appearance = "Кустарник с членистыми зелёными побегами, похожими на хвою. Содержит эфедрин и псевдоэфедрин — прекурсоры для кустарного изготовления наркотиков.",
            hints = listOf(
                "В перечень Постановления № 1002 как растение НЕ входит.",
                "НО: эфедрин и псевдоэфедрин — прекурсоры (Постановление № 1002, список IV).",
                "Оборот эфедрина контролируется.",
                "Может фигурировать по ст. 228.3, 228.4 УК РФ (прекурсоры).",
                "Назначить ботаническую и химическую экспертизу."
            ),
            hasCultivationLimits = true,
            cultivationLarge = 10,
            cultivationExtraLarge = 200,
            cultivationUnit = "растений"
        ),

        // === СМЕЖНЫЕ РАСТЕНИЯ (НЕ В ПЕРЕЧНЕ) ===
        Plant(
            name = "Мухомор (красный, пантерный)",
            latinName = "Amanita muscaria, Amanita pantherina",
            significant = 0.0,
            large = 0.0,
            extraLarge = 0.0,
            appearance = "Крупный гриб с ярко-красной или коричневой шляпкой, покрытой белыми хлопьями. Содержит мусцимол и иботеновую кислоту. НЕ входит в перечень № 1002, но может фигурировать по ст. 234 (ядовитые вещества) при отравлении.",
            hints = listOf(
                "В перечень Постановления № 1002 НЕ входит.",
                "Может фигурировать по ст. 234 УК РФ, если отнесён к ядовитым веществам.",
                "При обнаружении — назначить микологическую и химическую экспертизу.",
                "Фиксировать факты отравления, показания потерпевших."
            ),
            inOfficialList = false
        ),
        Plant(
            name = "Дурман обыкновенный",
            latinName = "Datura stramonium",
            significant = 0.0,
            large = 0.0,
            extraLarge = 0.0,
            appearance = "Травянистое растение с крупными воронковидными цветками (белыми, фиолетовыми) и колючими плодами-коробочками. Содержит атропин, скополамин. Ядовитое растение.",
            hints = listOf(
                "В перечень Постановления № 1002 НЕ входит.",
                "Может фигурировать по ст. 234 УК РФ как ядовитое вещество.",
                "Назначить ботаническую и химическую экспертизу.",
                "При отравлении — фиксировать клиническую картину, изъять остатки."
            ),
            inOfficialList = false
        ),
        Plant(
            name = "Белладонна (красавка)",
            latinName = "Atropa belladonna",
            significant = 0.0,
            large = 0.0,
            extraLarge = 0.0,
            appearance = "Многолетнее травянистое растение с крупными овальными листьями, одиночными цветками (фиолетовыми, бурыми) и чёрными блестящими ягодами. Содержит атропин. Ядовитое растение.",
            hints = listOf(
                "В перечень Постановления № 1002 НЕ входит.",
                "Может фигурировать по ст. 234 УК РФ как ядовитое вещество.",
                "Назначить ботаническую и химическую экспертизу.",
                "Ягоды опасны для детей — фиксировать факты отравления."
            ),
            inOfficialList = false
        ),
        Plant(
            name = "Гармала обыкновенная (могильник)",
            latinName = "Peganum harmala",
            significant = 0.0,
            large = 0.0,
            extraLarge = 0.0,
            appearance = "Многолетнее травянистое растение с рассечёнными листьями и одиночными белыми цветками. Семена содержат гармин и гармалин — ингибиторы МАО, обладающие психоактивным действием.",
            hints = listOf(
                "В перечень Постановления № 1002 НЕ входит.",
                "Психоактивные свойства семян — гармин, гармалин.",
                "Может фигурировать как сырьё для кустарного изготовления.",
                "Назначить химическую экспертизу семян."
            ),
            inOfficialList = false
        ),
        Plant(
            name = "Ипомея (вьюнок) голубая",
            latinName = "Ipomoea tricolor, Turbina corymbosa",
            significant = 0.0,
            large = 0.0,
            extraLarge = 0.0,
            appearance = "Вьющееся травянистое растение с крупными воронковидными цветками (синими, фиолетовыми, розовыми). Семена содержат эргин — психоактивное вещество.",
            hints = listOf(
                "В перечень Постановления № 1002 НЕ входит.",
                "Семена содержат эргин (LSA).",
                "Может фигурировать как сырьё для кустарного изготовления.",
                "Назначить химическую экспертизу семян."
            ),
            inOfficialList = false
        ),
        Plant(
            name = "Мимоза стыдливая",
            latinName = "Mimosa pudica",
            significant = 0.0,
            large = 0.0,
            extraLarge = 0.0,
            appearance = "Травянистое растение с перистыми листьями, складывающимися при прикосновении. Содержит мимозин. В РФ не относится к запрещённым.",
            hints = listOf(
                "В перечень Постановления № 1002 НЕ входит.",
                "В РФ не запрещена, но в ряде стран — под контролем.",
                "Используется как декоративное растение."
            ),
            inOfficialList = false
        ),
        Plant(
            name = "Табак (никотиносодержащее сырьё)",
            latinName = "Nicotiana tabacum",
            significant = 0.0,
            large = 0.0,
            extraLarge = 0.0,
            appearance = "Травянистое растение с крупными листьями и трубчатыми цветками. Содержит никотин. Оборот табака регулируется отдельным законодательством.",
            hints = listOf(
                "В перечень Постановления № 1002 НЕ входит.",
                "Оборот регулируется ФЗ № 15-ФЗ (охрана здоровья от табачного дыма).",
                "К ст. 228–234 УК РФ не относится."
            ),
            inOfficialList = false
        )
    )

    fun search(query: String): List<Plant> {
        if (query.isBlank()) return emptyList()
        val q = query.trim().lowercase()
        return plants.filter { plant ->
            plant.name.lowercase().contains(q) ||
            plant.latinName.lowercase().contains(q)
        }
    }
}
