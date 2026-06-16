package com.focos_dengue.domain.model
enum class ReportType(val type: String) {
    BOTTLE_DRUMS_UNCOVERED("Caixa de Água Destampada"),
    TIRES_DISCARDED("Pneus Abandonados"),
    BOTTLES_OR_CONTAINERS_WITH_WATER("Garrafas/Recipientes com Água"),
    CONSTRUCTION_DEBRIS("Entulho de contrução"),
    POOL_WITHOUT_MAINTENANCE("Piscina sem Manutenção"),
    OTHER("Outro");
}

fun String.toReportType(): ReportType? {
    return ReportType.entries.find { it.type == this }
}
